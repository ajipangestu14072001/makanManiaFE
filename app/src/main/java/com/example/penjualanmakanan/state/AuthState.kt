package com.example.penjualanmakanan.state

import androidx.compose.runtime.Immutable
import com.example.penjualanmakanan.base.BaseState
import com.example.penjualanmakanan.model.LoginResponse
import com.example.penjualanmakanan.model.RegisterResponse

@Immutable
data class AuthState constructor(
    var isLoading: Boolean = true,
    val result:LoginResponse? = null,
    val registerResult:RegisterResponse? = null,
    val error: String? = null
) : BaseState {
    companion object {
        val initialState: AuthState
            get() = AuthState()
    }
}