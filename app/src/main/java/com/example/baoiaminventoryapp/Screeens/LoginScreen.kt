package com.example.baoiaminventoryapp.Screeens

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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.constraintlayout.compose.Visibility
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// Firebase auth and Navcontroller to be inserted.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(auth: FirebaseAuth,
              onSignedIn: (FirebaseUser?) -> Unit,
              navController: NavController
) {
    var employeeID by remember {
        //employee ID assigned by admin, can also use registered mobile number for more ease
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisibleStatus by remember {
        mutableStateOf(false)
    }

    var isSignIn by remember { mutableStateOf(true) }

    var myErrorMessage by remember { mutableStateOf<String?>(null) }

    val colorPallete = Color(0xFFC75C85)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues()) //gathers the info about the notch
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
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
            Spacer(modifier = Modifier.height(25.dp))
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
                        value = employeeID,
                        label = { Text(text = "Employee ID")},
                        onValueChange = {employeeID = it},
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                        ),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {password = it},
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
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White
                        ),
                        visualTransformation = if (passwordVisibleStatus) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            val image = if (passwordVisibleStatus) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            }
                            val description = if (passwordVisibleStatus) {
                                "Show Password"
                            } else {
                                "Hide Password"
                            }
                            IconButton(onClick = {passwordVisibleStatus = !(passwordVisibleStatus)}) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Forget Password?",
//                        modifier = Modifier
//                            .clickable {}
//                            .align(AbsoluteAlignment.Right)
//                            .padding(end = 40.dp),
//                        style = TextStyle(
//                            fontSize = 15.sp,
//                            textDecoration = TextDecoration.Underline
//                        )
//                    )
                    // Error Message
                    Spacer(modifier = Modifier.height(8.dp))
                    if (myErrorMessage != null) {
                        Text(
                            text = myErrorMessage!!,
                            color = Color.Yellow,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            if (isSignIn) {
                                signIn(auth, employeeID , password,
                                    onSignedIn = { signedInUser: FirebaseUser ->
                                        onSignedIn(signedInUser)
                                    },
                                    onSignInError = { errorMessage: String ->
                                        // Show toast message on sign-in error
                                        myErrorMessage = errorMessage
                                    },
                                    navController = navController
                                )
                            } else {
//                                signUp(auth, employeeID, password, firstName, lastName, { signedInUser: FirebaseUser ->
//                                    onSignedIn(signedInUser)
//                                }, navController)
                            }
                        }, //navcontroller to be integrated after firebase auth
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
//
//@Preview (showBackground = true, showSystemUi = true)
//@Composable
////fun LoginPagePreview(){
//////
////}