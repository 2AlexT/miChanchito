package com.example.primera_evaluacion.data.repository

import com.example.primera_evaluacion.data.local.EntidadHistorial
import com.example.primera_evaluacion.data.local.HistorialDao
import kotlinx.coroutines.flow.Flow

class RepositorioHistorial(
    private val dao: HistorialDao
) {

    fun obtenerTodos(): Flow<List<EntidadHistorial>> {
        return dao.obtenerTodos()
    }

    suspend fun insertar(registro: EntidadHistorial) {
        dao.insertar(registro)
    }

    suspend fun eliminarTodos() {
        dao.eliminarTodos()
    }
}
