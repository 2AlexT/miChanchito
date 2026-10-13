package com.example.primera_evaluacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.primera_evaluacion.navigation.NavegacionApp
import com.example.primera_evaluacion.ui.theme.TemaPrimeraEvaluacion

class ActividadPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemaPrimeraEvaluacion {
                Surface {
                    NavegacionApp()
                }
            }
        }
    }
}
