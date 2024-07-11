package com.example.baoiaminventoryapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Spacing(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun EnclosingBox (content:@Composable () -> Unit) {
    Box(modifier = Modifier
//        .fillMaxWidth()
//        .fillMaxHeight()
        .fillMaxSize()
        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        .background(color = Color(0xFFC75C85))
    ){
        content()
    }
}
