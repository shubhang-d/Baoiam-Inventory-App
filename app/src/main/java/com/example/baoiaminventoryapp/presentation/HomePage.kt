package com.example.baoiaminventoryapp.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.components.CustomButton
import com.example.baoiaminventoryapp.components.DivueensLogo
import com.example.baoiaminventoryapp.components.EnclosingBox
import com.example.baoiaminventoryapp.components.HeaderText
import com.example.baoiaminventoryapp.components.Spacing
import com.example.baoiaminventoryapp.components.SubmitButton

@Composable
fun HomePage(viewModel: HomeViewModel = hiltViewModel(), auth: FirebaseAuth, navController: NavController, context: Context) {
    val state = viewModel.state.collectAsState()
    var aisleNumber by remember { mutableStateOf("") }
    val colorPallete = Color(0xFFC75C85)
    val product by viewModel.product.observeAsState()
    viewModel.fetchProduct(barcode = state.value.details, apikey = "wqpopsvmuvjt6birqz0nqri78mm1bk") //this line needs to be run at the instance when a barcode is read
    Surface(color = Color.White
        ,modifier = Modifier
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
                    onClick = { viewModel.logout(auth, navController, context) }
                )
                CustomButton(
                    label = "Start Scanning",
                    color = colorPallete,
                    height = 40, width = 160,
                    onClick = {viewModel.startScanning()}
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
                    HeaderText(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Spacing(height = 20)
                    product?.let {
                        Text(text = "Product name: ${it.productName}")
                        Text(text = "Manufacturer: ${it.manufacturer}")
                    } ?: kotlin.run {
                        Text(text = "Product not found")
                    }
                    Text(
                        text = state.value.details,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                    Spacing(height = 5)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacing(width = 15)
                        Text(
                            text = "Aisle no.: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.Black
                        )
                        OutlinedTextField(
                            value = aisleNumber,
                            onValueChange = {aisleNumber = it},
                            label = { Text(text = "Aisle no.", color = Color.Black)},
                            modifier = Modifier.width(200.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done,
                            ),
                            textStyle = TextStyle(color = Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                unfocusedBorderColor = Color.LightGray,
                                focusedBorderColor = Color.White,
                            )
                        )
                    }
                    Spacing(height = 30)
                    SubmitButton(onClick = {
                        viewModel.updateAisleNumber(aisleNumber)
                        viewModel.sendDataToFirestore(context)
                    })
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePagePreview(){
    HomePage(auth = FirebaseAuth.getInstance(),
        navController = NavController(LocalContext.current),
        context = LocalContext.current)
}