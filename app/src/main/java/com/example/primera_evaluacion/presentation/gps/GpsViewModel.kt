package com.example.primera_evaluacion.presentation.gps

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.BaseDatosApp
import com.example.primera_evaluacion.data.local.EntidadHistorial
import com.example.primera_evaluacion.data.repository.RepositorioHistorial
import com.example.primera_evaluacion.domain.model.InformacionUbicacion
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GpsViewModel(
    aplicacion: Application
) : AndroidViewModel(aplicacion) {

    private val clienteUbicacion =
        LocationServices.getFusedLocationProviderClient(aplicacion)

    private val repositorio: RepositorioHistorial

    private val _ubicacion =
        MutableStateFlow<InformacionUbicacion?>(null)

    val ubicacion: StateFlow<InformacionUbicacion?> = _ubicacion

    private val _mensaje =
        MutableStateFlow("Presiona el botón para obtener tu ubicación")

    val mensaje: StateFlow<String> = _mensaje

    init {

        val baseDatos =
            BaseDatosApp.obtenerBaseDatos(aplicacion)

        repositorio =
            RepositorioHistorial(
                baseDatos.historialDao()
            )
    }

    fun obtenerUbicacion() {

        val contexto = getApplication<Application>()

        val permisoPreciso =
            ContextCompat.checkSelfPermission(
                contexto,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

        val permisoAproximado =
            ContextCompat.checkSelfPermission(
                contexto,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

        if (
            permisoPreciso != PackageManager.PERMISSION_GRANTED &&
            permisoAproximado != PackageManager.PERMISSION_GRANTED
        ) {

            _mensaje.value = "Permiso de ubicación no concedido"

            return
        }

        _mensaje.value = "Obteniendo ubicación..."

        clienteUbicacion
            .getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
            .addOnSuccessListener { ubicacionAndroid ->

                if (ubicacionAndroid != null) {

                    val informacion = InformacionUbicacion(
                        latitud = ubicacionAndroid.latitude,
                        longitud = ubicacionAndroid.longitude
                    )

                    _ubicacion.value = informacion

                    _mensaje.value =
                        "Ubicación obtenida correctamente"

                    guardarUbicacion(informacion)

                } else {

                    _mensaje.value =
                        "No fue posible obtener la ubicación"
                }
            }
            .addOnFailureListener {

                _mensaje.value =
                    "Error al obtener la ubicación"
            }
    }

    private fun guardarUbicacion(
        ubicacion: InformacionUbicacion
    ) {

        viewModelScope.launch {

            repositorio.insertar(
                EntidadHistorial(
                    tipo = "GPS",
                    titulo = "Ubicación obtenida",
                    descripcion =
                        "Latitud: ${ubicacion.latitud}, Longitud: ${ubicacion.longitud}"
                )
            )
        }
    }
}
