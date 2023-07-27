package com.example.penjualanmakanan.view.news

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.penjualanmakanan.utils.ArticleItem
import com.example.penjualanmakanan.utils.SearchBar
import com.example.penjualanmakanan.viewmodel.EverythingViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewScreen(
    everythingViewModel: EverythingViewModel = hiltViewModel(),
    ) {
    val everythingNews = everythingViewModel.everything.value.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    val context = LocalContext.current as ComponentActivity
    context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            everythingViewModel.getEverything()
            everythingViewModel.searchParam.value = "Food"
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            SearchBar(
                autoFocus = true,
                onSearch = {
                    everythingViewModel.getEverything()
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    everythingNews.loadState.refresh is LoadState.Loading ||
                            everythingNews.loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    everythingNews.loadState.refresh is LoadState.Error -> {
                        val errorMessage =
                            (everythingNews.loadState.refresh as LoadState.Error).error.localizedMessage
                        item {
                            Text(text = "Failed to load data: $errorMessage")
                        }
                    }

                    everythingNews.loadState.append is LoadState.Error -> {
                        val errorMessage =
                            (everythingNews.loadState.append as LoadState.Error).error.localizedMessage
                        item {
                            Text(text = "Failed to load more data: $errorMessage")
                        }
                    }

                    else -> {
                        items(everythingNews.itemCount) {
                            val article = everythingNews[it]
                            ArticleItem(article = article)
                        }
                    }
                }
            }
        }
    }
    
}