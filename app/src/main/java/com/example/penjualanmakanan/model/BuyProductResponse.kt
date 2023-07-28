package com.example.penjualanmakanan.model

data class BuyProductResponse(
    val `data`: DataXXX,
    val message: String,
    val paginationInfo: Any,
    val statusCode: Int
)