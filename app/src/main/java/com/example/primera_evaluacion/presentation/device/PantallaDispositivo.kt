package com.example.primera_evaluacion.presentation.device

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaDispositivo(
    alVolver: () -> Unit,
    modeloVista: DispositivoViewModel = viewModel()
) {

    val dispositivo = remember {
        modeloVista.obtenerInformacionDispositivo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Información del dispositivo")

        Text("Fabricante: ${dispositivo.fabricante}")

        Text("Modelo: ${dispositivo.modelo}")

        Text("Android: ${dispositivo.versionAndroid}")

        Text("SDK: ${dispositivo.versionSdk}")

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                modeloVista.guardarInformacionDispositivo()
            }
        ) {
            Text("Guardar en historial")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alVolver
        ) {
            Text("Volver")
        }
    }
}
