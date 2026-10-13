package com.example.primera_evaluacion.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Declaracion de la base de datos Room para el prototipo Centavo.
 * Se puede agregar un constructor singleton cuando este modulo se conecte a la app.
 */
@Database(
    entities = [EntidadTransaccion::class],
    version = 1,
    exportSchema = false
)
abstract class BaseDatosCentavo : RoomDatabase() {
    abstract fun transaccionDao(): TransaccionDao
}
