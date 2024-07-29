package com.example.baoiaminventoryapp.api

data class ProductResponse (
    val products: List<Product>
)

data class Product(
    val barcode_number: String,
    val barcode_formats: String,

    val title: String,
    val model: String,
    val description: String,
    val brand: String,

    val weight: String,
    val length: String,
    val height: String,
    val width: String,

    val asin: String,
    val manufacturer: String,
)