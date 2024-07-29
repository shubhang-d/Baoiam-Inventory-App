package com.example.baoiaminventoryapp.api

data class ProductResponse (
    val products: List<Product>
)

data class Product(
    val barcode_number: String,
    val title: String,
    val brand: String,
    val asin: String,
    val images: List<String>
)