package com.example.penjualanmakanan.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.penjualanmakanan.model.Article
import com.example.penjualanmakanan.network.ApiServices
import com.example.penjualanmakanan.pagging.EverythingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EverythingRepository @Inject constructor(
    private val api : ApiServices
) {

    fun getEverythingNews(
        q : String,
        from : String,
        shortBy : String
    ) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                EverythingSource(q = q, from = from, shortBy = shortBy, api = api)
            }
        ).flow
    }
}