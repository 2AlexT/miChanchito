package com.example.primera_evaluacion.data.repository

import com.example.primera_evaluacion.data.api.ApiCentavo
import com.example.primera_evaluacion.data.api.aDominio
import com.example.primera_evaluacion.data.api.aDto
import com.example.primera_evaluacion.data.database.TransaccionDao
import com.example.primera_evaluacion.data.database.aDominio
import com.example.primera_evaluacion.data.database.aEntidad
import com.example.primera_evaluacion.domain.model.Transaccion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementacion del repositorio que coordina la API y la cache de Room.
 */
class RepositorioTransaccionesImpl(
    private val api: ApiCentavo,
    private val dao: TransaccionDao
) : RepositorioTransacciones {

    override fun observarTransacciones(): Flow<List<Transaccion>> =
        dao.observarTodas().map { entidades -> entidades.map { it.aDominio() } }

    override suspend fun guardarTransaccion(transaccion: Transaccion) {
        val transaccionGuardada = api.crearTransaccion(transaccion.aDto()).aDominio()
        dao.insertar(transaccionGuardada.aEntidad())
    }

    override suspend fun actualizarTransacciones() {
        val transaccionesRemotas = api.obtenerTransacciones().map { it.aDominio().aEntidad() }
        dao.eliminarTodas()
        dao.insertarTodas(transaccionesRemotas)
    }
}
