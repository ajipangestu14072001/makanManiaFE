package com.example.penjualanmakanan.view.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.penjualanmakanan.model.TabItem
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MessagesScreen() {
    val tabs = listOf(TabItem.Chats, TabItem.Notifications)
    val pagerState = rememberPagerState()
    Column() {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = primaryColor,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, it)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                text = { Text(tab.title) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(
        state = pagerState, count = tabs.size) {
        tabs[it].screen()
    }
}


