package com.example.baoiaminventoryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.baoiaminventoryapp.R

@Composable
fun DivueensLogo() {
    Image(
        painter = painterResource(id = R.drawable.divueenscropped),
        contentDescription = "Company_logo",
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
    )
}