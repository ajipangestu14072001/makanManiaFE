package com.example.penjualanmakanan.model

import androidx.compose.runtime.Composable
import com.example.penjualanmakanan.view.messages.ChatScreen
import com.example.penjualanmakanan.view.messages.Notification
import com.example.penjualanmakanan.view.register.RegisterScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Chats : TabItem(title = "Chats", { ChatScreen() })
    object Notifications : TabItem(title = "Notifications", { Notification() })
}