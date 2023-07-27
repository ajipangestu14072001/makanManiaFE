package com.example.penjualanmakanan.view.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.ui.theme.secondaryColor
import com.example.penjualanmakanan.utils.InputBoxShape
import com.example.penjualanmakanan.utils.buttonColor
import com.example.penjualanmakanan.utils.buttonModifier
import com.example.penjualanmakanan.utils.textFieldColor

@Composable
fun RegisterScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.regis))
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier
                    .size(170.dp)
                    .padding(top = 10.dp)
            )

            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Create Your Account",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = primaryColor
            )

            TextField(
                value = name, onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColor(),
                shape = InputBoxShape.medium,
                singleLine = true,
                placeholder = {
                    Text(text = "Nama Lengkap",  color = secondaryColor)
                },
            )

            TextField(
                value = email, onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColor(),
                shape = InputBoxShape.medium,
                singleLine = true,
                placeholder = {
                    Text(text = "Alamat Email",  color = secondaryColor)
                },

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColor(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
                shape = InputBoxShape.medium,
                trailingIcon = {
                    IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                        if (!isPasswordOpen) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_open),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye_close),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                placeholder = {
                    Text(text = "Password",  color = secondaryColor)
                },
            )

            TextField(
                value = phone, onValueChange = { phone = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColor(),
                shape = InputBoxShape.medium,
                singleLine = true,
                placeholder = {
                    Text(text = "Telepon",  color = secondaryColor)
                },

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Button(
            onClick = {
            },
            modifier = Modifier
                .buttonModifier()
                .padding(bottom = 10.dp),
            colors = ButtonDefaults.buttonColor(),
            shape = InputBoxShape.medium,
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(text = "Daftar")
        }

    }

}


