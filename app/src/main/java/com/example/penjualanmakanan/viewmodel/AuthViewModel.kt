package com.example.penjualanmakanan.viewmodel

import com.example.penjualanmakanan.base.BaseViewModel
import com.example.penjualanmakanan.model.LoginRequest
import com.example.penjualanmakanan.model.RegisterRequest
import com.example.penjualanmakanan.repository.AuthRepository
import com.example.penjualanmakanan.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<AuthState>(AuthState.initialState) {

    suspend fun getLogin(
        username: String, password: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val read = try {
            val loginRequest = LoginRequest(username, password)
            repository.getLogin(loginRequest = loginRequest)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, result = read) }
    }

    suspend fun getRegister(
        namaLengkap: String,
        email: String,
        password: String,
        telepon: String
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val read = try {
            val registerRequest = RegisterRequest(
                namaLengkap = namaLengkap,
                email = email,
                password = password,
                telepon = telepon
            )
            repository.getRegister(registerRequest = registerRequest)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, registerResult = read) }
    }

}