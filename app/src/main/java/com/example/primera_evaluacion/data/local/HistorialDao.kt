package com.example.primera_evaluacion.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialDao {

    @Insert
    suspend fun insertar(registro: EntidadHistorial)

    @Query("SELECT * FROM historial ORDER BY fecha DESC")
    fun obtenerTodos(): Flow<List<EntidadHistorial>>

    @Query("DELETE FROM historial")
    suspend fun eliminarTodos()

    @Delete
    suspend fun eliminar(registro: EntidadHistorial)
}
