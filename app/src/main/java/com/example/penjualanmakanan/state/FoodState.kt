package com.example.penjualanmakanan.state

import androidx.compose.runtime.Immutable
import com.example.penjualanmakanan.base.BaseState
import com.example.penjualanmakanan.model.BuyProductResponse
import com.example.penjualanmakanan.model.FoodResponse
import com.example.penjualanmakanan.model.LoginResponse
import com.example.penjualanmakanan.model.RegisterResponse

@Immutable
data class FoodState constructor(
    var isLoading: Boolean = true,
    val result: FoodResponse? = null,
    val buyResult: BuyProductResponse? = null,
    val error: String? = null
) : BaseState {
    companion object {
        val initialState: FoodState
            get() = FoodState()
    }
}