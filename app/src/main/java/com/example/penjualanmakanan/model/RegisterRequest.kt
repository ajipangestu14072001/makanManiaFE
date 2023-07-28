package com.example.penjualanmakanan.model

data class RegisterRequest(
    val email: String,
    val namaLengkap: String,
    val password: String,
    val telepon: String
)