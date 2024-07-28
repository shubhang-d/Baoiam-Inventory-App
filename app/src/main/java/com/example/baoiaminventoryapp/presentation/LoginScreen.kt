package com.example.baoiaminventoryapp.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.example.baoiaminventoryapp.components.CustomButton
import com.example.baoiaminventoryapp.components.DivueensLogo
import com.example.baoiaminventoryapp.components.EnclosingBox
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User

@Composable
fun LoginPage(loginViewModel: LoginViewModel = viewModel(),
              app: App,
              onSignedIn: (User?) -> Unit,
              navController: NavController
) {
    val loginViewState by loginViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember{ androidx.compose.animation.core.Animatable(0f) }

    var passwordVisibleStatus by remember {
        mutableStateOf(false)
    }

    var myErrorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current


    val colorPalette = Color(0xFFC75C85)
    Surface(color = Color.White,
         modifier = Modifier
             .fillMaxSize()
             .background(color = Color.White)
             .padding(WindowInsets.statusBars.asPaddingValues()) //gathers the info about the notch
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(10.dp))
            DivueensLogo()
            Text(
                text = "Warehouse App",
                modifier = Modifier
                    .padding(top = 10.dp),
                color = colorPalette,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
          
            EnclosingBox {
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
                        value = loginViewState.employeeID,
                        label = { Text(text = "Employee ID")},
                        onValueChange = {loginViewModel.onEmployeeIDChange(it)},
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth()
                            .offset(x = offsetX.value.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        shape = RoundedCornerShape(20.dp),
                        isError = !isValidEmail(loginViewState.employeeID),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            focusedBorderColor = Color.White,
                            errorContainerColor = Color.White,
                            errorBorderColor = Color(0xFF7C0A02)
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if(!isValidEmail(loginViewState.employeeID) and loginViewState.employeeID.isNotBlank()){
                        loginViewModel.onIdErrorChange("Please enter a valid email!")
                        loginViewState.idError
                    }else{
                        loginViewModel.onIdErrorChange("")
                        loginViewState.idError
                    }
                    //employee ID error message
                    if(loginViewState.idError.isNotBlank()){
                        Text(text = loginViewState.idError,
                            color = Color(0xFF7C0A02),
                            fontSize = 15.sp)
                    }



                    Spacer(modifier = Modifier.height(40.dp))
                    OutlinedTextField(
                        value = loginViewState.password,
                        onValueChange = {loginViewModel.onPasswordChange(it)},
                        label = { Text(text = "Password")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .offset(x = offsetX.value.dp),
                        singleLine = true,
                        textStyle = TextStyle(color = Color.Black),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        isError = !isValidPassword(loginViewState.password),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            errorContainerColor = Color.White,
                            errorBorderColor = Color(0xFF7C0A02),
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
                    Spacer(modifier = Modifier.height(4.dp))
                    if(!isValidPassword(loginViewState.password) and loginViewState.password.isNotBlank()){
                        Text(text = "Please enter a valid password",
                            color = Color(0xFF7C0A02),
                            fontSize = 15.sp)
                    }

                    // Error Message
                    Spacer(modifier = Modifier.height(8.dp))
                    if (myErrorMessage != null) {
                        Text(
                            text = myErrorMessage!!,
                            color = Color(0xFF7C0A02),
                            fontSize = 18.sp,
                            modifier = Modifier.offset(x = offsetX.value.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    CustomButton(label = "Log in",
                         color = Color.Black,
                         height = 50,
                         width = 150,
                         onClick = {
                                 loginViewModel.signIn(app,
                                     loginViewState.employeeID, loginViewState.password,
                                     onSignedIn = { signedInUser: User? ->
                                         onSignedIn(signedInUser)
                                     },
                                     onSignInError = { errorMessage: String ->
                                         Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                     },
                                     navController = navController,
                                     coroutineScope,
                                     offsetX,
                                     context
                                 )
                         }
                    )
                }
            }
        }
    }
}


