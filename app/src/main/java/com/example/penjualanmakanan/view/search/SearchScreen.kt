package com.example.penjualanmakanan.view.search

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.penjualanmakanan.repository.DataStoreRepository
import com.example.penjualanmakanan.utils.Constant
import com.example.penjualanmakanan.utils.ImageSlider
import com.example.penjualanmakanan.utils.ListFood
import com.example.penjualanmakanan.utils.SearchBar
import com.example.penjualanmakanan.viewmodel.FoodViewModel

@Composable
fun SearchScreen(
    foodViewModel: FoodViewModel = hiltViewModel()
) {
    val context = LocalContext.current as ComponentActivity
    context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    val dataStore = DataStoreRepository(context)
    val token = dataStore.getToken.collectAsState(initial = "")
    val apiFoodItems by foodViewModel.apiFoodItems.observeAsState(emptyList())

    LaunchedEffect(Unit){
        foodViewModel.getAllFood(token = "Bearer ${token.value.toString()}")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)) {
        SearchBar(
            autoFocus = true,
            onSearch = {
            }
        )
        Text(
            text = "Selalu Untukmu",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 10.dp, top = 10.dp)
        )

        ImageSlider(images = Constant.images)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
//            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(apiFoodItems) { data ->
                ListFood(foodItem = data)
            }
        }
    }

}