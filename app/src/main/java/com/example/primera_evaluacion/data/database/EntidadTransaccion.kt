package com.example.primera_evaluacion.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.primera_evaluacion.domain.model.TipoTransaccion
import com.example.primera_evaluacion.domain.model.Transaccion

/** Representacion Room de una transaccion. */
@Entity(tableName = "transacciones")
data class EntidadTransaccion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val titulo: String,
    val montoEnCentavos: Long,
    val tipo: String,
    val creadaEn: Long
)

fun EntidadTransaccion.aDominio(): Transaccion = Transaccion(
    id = id,
    titulo = titulo,
    montoEnCentavos = montoEnCentavos,
    tipo = TipoTransaccion.valueOf(tipo),
    creadaEn = creadaEn
)

fun Transaccion.aEntidad(): EntidadTransaccion = EntidadTransaccion(
    id = id,
    titulo = titulo,
    montoEnCentavos = montoEnCentavos,
    tipo = tipo.name,
    creadaEn = creadaEn
)
