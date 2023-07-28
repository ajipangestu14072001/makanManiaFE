package com.example.penjualanmakanan.view.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.model.HomeSection
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.utils.NoRippleTheme
import com.example.penjualanmakanan.utils.ShimmerImage
import com.example.penjualanmakanan.utils.bottomBarHeight
import com.example.penjualanmakanan.utils.icon
import com.example.penjualanmakanan.view.home.HomeScreen
import com.example.penjualanmakanan.view.messages.MessagesScreen
import com.example.penjualanmakanan.view.news.NewScreen
import com.example.penjualanmakanan.view.profile.ProfileScreen
import com.example.penjualanmakanan.view.search.SearchScreen

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController) {

    val sectionState = remember { mutableStateOf(HomeSection.Home) }

    val navItems = HomeSection.values()
        .toList()
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Scaffold(
            bottomBar = {
                BottomBar(
                    items = navItems,
                    currentSection = sectionState.value,
                    onSectionSelected = {
                        sectionState.value = it
                    },
                )
            }) { innerPadding ->
            val modifier = Modifier.padding(paddingValues = innerPadding)
            Crossfade(
                modifier = modifier,
                targetState = sectionState.value, label = ""
            )
            { section ->
                when (section) {
                    HomeSection.Home -> HomeScreen(navController = navController)
                    HomeSection.Reels -> SearchScreen()
                    HomeSection.Add -> NewScreen()
                    HomeSection.Favorite -> MessagesScreen()
                    HomeSection.Profile -> ProfileScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    items: List<HomeSection>,
    currentSection: HomeSection,
    onSectionSelected: (HomeSection) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.height(height = bottomBarHeight),
        containerColor = Color.White,
        contentColor = primaryColor
    ) {
        items.forEach { section ->

            val selected = section == currentSection

            val iconRes = if (selected) section.selectedIcon else section.icon
            val inactiveColor = Color(0xFF777777)

            NavigationBarItem(
                icon = {

                    if (section == HomeSection.Profile) {
                        BottomBarProfile(selected)
                    } else {
                        Icon(
                            painter = painterResource(id = iconRes),
                            modifier = Modifier.icon(),
                            tint = if (selected) primaryColor else inactiveColor,
                            contentDescription = ""
                        )
                    }

                },
                selected = selected,
                onClick = { onSectionSelected(section) },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
private fun BottomBarProfile(isSelected: Boolean) {
    val shape = CircleShape

    val borderModifier = if (isSelected) {
        Modifier
            .border(
                color = primaryColor,
                width = 1.dp,
                shape = shape
            )
    } else Modifier

    val padding = if (isSelected) 3.dp else 0.dp

    Box(
        modifier = borderModifier
    ) {
        Box(
            modifier = Modifier
                .icon()
                .padding(all = padding)
                .background(color = Color.LightGray, shape = shape)
                .clip(shape = shape)
        ) {
            ShimmerImage(
                imageUrl = "https://avatars.githubusercontent.com/u/50790624?v=4",
                placeholderResId = R.drawable.image_not_available,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}
