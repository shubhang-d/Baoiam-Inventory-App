package com.example.baoiaminventoryapp.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.baoiaminventoryapp.api.BarcodeLookupService
import com.example.baoiaminventoryapp.api.Product
import com.example.baoiaminventoryapp.domain.repo.MainRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo,
    private  val api: BarcodeLookupService
):ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val db = Firebase.firestore

    //barcodeAPI
    private val _product = MutableLiveData<Product?>()
    val product: LiveData<Product?> = _product

    fun fetchProduct(barcode: String, apikey: String) {
        viewModelScope.launch {
            try {
                val encodedBarcode = URLEncoder.encode(barcode, "UTF-8") //debug attempt
                val response = api.getProduct(encodedBarcode, true, apikey)
                Log.d("ProductViewModel", "Scanned Barcode: $barcode") //debug
                Log.d("ProductViewModel", "Response: ${response.products}") //debug
                if (response.products.isNotEmpty()) {
                    _product.postValue(response.products[0])
                } else {
                    _product.postValue(null)
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching product info", e) //debug
                _product.postValue(null)
            }
        }
    }


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

    fun logout(auth: FirebaseAuth, navController: NavController, context: Context){
        auth.signOut()
        if(auth.currentUser == null){
            navController.navigate("login")
        }else{
            Toast.makeText(context, "Failed to signOut", Toast.LENGTH_SHORT).show()
        }
    }
}