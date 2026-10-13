package com.example.primera_evaluacion.data.datasource

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.example.primera_evaluacion.domain.model.InformacionSensor

class FuenteDatosSensores(
    contexto: Context
) {

    private val administradorSensores =
        contexto.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    fun obtenerSensores(): List<InformacionSensor> {

        return administradorSensores
            .getSensorList(Sensor.TYPE_ALL)
            .map { sensor ->

                InformacionSensor(
                    nombre = sensor.name,
                    tipo = sensor.type,
                    fabricante = sensor.vendor
                )
            }
    }
}
