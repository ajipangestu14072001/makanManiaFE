package com.example.penjualanmakanan.view.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.repository.DataStoreRepository
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.ui.theme.secondaryColor
import com.example.penjualanmakanan.utils.DefaultSpacer
import com.example.penjualanmakanan.utils.InputBoxShape
import com.example.penjualanmakanan.utils.Loading
import com.example.penjualanmakanan.utils.buttonColor
import com.example.penjualanmakanan.utils.buttonModifier
import com.example.penjualanmakanan.utils.textFieldColor
import com.example.penjualanmakanan.utils.textFieldModifier
import com.example.penjualanmakanan.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val state by authViewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) Loading()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.food))
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
                modifier = Modifier.padding(top = 6.dp),
                text = "Login to Your Account",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = primaryColor
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.textFieldModifier(),
                colors = TextFieldDefaults.textFieldColor(),
                shape = InputBoxShape.medium,
                singleLine = true,
                leadingIcon = {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = "",
                            tint = primaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                        DefaultSpacer()
                    }
                },
                placeholder = {
                    Text(text = "Email Address", color = secondaryColor)
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.textFieldModifier(),
                colors = TextFieldDefaults.textFieldColor(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = InputBoxShape.medium,
                singleLine = true,
                visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
                leadingIcon = {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = "",
                            tint = primaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                        DefaultSpacer()
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                        if (!isPasswordOpen) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_open),
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_close),
                                contentDescription = "",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                placeholder = {
                    Text(text = "Password", color = secondaryColor)
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

        }
        Button(
            onClick = {
                scope.launch {
                    showDialog = true
                    authViewModel.getLogin(username = email, password = password)
                    if (state.result != null) {
                        showDialog = false
                        navController.navigate(Screen.Main.route)
                        dataStore.saveTokenAndId(
                            token = state.result?.data!!.accessToken,
                            id = state.result?.data!!.id
                        )
                    }
                }
            },
            modifier = Modifier
                .buttonModifier()
                .padding(bottom = 10.dp),
            colors = ButtonDefaults.buttonColor(),
            shape = InputBoxShape.medium,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(text = "Log In")
        }


        OutlinedButton(
            onClick = {
                navController.navigate(Screen.Register.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .padding(horizontal = 20.dp),
            border = BorderStroke(1.dp, primaryColor),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = primaryColor,
            ),
            shape = InputBoxShape.medium,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(text = "Register", color = primaryColor)
        }
    }
}
