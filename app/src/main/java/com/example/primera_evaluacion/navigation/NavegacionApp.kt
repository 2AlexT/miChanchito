package com.example.primera_evaluacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primera_evaluacion.presentation.camera.PantallaCamara
import com.example.primera_evaluacion.presentation.device.PantallaDispositivo
import com.example.primera_evaluacion.presentation.gps.PantallaGps
import com.example.primera_evaluacion.presentation.history.PantallaHistorial
import com.example.primera_evaluacion.presentation.home.PantallaInicio
import com.example.primera_evaluacion.presentation.sensors.PantallaSensores

@Composable
fun NavegacionApp() {

    val controladorNavegacion = rememberNavController()

    NavHost(
        navController = controladorNavegacion,
        startDestination = RutasApp.INICIO
    ) {

        composable(RutasApp.INICIO) {
            PantallaInicio(
                alPulsarDispositivo = {
                    controladorNavegacion.navigate(RutasApp.DISPOSITIVO)
                },
                alPulsarSensores = {
                    controladorNavegacion.navigate(RutasApp.SENSORES)
                },
                alPulsarGps = {
                    controladorNavegacion.navigate(RutasApp.GPS)
                },
                alPulsarCamara = {
                    controladorNavegacion.navigate(RutasApp.CAMARA)
                },
                alPulsarHistorial = {
                    controladorNavegacion.navigate(RutasApp.HISTORIAL)
                }
            )
        }

        composable(RutasApp.DISPOSITIVO) {
            PantallaDispositivo(
                alVolver = {
                    controladorNavegacion.popBackStack()
                }
            )
        }

        composable(RutasApp.SENSORES) {
            PantallaSensores(
                alVolver = {
                    controladorNavegacion.popBackStack()
                }
            )
        }

        composable(RutasApp.GPS) {
            PantallaGps(
                alVolver = {
                    controladorNavegacion.popBackStack()
                }
            )
        }

        composable(RutasApp.CAMARA) {
            PantallaCamara(
                alVolver = {
                    controladorNavegacion.popBackStack()
                }
            )
        }

        composable(RutasApp.HISTORIAL) {
            PantallaHistorial(
                alVolver = {
                    controladorNavegacion.popBackStack()
                }
            )
        }
    }
}
