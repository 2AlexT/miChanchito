package com.example.primera_evaluacion.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [EntidadHistorial::class],
    version = 2,
    exportSchema = false
)
abstract class BaseDatosApp : RoomDatabase() {

    abstract fun historialDao(): HistorialDao

    companion object {

        private val MIGRACION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS historial_nuevo (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        tipo TEXT NOT NULL,
                        titulo TEXT NOT NULL,
                        descripcion TEXT NOT NULL,
                        fecha INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    INSERT INTO historial_nuevo (id, tipo, titulo, descripcion, fecha)
                    SELECT id, type, title, description, date FROM history
                    """.trimIndent()
                )
                db.execSQL("DROP TABLE history")
                db.execSQL("ALTER TABLE historial_nuevo RENAME TO historial")
            }
        }

        @Volatile
        private var INSTANCIA: BaseDatosApp? = null

        fun obtenerBaseDatos(contexto: Context): BaseDatosApp {

            return INSTANCIA ?: synchronized(this) {

                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    BaseDatosApp::class.java,
                    "evaluacion_movil_db"
                )
                    .addMigrations(MIGRACION_1_2)
                    .build()

                INSTANCIA = instancia

                instancia
            }
        }
    }
}
