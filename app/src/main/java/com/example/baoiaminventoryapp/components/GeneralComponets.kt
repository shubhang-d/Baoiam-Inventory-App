package com.example.baoiaminventoryapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Spacing(height: Int = 0, width: Int = 0) {
    Spacer(modifier = Modifier.height(height.dp).width(width.dp))
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

@Composable
fun SubmitButton () {
    Button(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .height(50.dp)
    ){
        Text(text = "Submit", fontSize = 16.sp)
    }
}

@Composable
fun HeaderText (qrCodeDetected: Boolean = false, modifier:Modifier) {
    val headerText = if (qrCodeDetected) "No barcode detected" else "Barcode infromation"
    Text(
        text = headerText,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        modifier = modifier
    )
}