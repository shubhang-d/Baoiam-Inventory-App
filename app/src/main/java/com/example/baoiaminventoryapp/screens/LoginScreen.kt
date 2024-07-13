package com.example.baoiaminventoryapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.components.CustomButton
import com.example.baoiaminventoryapp.components.CustomTextField
import com.example.baoiaminventoryapp.components.DivueensLogo
import com.example.baoiaminventoryapp.components.EnclosingBox
import com.example.baoiaminventoryapp.components.Spacing

// Firebase auth and Navcontroller to be inserted.
//@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LoginPage(navController: NavController) {
    //emlpyee ID assigned by admin, can also use registered mobile number for more ease
    val colorPallete = Color(0xFFC75C85)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues()) //gathers the info about the notch
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacing(height = 10)
            DivueensLogo()
            Text(
                text = "Warehouse App",
                color = colorPallete,
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp
            )
            Spacing(height = 25)
            EnclosingBox {
                Column (horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()){
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "LOG IN",
                        fontWeight = FontWeight.Thin,
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacing(height = 30)
                    CustomTextField(
                        label = "Employee ID",
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth()
                        )
                    Spacing(height = 40)
                    CustomTextField(
                        label = "Password",
                        passwordField = true,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth()
                        )
                    Spacing(height = 8)
                    Spacing(height = 30)
                    CustomButton(label = "Log in", color = Color.Black, height = 50, width = 150, onClick = {navController.navigate("home")})
                }
            }
        }
    }
}