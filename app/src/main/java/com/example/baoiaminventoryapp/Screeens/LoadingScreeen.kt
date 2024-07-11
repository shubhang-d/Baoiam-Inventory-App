package com.example.baoiaminventoryapp.Screeens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baoiaminventoryapp.R

// mainly  for decoration purpose, can be used while the scanner and firebase api loads in the background
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Company_Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(220.dp)
        )
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreenPreview(){
    LoadingScreen()
}