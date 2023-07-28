package com.example.penjualanmakanan.view.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.repository.DataStoreRepository
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.utils.Constant
import com.example.penjualanmakanan.utils.OptionsItemStyle
import com.example.penjualanmakanan.utils.ShimmerImage
import com.example.penjualanmakanan.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val idProfile = dataStore.getUserId.collectAsState(initial = "")

    LaunchedEffect(Unit){
        viewModel.getProfile(id = idProfile.value.toString())
        val profileData = state.registerResult?.data
        if (profileData != null) {
            namaLengkap = profileData.namaLengkap
            email = profileData.email
        }
    }

    Column {
        Box {
            val overlayBoxHeight = 20.dp
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(containerColor = primaryColor),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 40.dp,
                    bottomStart = 40.dp,
                ),
            ) {

            }
            ShimmerImage(
                imageUrl = "https://avatars.githubusercontent.com/u/50790624?v=4",
                placeholderResId = R.drawable.image_not_available,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = 0.dp, y = overlayBoxHeight)
                    .clip(CircleShape)
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = namaLengkap,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = email,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyColumn {
                items(Constant.optionsList) { item ->
                    OptionsItemStyle(item = item)
                }

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                navController.navigate(route = Screen.Login.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = primaryColor),
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(text = "Keluar", color = Color.Gray)
        }
    }
}