package com.example.baoiaminventoryapp.api

data class ProductResponse (
    val products: List<Product>
)

data class Product(
    val barcode: String,
    val productName: String,
    val manufacturer: String,
    val asin: String
)