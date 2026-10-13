package com.example.primera_evaluacion.presentation.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PantallaHistorial(
    alVolver: () -> Unit,
    modeloVista: HistorialViewModel = viewModel()
) {

    val historial by modeloVista.historial.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Historial")

        Text(
            text = "Registros: ${historial.size}"
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(historial) { registro ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = registro.titulo
                        )

                        Text(
                            text = registro.tipo
                        )

                        Text(
                            text = registro.descripcion
                        )

                        Text(
                            text = formatearFecha(registro.fecha)
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                modeloVista.limpiarHistorial()
            }
        ) {
            Text("Limpiar historial")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = alVolver
        ) {
            Text("Volver")
        }
    }
}

private fun formatearFecha(marcaTiempo: Long): String {

    val formateador =
        SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        )

    return formateador.format(
        Date(marcaTiempo)
    )
}
