package com.example.penjualanmakanan.network

import com.example.penjualanmakanan.model.EverythingResponse
import com.example.penjualanmakanan.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("everything")
    suspend fun getEverything(
        @Query("page") page : Int = 1,
        @Query("q") q : String,
        @Query("from") from : String,
        @Query("shortBy") shortBy : String,
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ): EverythingResponse
}