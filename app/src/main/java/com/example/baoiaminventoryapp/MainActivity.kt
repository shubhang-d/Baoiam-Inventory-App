package com.example.baoiaminventoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baoiaminventoryapp.screens.LoginPage
import com.example.baoiaminventoryapp.screens.navHost
import com.example.baoiaminventoryapp.ui.theme.BaoiamInventoryAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaoiamInventoryAppTheme {
                navHost()
            }
        }
    }
}
