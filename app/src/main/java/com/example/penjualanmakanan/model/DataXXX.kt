package com.example.penjualanmakanan.model

data class DataXXX(
    val deliveryStatus: String,
    val id: String,
    val items: List<Item>,
    val orderDate: String,
    val totalAmount: Double,
    val totalItems: Int
)