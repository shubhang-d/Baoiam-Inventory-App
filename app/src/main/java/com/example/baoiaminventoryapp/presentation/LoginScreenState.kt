package com.example.baoiaminventoryapp.presentation

data class LoginScreenState(
    val employeeID: String = "",
    val password: String = "",
    val idError: String = "",
    val passwordVisibleStatus: String = "",
    val isSignIn: Boolean = true,
    val myErrorMessage: String? = null,

)