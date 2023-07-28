package com.example.penjualanmakanan.model

data class FoodResponse(
    val `data`: List<DataXX>,
    val message: String,
    val paginationInfo: PaginationInfo,
    val statusCode: Int
)