package com.example.baoiaminventoryapp.Screeens

import android.util.Patterns

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPassword(password: String):Boolean{
    return  password.length >= 6
}