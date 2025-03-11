package com.dportenis.dphome

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController, startDestination = "home") {
            composable("home") {
                HomeScreen { navController.navigate("progress") }
            }
            composable("progress") {
                LocationScreen { navController.popBackStack() }
            }
        }
    }
}