package com.example.penjualanmakanan.model

data class Item(
    val deliveryTime: Int,
    val distance: Double,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val rating: Double,
    val seller: String,
    val stock: Int
)