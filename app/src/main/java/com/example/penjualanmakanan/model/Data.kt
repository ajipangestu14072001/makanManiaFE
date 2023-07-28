package com.example.penjualanmakanan.model

data class Data(
    val accessToken: String,
    val email: String,
    val id: String,
    val namaLengkap: String,
    val roles: List<String>,
    val telepon: String,
    val tokenType: String
)