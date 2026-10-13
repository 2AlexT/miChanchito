package com.example.primera_evaluacion.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransaccionDao {
    @Query("SELECT * FROM transacciones ORDER BY creadaEn DESC")
    fun observarTodas(): Flow<List<EntidadTransaccion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(transaccion: EntidadTransaccion)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(transacciones: List<EntidadTransaccion>)

    @Query("DELETE FROM transacciones")
    suspend fun eliminarTodas()
}
