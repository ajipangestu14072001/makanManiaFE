package com.example.penjualanmakanan.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.penjualanmakanan.view.cart.CartScreen
import com.example.penjualanmakanan.view.favorite.FavoriteScreen
import com.example.penjualanmakanan.view.home.HomeScreen
import com.example.penjualanmakanan.view.login.LoginScreen
import com.example.penjualanmakanan.view.main.MainScreen
import com.example.penjualanmakanan.view.messages.ChatScreen
import com.example.penjualanmakanan.view.register.CompleteRegister
import com.example.penjualanmakanan.view.register.RegisterScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun NavController(
    navController: NavHostController,
    startDestination: String
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screen.Cart.route) {
            CartScreen(navController = navController)
        }

        composable(route = Screen.Fav.route) {
            FavoriteScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navHostController = navController)
        }

        composable(route = Screen.CompleteRegister.route) {
            CompleteRegister()
        }
    }
}