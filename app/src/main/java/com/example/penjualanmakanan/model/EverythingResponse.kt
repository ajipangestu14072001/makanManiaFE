package com.example.penjualanmakanan.model

data class EverythingResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)