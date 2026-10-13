package com.example.primera_evaluacion.presentation.device

import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.BaseDatosApp
import com.example.primera_evaluacion.data.local.EntidadHistorial
import com.example.primera_evaluacion.data.repository.RepositorioHistorial
import com.example.primera_evaluacion.domain.model.InformacionDispositivo
import kotlinx.coroutines.launch

class DispositivoViewModel(
    aplicacion: Application
) : AndroidViewModel(aplicacion) {

    private val repositorio: RepositorioHistorial

    init {

        val baseDatos = BaseDatosApp.obtenerBaseDatos(aplicacion)

        repositorio = RepositorioHistorial(
            baseDatos.historialDao()
        )
    }

    fun obtenerInformacionDispositivo(): InformacionDispositivo {

        return InformacionDispositivo(
            fabricante = Build.MANUFACTURER,
            modelo = Build.MODEL,
            versionAndroid = Build.VERSION.RELEASE,
            versionSdk = Build.VERSION.SDK_INT
        )
    }

    fun guardarInformacionDispositivo() {

        val dispositivo = obtenerInformacionDispositivo()

        viewModelScope.launch {

            repositorio.insertar(
                EntidadHistorial(
                    tipo = "DISPOSITIVO",
                    titulo = "Información del dispositivo",
                    descripcion = "${dispositivo.fabricante} ${dispositivo.modelo} - Android ${dispositivo.versionAndroid}"
                )
            )
        }
    }
}
