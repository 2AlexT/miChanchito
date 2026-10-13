package com.example.primera_evaluacion.data.repository

import com.example.primera_evaluacion.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow

/** Contrato unico de datos que consumen los casos de uso. */
interface RepositorioTransacciones {
    fun observarTransacciones(): Flow<List<Transaccion>>

    suspend fun guardarTransaccion(transaccion: Transaccion)

    suspend fun actualizarTransacciones()
}
