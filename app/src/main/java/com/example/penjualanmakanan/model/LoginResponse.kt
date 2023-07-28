package com.example.penjualanmakanan.model

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val paginationInfo: Any,
    val statusCode: Int
)