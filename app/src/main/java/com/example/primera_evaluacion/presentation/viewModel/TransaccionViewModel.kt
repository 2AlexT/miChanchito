package com.example.primera_evaluacion.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.domain.model.TipoTransaccion
import com.example.primera_evaluacion.domain.model.Transaccion
import com.example.primera_evaluacion.domain.usecase.GuardarTransaccionCasoUso
import com.example.primera_evaluacion.domain.usecase.ObtenerTransaccionesCasoUso
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** Contenedor de estado que consume la interfaz de Compose. */
class TransaccionViewModel(
    obtenerTransacciones: ObtenerTransaccionesCasoUso,
    private val guardarTransaccion: GuardarTransaccionCasoUso
) : ViewModel() {

    val transacciones: StateFlow<List<Transaccion>> = obtenerTransacciones()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun agregarGasto(titulo: String, montoEnCentavos: Long) {
        viewModelScope.launch {
            guardarTransaccion(
                Transaccion(
                    titulo = titulo,
                    montoEnCentavos = montoEnCentavos,
                    tipo = TipoTransaccion.GASTO
                )
            )
        }
    }
}
