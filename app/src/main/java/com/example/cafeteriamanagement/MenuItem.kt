package com.example.cafeteriamanagement

data class MenuItem(
    val id: String,
    val name: String,
    val price: Int,
    var quantity: Int
)
