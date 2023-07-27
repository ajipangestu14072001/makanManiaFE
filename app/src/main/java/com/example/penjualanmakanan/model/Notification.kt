package com.example.penjualanmakanan.model

data class Notification(
    val title: String,
    val description: String,
    val time: String,
    val isUnread: Boolean = true
)