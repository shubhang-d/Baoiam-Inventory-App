package com.example.baoiaminventoryapp.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.animation.onLoginError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
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
        val appID = "application-1-ifxueik"
        if (isValidEmail(email) and isValidPassword(password)) {
            val app: App = App.create(appID) // Replace this with your App ID
            runBlocking {
                val emailPasswordCredentials =
                    io.realm.kotlin.mongodb.Credentials.emailPassword(email, password)
                try {
                    val user = app.login(emailPasswordCredentials)
                    navController.navigate("home")
                } catch (e: Exception) {
                    onSignInError("Invalid email or password")
                    onLoginError(coroutineScope, offset)
                }


            }

        }

    }
}