package com.example.baoiaminventoryapp.Screeens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlin.contracts.contract

fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignedIn: (FirebaseUser) -> Unit,
    onSignInError: (String) -> Unit,// Callback for sign-in error
    navController: NavController,
    coroutineScope: CoroutineScope,
    offset: Animatable<Float, AnimationVector1D>
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                onSignedIn(user!!)
                navController.navigate("home")
            } else {
                // Handle sign-in failure
                onSignInError("Invalid email or password")
                onLoginError(coroutineScope, offset)
            }
        }
}
//fun signUp(
//    auth: FirebaseAuth,
//    email: String,
//    password: String,
//    firstName: String,
//    lastName: String,
//    onSignedIn: (FirebaseUser?) -> Unit,
//    navController: NavController,
//
//) {
//    auth.createUserWithEmailAndPassword(email, password)
//        .addOnCompleteListener { task ->
//            if (task.isComplete) {
//                val user = auth.currentUser
//                onSignedIn(user)
//                navController.navigate("home")
//
//            } else {
//                // Handle sign-up failure
////                Toast.makeText(LocalContext.current, "SignUp failed", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//}

fun logout(auth: FirebaseAuth, navController: NavController, context: Context){
    auth.signOut()
    auth.addAuthStateListener { fireauth->
        if (fireauth.currentUser == null){
            navController.navigate("login")
        }
        else{
            Toast.makeText(context, "Failed to signOut", Toast.LENGTH_SHORT).show()
        }
    }

}