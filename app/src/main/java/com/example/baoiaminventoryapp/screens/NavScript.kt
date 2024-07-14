package com.example.baoiaminventoryapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


@Composable
fun NavigationScript(auth: FirebaseAuth,
                     navController: NavHostController,
                     currentUser: FirebaseUser?,
                     onSignedIn: (FirebaseUser?) -> Unit,

){
    val context = LocalContext.current
    NavHost(navController = navController,
        startDestination = if (currentUser != null){ "home"} else{ "login"}){
        composable(route= "login"){
            LoginPage(auth, onSignedIn = onSignedIn, navController = navController)
        }
        composable("signup"){
            LoginPage(auth, onSignedIn = onSignedIn,  navController = navController)
        }
        composable(route = "home"){
            HomePage(auth, navController, context)
        }
        composable(route = "enter"){
            LoadingScreen()
        }
    }
}