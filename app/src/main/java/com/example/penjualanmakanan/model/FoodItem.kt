package com.example.penjualanmakanan.model

data class FoodItem(
    val name: String,
    val imageUrl: String,
    val deliveryTime: Int,
    val distance: Double,
    val rating: Float
)