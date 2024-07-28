package com.example.baoiaminventoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.baoiaminventoryapp.navigation.NavigationScript
import com.example.baoiaminventoryapp.ui.theme.BaoiamInventoryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.mongodb.App


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appID = "application-1-ifxueik"
        val app: App = App.create(appID)
        var user: io.realm.kotlin.mongodb.User? = app.currentUser
        setContent{
            BaoiamInventoryAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavigationScript(app, navController, user, onSignedIn = {signedInUser->
                        user = signedInUser
                    })
                }
            }
        }
    }
}


