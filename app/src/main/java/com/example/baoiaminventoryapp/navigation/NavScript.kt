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
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User


@Composable
fun NavigationScript(app: App,
                     navController: NavHostController,
                     currentUser: User?,
                     onSignedIn: (User?) -> Unit,

){
    val context = LocalContext.current
    NavHost(navController = navController,
        startDestination = if (currentUser != null){ "home"} else{ "login"}){
        composable(route= "login"){
            LoginPage(app= app, onSignedIn = onSignedIn, navController = navController)
        }
        composable("signup"){
            LoginPage(app = app, onSignedIn = onSignedIn,  navController = navController)
        }
        composable(route = "home"){
            HomePage(navController = navController, context = context, user = currentUser)
        }
        composable(route = "enter"){
            com.example.baoiaminventoryapp.presentation.LoadingScreen(navController = navController)
        }
    }
}