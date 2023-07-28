package com.example.penjualanmakanan.repository

import com.example.penjualanmakanan.model.BuyProductRequest
import com.example.penjualanmakanan.model.BuyProductResponse
import com.example.penjualanmakanan.model.FoodResponse
import com.example.penjualanmakanan.network.ApiServices
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api : ApiServices
) {
    suspend fun getAllFood(token : String): FoodResponse{
        return api.getAllFood(token = token)
    }

    suspend fun buyFood(token: String, request: BuyProductRequest): BuyProductResponse{
        return api.buyProduct(token = token, buyProductRequest = request)
    }
}