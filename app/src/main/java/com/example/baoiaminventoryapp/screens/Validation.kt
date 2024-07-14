package com.example.baoiaminventoryapp.screens

import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String):Boolean{
    return  password.length >= 6
}