package com.example.baoiaminventoryapp.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.keyframes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun onLoginError(coroutineScope: CoroutineScope, offsetX: Animatable<Float, AnimationVector1D>) {
    coroutineScope.launch {
        offsetX.animateTo(
            targetValue = 0f,
            animationSpec = keyframes {
                durationMillis = 300
                (-15f) at 0
                15f at 100
                (-15f) at 200
                0f at 300
            }
        )
    }
}