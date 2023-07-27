package com.example.penjualanmakanan.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.penjualanmakanan.model.Article
import com.example.penjualanmakanan.network.ApiServices
import retrofit2.HttpException
import java.io.IOException

class EverythingSource(
    private val api: ApiServices,
    private val q : String,
    private val from : String,
    private val shortBy : String
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPage = params.key ?: 1
            val newEverything =
                api.getEverything(page = nextPage,q = q, from = from, shortBy = shortBy)
            LoadResult.Page(
                data = newEverything.articles,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (newEverything.articles.isEmpty()) null else nextPage + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}