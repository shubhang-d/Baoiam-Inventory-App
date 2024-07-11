package com.example.baoiaminventoryapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baoiaminventoryapp.components.CustomButton
import com.example.baoiaminventoryapp.components.CustomTextField
import com.example.baoiaminventoryapp.components.DivueensLogo
import com.example.baoiaminventoryapp.components.EnclosingBox
import com.example.baoiaminventoryapp.components.HeaderText
import com.example.baoiaminventoryapp.components.Spacing
import com.example.baoiaminventoryapp.components.SubmitButton

@Composable
fun HomePage(dataOfQR: String) {
    val scannedLines = dataOfQR.split("\n")
    val colorPallete = Color(0xFFC75C85)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues())
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Row (verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 10.dp, top = 30.dp)
            ) {
                CustomButton(
                    label = "Log out",
                    color = Color.Black,
                    height = 40, width = 120,
                    onClick = {}
                )
                CustomButton(
                    label = "Start Scanning",
                    color = colorPallete,
                    height = 40, width = 160,
                    onClick = {}
                )
            }
            Spacing(height = 20)
            DivueensLogo()
            Spacing(height = 20)
            EnclosingBox {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacing(height = 30)
                    HeaderText(modifier = Modifier.align(Alignment.CenterHorizontally)) // I dont like this at all I will replace/ make it look better later
                    Spacing(height = 20)
                    scannedLines.forEach { line ->
                        val colonSeperatedLines = line.split(":")
                        Row {
                            Text(
                                text = colonSeperatedLines[0] + ": ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                modifier = Modifier.padding(start = 15.dp, bottom = 8.dp)
                            )
                            Text(
                                text = colonSeperatedLines[1],
                                fontSize = 25.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                    Spacing(height = 5)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacing(width = 15)
                        Text(
                            text = "Aisle no.: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        CustomTextField(
                            modifier = Modifier
                                .height(25.dp)
                                .width(150.dp)
                        )
                    }
                    Spacing(height = 30)
                    SubmitButton()
                }
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomePage(dataOfQR = "name: john wick \nproduct: sample product \nExpiry date: 01/01/2026 \nFragile product: yes \nproduct id:123456789")
}