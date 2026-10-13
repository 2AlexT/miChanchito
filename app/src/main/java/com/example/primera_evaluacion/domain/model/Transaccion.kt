package com.example.primera_evaluacion.domain.model

/**
 * Modelo de dominio usado por el prototipo Centavo.
 *
 * Los modelos de dominio contienen solamente datos de negocio. No dependen de
 * Compose, Room ni de una API remota.
 */
data class Transaccion(
    val id: Long = 0,
    val titulo: String,
    val montoEnCentavos: Long,
    val tipo: TipoTransaccion,
    val creadaEn: Long = System.currentTimeMillis()
)

enum class TipoTransaccion {
    INGRESO,
    GASTO
}
