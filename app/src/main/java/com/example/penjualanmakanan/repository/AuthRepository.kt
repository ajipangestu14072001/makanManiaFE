package com.example.penjualanmakanan.repository

import com.example.penjualanmakanan.model.LoginRequest
import com.example.penjualanmakanan.model.LoginResponse
import com.example.penjualanmakanan.model.RegisterRequest
import com.example.penjualanmakanan.model.RegisterResponse
import com.example.penjualanmakanan.network.ApiServices
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api : ApiServices
) {
    suspend fun getLogin(loginRequest: LoginRequest): LoginResponse{
        return api.getLogin(loginRequest = loginRequest)
    }

    suspend fun getRegister(registerRequest: RegisterRequest): RegisterResponse{
        return api.getRegister(registerRequest = registerRequest)
    }
}