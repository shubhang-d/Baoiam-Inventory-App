package com.example.baoiaminventoryapp.screens


import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baoiaminventoryapp.R

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun HomePage() {
    val dataOfQR = remember {
        mutableStateOf<String?>("name: john wick \nage: idk honestly \nis he cool: hell yeah") //QR code data get here in the from of a string
    }
    val scannedLines = dataOfQR.value?.split("\n") ?: listOf() // Handling null value

    /*
    * the data gets scanned in the from of string i bilieve
    * the incoming data gets split with the newline chracter, as we have no concrete idea of how many lines of info the qr will carry,
    * so we need a dynamic behavior of the information display. */

    val headerText = if (true /* QR code detected*/) { //checks wht heading text to display (the one directly below the scan area)
        "No barcode detected"
    } else {
        "Barcode infromation"
    }

    val colorPallete = Color(0xFFC75C85)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = R.drawable.divueenscropped),
                    contentDescription = "Divueens",
                    contentScale = ContentScale.Fit
                )
                Button(
                    onClick = {/* firebase auth logout logic + nav controller to login page*/},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorPallete),
                    modifier = Modifier
                        .height(40.dp)
                        .width(120.dp)
                        .padding(end = 15.dp)
                ) {
                    Text(text = "Log out")
                }
            }
            Spacer(modifier = Modifier.height(50.dp)) //isolating the barcode area for enhance visibility
            Box(modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Color.Black)
            ){
                //barcode scan area
            }
            Spacer(modifier = Modifier.height(30.dp)) //isolating the barcode area for enhance visibility
            Text(text = headerText, fontSize = 25.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.height(20.dp))
            Column (modifier = Modifier.fillMaxWidth()){
                scannedLines.forEach { line ->
                    val colonSeperatedLines = line.split(":")
                    Row {
                        Text(
                            text = colonSeperatedLines[0]+": ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        Text(
                            text = colonSeperatedLines[1],
                            fontSize = 25.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row{
                Text(text = "Aisle nnumber: ")
            }
        }
    }
}