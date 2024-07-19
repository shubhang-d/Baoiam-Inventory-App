package com.example.baoiaminventoryapp.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.animation.onLoginError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    fun onEmployeeIDChange(employeeID: String) {
        _uiState.value = _uiState.value.copy(employeeID = employeeID)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onIdErrorChange(idError: String) {
        _uiState.value = _uiState.value.copy(idError = idError)
    }

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
        if(isValidEmail(email) and isValidPassword(password)){
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
        }else{
            onLoginError(coroutineScope, offset)
        }
    }

}