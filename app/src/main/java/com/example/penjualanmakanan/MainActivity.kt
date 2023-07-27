package com.example.penjualanmakanan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.penjualanmakanan.navigation.NavController
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.ui.theme.PenjualanMakananTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PenjualanMakananTheme {
                val navController = rememberAnimatedNavController()
                NavController(
                    navController = navController,
                    startDestination = Screen.Login.route
                )
            }
        }
    }
}