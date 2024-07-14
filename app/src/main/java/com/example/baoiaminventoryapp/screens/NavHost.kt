package com.example.baoiaminventoryapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navHost() {
    val navController = rememberNavController()
    Surface (modifier = Modifier.fillMaxSize()){
        NavHost(navController = navController, startDestination = "loading") {
            composable("loading") { LoadingScreen(navController = navController)}
            composable("login") { LoginPage(navController = navController)}
            composable("home") { HomePage(dataOfQR = "name: john wick \nproduct: sample product \nExpiry date: 01/01/2026 \nFragile product: yes \nproduct id:123456789", navController = navController)}
        }
    }
}