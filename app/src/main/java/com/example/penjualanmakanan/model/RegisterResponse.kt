package com.example.penjualanmakanan.model

data class RegisterResponse(
    val `data`: DataX,
    val message: String,
    val paginationInfo: Any,
    val statusCode: Int
)