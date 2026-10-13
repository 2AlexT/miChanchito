package com.example.primera_evaluacion.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val EsquemaOscuro = darkColorScheme(
    primary = Morado80,
    secondary = GrisMorado80,
    tertiary = Rosa80
)

private val EsquemaClaro = lightColorScheme(
    primary = Morado40,
    secondary = GrisMorado40,
    tertiary = Rosa40

    /* Otros colores predeterminados que se pueden personalizar:
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TemaPrimeraEvaluacion(
    temaOscuro: Boolean = isSystemInDarkTheme(),
    // El color dinamico esta disponible desde Android 12.
    colorDinamico: Boolean = true,
    contenido: @Composable () -> Unit
) {
    val esquemaColores = when {
        colorDinamico && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val contexto = LocalContext.current
            if (temaOscuro) dynamicDarkColorScheme(contexto) else dynamicLightColorScheme(contexto)
        }

        temaOscuro -> EsquemaOscuro
        else -> EsquemaClaro
    }

    MaterialTheme(
        colorScheme = esquemaColores,
        typography = Tipografia,
        content = contenido
    )
}
