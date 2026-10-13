package com.example.primera_evaluacion.data.api

import com.example.primera_evaluacion.domain.model.TipoTransaccion
import com.example.primera_evaluacion.domain.model.Transaccion

/** Representacion recibida o enviada por medio de la API remota. */
data class TransaccionDto(
    val id: Long,
    val titulo: String,
    val montoEnCentavos: Long,
    val tipo: String,
    val creadaEn: Long
)

fun TransaccionDto.aDominio(): Transaccion = Transaccion(
    id = id,
    titulo = titulo,
    montoEnCentavos = montoEnCentavos,
    tipo = TipoTransaccion.valueOf(tipo),
    creadaEn = creadaEn
)

fun Transaccion.aDto(): TransaccionDto = TransaccionDto(
    id = id,
    titulo = titulo,
    montoEnCentavos = montoEnCentavos,
    tipo = tipo.name,
    creadaEn = creadaEn
)
