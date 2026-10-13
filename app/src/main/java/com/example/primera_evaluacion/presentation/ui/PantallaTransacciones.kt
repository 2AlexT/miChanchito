package com.example.primera_evaluacion.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.primera_evaluacion.domain.model.Transaccion

/**
 * Prototipo de interfaz sin estado. La navegacion y una fabrica de ViewModel
 * se pueden conectar al definir las pantallas finales y la inyeccion de dependencias.
 */
@Composable
fun PantallaTransacciones(
    transacciones: List<Transaccion>,
    alAgregarGastoEjemplo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Centavo - Movimientos")

        Button(onClick = alAgregarGastoEjemplo) {
            Text(text = "Agregar gasto de ejemplo")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(transacciones, key = { it.id }) { transaccion ->
                Text(
                    text = "${transaccion.titulo}: ${transaccion.montoEnCentavos} centavos"
                )
            }
        }
    }
}
