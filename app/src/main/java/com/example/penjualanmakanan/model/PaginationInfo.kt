package com.example.penjualanmakanan.model

data class PaginationInfo(
    val limit: Int,
    val page: Int,
    val totalData: Int,
    val totalPages: Int
)