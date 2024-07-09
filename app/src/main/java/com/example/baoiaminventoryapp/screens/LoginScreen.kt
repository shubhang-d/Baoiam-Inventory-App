package com.example.baoiaminventoryapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.example.baoiaminventoryapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LoginPage() {
    val empolyeeID = remember { //emlpyee ID assigned be admin, can also use registered mobile number for more ease
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisibleStatus = remember {
        mutableStateOf(false)
    }
    val colorPallete = Color(0xFFC75C85)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
//            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.divueenscropped),
                contentDescription = "Company_logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(top = 10.dp)
            )
            Text(
                text = "Warehouse App",
                modifier = Modifier
                    .padding(top = 10.dp),
                color = colorPallete,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .background(color = colorPallete)
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()){
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "LOG IN",
                        style = TextStyle(
                            fontWeight = FontWeight.Thin
                        ),
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(
                            top = 10.dp
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = empolyeeID.value,
                        label = { Text(text = "Employee ID")},
                        onValueChange = {empolyeeID.value = it},
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = {password.value = it},
                        label = { Text(text = "Password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White
                        ),
                        visualTransformation = if (passwordVisibleStatus.value) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            val image = if (passwordVisibleStatus.value) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            }
                            val description = if (passwordVisibleStatus.value) {
                                "Show Password"
                            } else {
                                "Hide Password"
                            }
                            IconButton(onClick = {passwordVisibleStatus.value = !(passwordVisibleStatus.value)}) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Forget Password?",
                        modifier = Modifier
                            .clickable {}
                            .align(AbsoluteAlignment.Right)
                            .padding(end = 40.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                    ) {
                        Text(text = "Log in")
                    }
                }
            }
        }
    }
}