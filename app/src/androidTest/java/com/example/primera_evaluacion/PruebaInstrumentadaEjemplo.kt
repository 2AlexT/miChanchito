package com.example.primera_evaluacion

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Prueba instrumentada que se ejecuta en un dispositivo Android.
 *
 * Consulta la [documentacion de pruebas](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PruebaInstrumentadaEjemplo {
    @Test
    fun usaContextoAplicacion() {
        // Contexto de la aplicacion bajo prueba.
        val contextoAplicacion = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.primera_evaluacion", contextoAplicacion.packageName)
    }
}
