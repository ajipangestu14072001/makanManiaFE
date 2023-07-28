package com.example.penjualanmakanan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.penjualanmakanan.base.BaseViewModel
import com.example.penjualanmakanan.model.BuyProductRequest
import com.example.penjualanmakanan.model.DataXX
import com.example.penjualanmakanan.model.FoodItem
import com.example.penjualanmakanan.model.FoodResponse
import com.example.penjualanmakanan.model.LoginRequest
import com.example.penjualanmakanan.repository.FoodRepository
import com.example.penjualanmakanan.state.FoodState
import com.example.penjualanmakanan.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository
) : BaseViewModel<FoodState>(FoodState.initialState) {
    private val _apiFoodItems: MutableLiveData<List<FoodItem>> = MutableLiveData()
    val apiFoodItems: LiveData<List<FoodItem>> get() = _apiFoodItems

    private fun convertToFoodItem(data: DataXX): FoodItem {
        return FoodItem(
            name = data.name,
            imageUrl = data.imageUrl,
            deliveryTime = data.deliveryTime,
            distance = data.distance,
            rating = data.rating.toFloat()
        )
    }

    suspend fun geyBuyFood(
        token: String,
        deliveryStatus: String,
        items: List<String>
    ) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val read = try {
            val buyProductRequest = BuyProductRequest(
                deliveryStatus = deliveryStatus,
                items = items
            )
            repository.buyFood(token = token, request = buyProductRequest)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }
        setState { copy(isLoading = false, buyResult = read) }
    }

    suspend fun getAllFood(token: String) = withContext(Dispatchers.Default) {
        setState { copy(isLoading = true) }
        val read = try {
            repository.getAllFood(token = token)
        } catch (e: HttpException) {
            setState { copy(isLoading = false, error = e.message) }
            return@withContext
        }

        val apiFoodItems = read.data.map { data ->
            convertToFoodItem(data)
        }

        val combinedFoodItems = Constant.foodItems + apiFoodItems

        _apiFoodItems.postValue(combinedFoodItems)
        setState { copy(isLoading = false, result = read) }
    }
}