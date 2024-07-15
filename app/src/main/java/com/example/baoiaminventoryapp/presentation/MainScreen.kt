package com.example.baoiaminventoryapp.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.R
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), auth: FirebaseAuth, navController: NavController, context: Context) {
    val dataOfQR = remember {
        mutableStateOf<String?>("name: john wick \nage: idk honestly \nis he cool: hell yeah") //QR code data get here in the from of a string
    }
    val scannedLines = dataOfQR.value?.split("\n") ?: listOf() // Handling null value
    val state = viewModel.state.collectAsState()
    val headerText = if (true /* QR code detected*/) { //checks wht heading text to display (the one directly below the scan area)
        "No barcode detected"
    } else {
        "Barcode infromation"
    }
    val colorPallete = Color(0xFFC75C85)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar with logo and logout button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.divueens_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(90.dp))
            Button(
                onClick = { /* Handle logout */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF16192))
            ) {
                Text("Log out", color = Color.White)
            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Barcode area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Black, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Barcode area", color = Color.White, fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Package details
        Column {
            Text(
                text = "Information:",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = state.value.details,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )

            DetailRow("Aisle number:", "") {
                TextField(
                    value = state.value.aisleNumber, // Assuming you've added aisleNumber to your state
                    onValueChange = { viewModel.updateAisleNumber(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        // Submit button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Handle submit */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
            ) {
                Text("Submit", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f), contentAlignment = Alignment.BottomCenter
            ) {
                Button(onClick = { viewModel.startScanning() }) {
                    Text(text = "Start Scanning")
                }
            }
        }

    }
}

@Composable
fun DetailRow(label: String, value: String, content: @Composable () -> Unit = { Text(value) }) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Bold)
        content()
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}