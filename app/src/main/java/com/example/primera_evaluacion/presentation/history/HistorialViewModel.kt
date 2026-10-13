package com.example.primera_evaluacion.presentation.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primera_evaluacion.data.local.BaseDatosApp
import com.example.primera_evaluacion.data.local.EntidadHistorial
import com.example.primera_evaluacion.data.repository.RepositorioHistorial
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistorialViewModel(
    aplicacion: Application
) : AndroidViewModel(aplicacion) {

    private val repositorio: RepositorioHistorial

    val historial: StateFlow<List<EntidadHistorial>>

    init {

        val baseDatos =
            BaseDatosApp.obtenerBaseDatos(aplicacion)

        repositorio =
            RepositorioHistorial(
                baseDatos.historialDao()
            )

        historial =
            repositorio
                .obtenerTodos()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )
    }

    fun limpiarHistorial() {

        viewModelScope.launch {

            repositorio.eliminarTodos()
        }
    }
}
