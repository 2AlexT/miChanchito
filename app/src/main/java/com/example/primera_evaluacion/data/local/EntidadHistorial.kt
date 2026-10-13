package com.example.primera_evaluacion.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial")
data class EntidadHistorial(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tipo: String,

    val titulo: String,

    val descripcion: String,

    val fecha: Long = System.currentTimeMillis()
)
