package com.example.baoiaminventoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baoiaminventoryapp.presentation.HomePage
import com.example.baoiaminventoryapp.presentation.LoginPage
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
            LoginPage(auth = auth, onSignedIn = onSignedIn, navController = navController)
        }
        composable("signup"){
            LoginPage(auth = auth, onSignedIn = onSignedIn,  navController = navController)
        }
        composable(route = "home"){
            HomePage(auth = auth, navController = navController, context = context)
        }
        composable(route = "enter"){
            com.example.baoiaminventoryapp.presentation.LoadingScreen(navController = navController)
        }
    }
}