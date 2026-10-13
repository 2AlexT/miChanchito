package com.example.primera_evaluacion.data.api

/**
 * Contrato para el futuro servicio remoto.
 *
 * Las anotaciones de Retrofit se pueden agregar cuando se conozcan la URL
 * real del backend y sus endpoints.
 */
interface ApiCentavo {
    suspend fun obtenerTransacciones(): List<TransaccionDto>

    suspend fun crearTransaccion(transaccion: TransaccionDto): TransaccionDto
}
