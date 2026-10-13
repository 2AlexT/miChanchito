package com.example.primera_evaluacion.presentation.gps

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaGps(
    alVolver: () -> Unit,
    modeloVista: GpsViewModel = viewModel()
) {

    val ubicacion by modeloVista.ubicacion.collectAsStateWithLifecycle()

    val mensaje by modeloVista.mensaje.collectAsStateWithLifecycle()

    val lanzadorPermisos =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {
            modeloVista.obtenerUbicacion()
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text("GPS")

        Text(mensaje)

        ubicacion?.let {

            Text(
                text = "Latitud: ${it.latitud}"
            )

            Text(
                text = "Longitud: ${it.longitud}"
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                lanzadorPermisos.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        ) {
            Text("Actualizar ubicación")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alVolver
        ) {
            Text("Volver")
        }
    }
}
