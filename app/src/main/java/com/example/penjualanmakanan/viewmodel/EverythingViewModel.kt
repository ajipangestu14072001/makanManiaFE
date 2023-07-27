package com.example.penjualanmakanan.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.penjualanmakanan.model.Article
import com.example.penjualanmakanan.repository.EverythingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    private val repository: EverythingRepository
) : ViewModel() {
    private var _everything = mutableStateOf<Flow<PagingData<Article>>>(emptyFlow())
    val everything: State<Flow<PagingData<Article>>> = _everything
    var searchParam = mutableStateOf(value = "Food")
    var previousSearch = mutableStateOf(value = "Food")

    init {
        getEverything()
    }

    fun getEverything() {
        viewModelScope.launch {
            repository.getEverythingNews(q = searchParam.value, from = "", shortBy = "2023-06-10")
                .also {
                    _everything.value = it
                }.cachedIn(viewModelScope)
        }
    }

}