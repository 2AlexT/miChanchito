package com.example.primera_evaluacion.presentation.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun PantallaCamara(
    alVolver: () -> Unit
) {

    val contexto = LocalContext.current

    var fotografia by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var mensaje by remember {
        mutableStateOf("No hay fotografía")
    }

    val lanzadorCamara =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { mapaBits ->

            if (mapaBits != null) {

                fotografia = mapaBits

                mensaje =
                    "Fotografía capturada correctamente"
            }
        }
    val lanzadorPermisoCamara =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { concedido ->

            if (concedido) {

                mensaje =
                    "Permiso concedido. Presiona nuevamente para tomar la fotografía."

            } else {

                mensaje =
                    "Permiso de cámara denegado"
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Cámara"
        )

        Text(
            text = mensaje
        )

        fotografia?.let { mapaBits ->

            Image(
                bitmap = mapaBits.asImageBitmap(),
                contentDescription = "Fotografía capturada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                val estadoPermiso =
                    ContextCompat.checkSelfPermission(
                        contexto,
                        Manifest.permission.CAMERA
                    )

                if (
                    estadoPermiso ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    lanzadorCamara.launch(null)

                } else {
                    lanzadorPermisoCamara.launch(
                        Manifest.permission.CAMERA
                    )
                }
            }
        ) {

            Text(
                text = "Tomar fotografía"
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                fotografia = null

                mensaje = "No hay fotografía"
            }
        ) {

            Text(
                text = "Limpiar fotografía"
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alVolver
        ) {

            Text(
                text = "Volver"
            )
        }
    }
}
