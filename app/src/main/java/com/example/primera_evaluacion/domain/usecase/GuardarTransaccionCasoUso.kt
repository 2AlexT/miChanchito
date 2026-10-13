package com.example.primera_evaluacion.domain.usecase

import com.example.primera_evaluacion.data.repository.RepositorioTransacciones
import com.example.primera_evaluacion.domain.model.Transaccion

/**
 * Valida una transaccion antes de pedir al repositorio que la guarde.
 */
class GuardarTransaccionCasoUso(
    private val repositorio: RepositorioTransacciones
) {
    suspend operator fun invoke(transaccion: Transaccion) {
        require(transaccion.titulo.isNotBlank()) { "El titulo no puede estar vacio" }
        require(transaccion.montoEnCentavos > 0) { "El monto debe ser mayor que cero" }
        repositorio.guardarTransaccion(transaccion)
    }
}
