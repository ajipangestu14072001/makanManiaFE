package com.example.penjualanmakanan.model

data class BuyProductRequest(
    val deliveryStatus: String,
    val items: List<String>
)