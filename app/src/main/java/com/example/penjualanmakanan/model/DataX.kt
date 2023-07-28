package com.example.penjualanmakanan.model

data class DataX(
    val cart: Any,
    val email: String,
    val id: String,
    val namaLengkap: String,
    val password: String,
    val roles: List<Role>,
    val telepon: String
)