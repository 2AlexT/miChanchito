package com.example.primera_evaluacion.presentation.sensors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.primera_evaluacion.data.datasource.FuenteDatosSensores
import com.example.primera_evaluacion.domain.model.InformacionSensor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SensoresViewModel(
    aplicacion: Application
) : AndroidViewModel(aplicacion) {

    private val fuenteDatos =
        FuenteDatosSensores(aplicacion)

    private val _sensores =
        MutableStateFlow<List<InformacionSensor>>(emptyList())

    val sensores: StateFlow<List<InformacionSensor>> = _sensores

    init {
        cargarSensores()
    }

    private fun cargarSensores() {

        _sensores.value = fuenteDatos.obtenerSensores()
    }
}
