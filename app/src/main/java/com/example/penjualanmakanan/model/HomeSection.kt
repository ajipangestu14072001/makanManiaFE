package com.example.penjualanmakanan.model

import com.example.penjualanmakanan.R

 enum class HomeSection(
    val icon: Int,
    val selectedIcon: Int,
) {
    Home(icon = R.drawable.home, selectedIcon = R.drawable.home_selected),
    Reels(icon = R.drawable.search, selectedIcon = R.drawable.search_selected),
    Add(icon = R.drawable.outline_text_snippet_24, selectedIcon = R.drawable.round_text_snippet_24),
    Favorite(icon = R.drawable.outline_message_24, selectedIcon = R.drawable.round_message_24),
    Profile(icon = R.drawable.baseline_circle_24, selectedIcon = R.drawable.baseline_circle_24)
}