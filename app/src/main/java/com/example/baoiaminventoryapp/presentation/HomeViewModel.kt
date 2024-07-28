package com.example.baoiaminventoryapp.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.domain.repo.MainRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo
):ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val db = Firebase.firestore


    fun startScanning(){
        viewModelScope.launch {
            repo.startScanning().collect{
                if (!it.isNullOrBlank()){
                    _state.value = state.value.copy(
                        details = it
                    )
                }
            }
        }
    }
    fun updateAisleNumber(number: String) {
        _state.value = _state.value.copy(aisleNumber = number)
    }

    fun sendDataToFirestore(context: Context){
        if (_state.value.details.equals("Start scanning to get details")) {
            Toast.makeText(context, "Please start scanning first", Toast.LENGTH_SHORT).show()
        }else if(_state.value.aisleNumber.isEmpty()){
            Toast.makeText(context, "Please enter Aisle Details Again", Toast.LENGTH_SHORT).show()
        }else{
            val data = hashMapOf(
                "data" to _state.value.details,
                "aisleNumber" to _state.value.aisleNumber
            )
            db.collection("Products").document().set(data).addOnSuccessListener {
                Toast.makeText(context, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed to update data", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun logout(navController: NavController, context: Context, user: User?){
        try {
            runBlocking { user?.logOut() }

            Toast.makeText(context, "Logged out Successfully", Toast.LENGTH_SHORT).show()
            navController.navigate("login")
        }
        catch (e: Exception){
            Toast.makeText(context, "Logout failed", Toast.LENGTH_SHORT).show()
        }


        //logout logic
    }
}