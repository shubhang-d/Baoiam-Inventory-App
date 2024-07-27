package com.example.baoiaminventoryapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface BarcodeLookupService {
    @GET("v3/products")
    suspend fun getProduct(
        @Query("barcode") barcode: String,
        @Query("formatted") formatted: Boolean,
        @Query("key") apiKey: String
    ): ProductResponse
}