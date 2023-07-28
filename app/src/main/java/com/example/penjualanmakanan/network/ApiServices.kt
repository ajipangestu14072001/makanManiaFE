package com.example.penjualanmakanan.network

import com.example.penjualanmakanan.model.EverythingResponse
import com.example.penjualanmakanan.model.LoginRequest
import com.example.penjualanmakanan.model.LoginResponse
import com.example.penjualanmakanan.model.RegisterRequest
import com.example.penjualanmakanan.model.RegisterResponse
import com.example.penjualanmakanan.utils.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("https://newsapi.org/v2/everything")
    suspend fun getEverything(
        @Query("page") page : Int = 1,
        @Query("q") q : String,
        @Query("from") from : String,
        @Query("shortBy") shortBy : String,
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ): EverythingResponse

    @POST("api/auth/signin")
    suspend fun getLogin(@Body loginRequest: LoginRequest) : LoginResponse

    @POST("api/auth/signup")
    suspend fun getRegister(@Body registerRequest: RegisterRequest) : RegisterResponse

}