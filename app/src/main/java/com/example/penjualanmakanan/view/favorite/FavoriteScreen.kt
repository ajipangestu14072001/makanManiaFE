package com.example.penjualanmakanan.view.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.utils.InputBoxShape
import com.example.penjualanmakanan.utils.buttonColor
import com.example.penjualanmakanan.utils.buttonModifier

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.fav))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = "Find your favorite foods with our\nsupport specialist here!",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Button(
            onClick = {
                navController.navigate(Screen.Main.route)
            },
            modifier = Modifier
                .buttonModifier()
                .padding(bottom = 10.dp),
            colors = ButtonDefaults.buttonColor(),
            shape = InputBoxShape.medium,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            androidx.compose.material3.Text(text = "Explore Now!")
        }
    }
}