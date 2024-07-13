package com.example.baoiaminventoryapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.R
import kotlinx.coroutines.delay

// mainly  for decoration purpose, can be used while the scanner and firebase api loads in the background
//@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LoadingScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate("login") {
            popUpTo("splash") {inclusive = true}
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        Image(
            painter = painterResource(id = R.drawable.divueenscropped),
            contentDescription = "Company_Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(220.dp)
        )
    }
}