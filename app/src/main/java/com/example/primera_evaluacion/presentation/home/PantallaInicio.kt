package com.example.primera_evaluacion.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(
    alPulsarDispositivo: () -> Unit,
    alPulsarSensores: () -> Unit,
    alPulsarGps: () -> Unit,
    alPulsarCamara: () -> Unit,
    alPulsarHistorial: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Mi Dispositivo"
        )

        Text(
            text = "Explora las capacidades del dispositivo"
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alPulsarDispositivo
        ) {
            Text("Información del dispositivo")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alPulsarSensores
        ) {
            Text("Sensores")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alPulsarGps
        ) {
            Text("GPS")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alPulsarCamara
        ) {
            Text("Cámara")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alPulsarHistorial
        ) {
            Text("Historial")
        }
    }
}
