package com.example.penjualanmakanan.view.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.penjualanmakanan.utils.Constant.notifications
import com.example.penjualanmakanan.utils.NotificationList

@Composable
fun Notification() {
    Column(modifier = Modifier.fillMaxSize()) {
        NotificationList(notifications = notifications)
    }
}