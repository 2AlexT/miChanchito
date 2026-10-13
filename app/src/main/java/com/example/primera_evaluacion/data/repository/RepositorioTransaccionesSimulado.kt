package com.example.primera_evaluacion.data.repository

import com.example.primera_evaluacion.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/** Implementacion en memoria para usar mientras la API o la base de datos no esten terminadas. */
class RepositorioTransaccionesSimulado(
    transaccionesIniciales: List<Transaccion> = emptyList()
) : RepositorioTransacciones {
    private val transacciones = MutableStateFlow(transaccionesIniciales)

    override fun observarTransacciones(): Flow<List<Transaccion>> = transacciones

    override suspend fun guardarTransaccion(transaccion: Transaccion) {
        transacciones.value = listOf(transaccion) + transacciones.value
    }

    override suspend fun actualizarTransacciones() = Unit
}
