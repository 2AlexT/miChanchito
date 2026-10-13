package com.example.primera_evaluacion.domain.usecase

import com.example.primera_evaluacion.data.repository.RepositorioTransacciones
import com.example.primera_evaluacion.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow

/** Devuelve las transacciones que debe mostrar la capa de presentacion. */
class ObtenerTransaccionesCasoUso(
    private val repositorio: RepositorioTransacciones
) {
    operator fun invoke(): Flow<List<Transaccion>> = repositorio.observarTransacciones()
}
