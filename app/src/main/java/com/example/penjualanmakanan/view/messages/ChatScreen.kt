package com.example.penjualanmakanan.view.messages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
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
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.ui.theme.primaryColor

@Composable
fun ChatScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.botchat))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(170.dp)
        )

        Text(
            text = "Find your chat with our support\nspecialist here!",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Text(
            text = buildAnnotatedString {
                append("You can also get help from them via our ")
                withStyle(style = SpanStyle(color = primaryColor, fontWeight = FontWeight.Bold)) {
                    append("Help\nCenter")
                }
                append(".")
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}