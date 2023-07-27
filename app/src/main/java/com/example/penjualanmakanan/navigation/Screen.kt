package com.example.penjualanmakanan.navigation

sealed class Screen (val route: String){
    object Login : Screen(route = "login")
    object Register : Screen(route = "register")
    object CompleteRegister : Screen(route = "completeRegister")
    object Main : Screen(route = "main")
    object Cart : Screen(route = "cart")
    object Fav : Screen(route = "favorite")
}