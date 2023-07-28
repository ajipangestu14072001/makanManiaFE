package com.example.penjualanmakanan.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.repository.DataStoreRepository
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.utils.Chip
import com.example.penjualanmakanan.utils.Constant.deliveryOptions
import com.example.penjualanmakanan.utils.Constant.foodCategories
import com.example.penjualanmakanan.utils.Constant.foodItems
import com.example.penjualanmakanan.utils.Constant.images
import com.example.penjualanmakanan.utils.FoodItemList
import com.example.penjualanmakanan.utils.ImageSlider
import com.example.penjualanmakanan.utils.ListFood
import com.example.penjualanmakanan.utils.ShimmerImage
import com.example.penjualanmakanan.utils.buttonColor
import com.example.penjualanmakanan.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    foodViewModel: FoodViewModel = hiltViewModel()
    ) {
    val listState = rememberLazyListState()
    val selectedOptionState = remember { mutableStateOf(deliveryOptions[0]) }
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val token = dataStore.getToken.collectAsState(initial = "")
    val apiFoodItems by foodViewModel.apiFoodItems.observeAsState(emptyList())

    LaunchedEffect(Unit){
        foodViewModel.getAllFood(token = "Bearer ${token.value.toString()}")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "Deliver To".uppercase(), color = White, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Jalan Hanjuang Nomor 2",
                            color = White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = primaryColor,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Fav.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "",
                            tint = White
                        )
                    }

                    BadgedBox(
                        badge = { Badge { Text("4") } },
                        modifier = Modifier
                            .padding(start = 5.dp, end = 20.dp)
                            .clickable {
                                navController.navigate(Screen.Cart.route)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            tint = White

                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                LazyRow(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    items(deliveryOptions) { option ->
                        Chip(
                            deliveryOptions = option.name,
                            selected = option == selectedOptionState.value,
                            onClick = {
                                selectedOptionState.value = option
                            },
                            icon = option.icon
                        )
                    }
                }
                LazyColumn(
                    state = listState
                ) {
                    items(foodCategories.chunked(size = 4)) { items ->
                        Row(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            )
                        ) {
                            for ((index, item) in items.withIndex()) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth(1f / (4 - index))
                                ) {
                                    ShimmerImage(
                                        imageUrl = item.imageUrl,
                                        placeholderResId = R.drawable.image_not_available,
                                        modifier = Modifier.size(65.dp)
                                    )
                                    Text(text = item.name, fontSize = 11.sp)
                                }
                            }
                        }
                    }
//                    item {
//                        LazyVerticalGrid(
//                            modifier = Modifier.height(180.dp),
//                            columns = GridCells.Fixed(count = 4),
//                            contentPadding = PaddingValues(
//                                start = 10.dp,
//                                end = 10.dp,
//                                top = 4.dp,
//                                bottom = 16.dp
//                            ),
//                            content = {
//                                items(foodCategories.size) {
//                                    Column(
//                                        horizontalAlignment = Alignment.CenterHorizontally,
//                                    ) {
//                                        Image(
//                                            painter = rememberAsyncImagePainter(model = foodCategories[it].imageUrl),
//                                            contentDescription = null,
//                                            modifier = Modifier.size(65.dp)
//                                        )
//                                        Text(text = foodCategories[it].name, fontSize = 11.sp)
//                                    }
//
//                                }
//                            }
//                        )
//                    }
                    item {
                        Text(
                            text = "Promo Untukmu",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                        )
                        ImageSlider(images = images)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "There are 358 food rewards waiting.",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Button(
                                    modifier = Modifier.height(35.dp),
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColor()
                                ) {
                                    Text(text = "View", fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Order Again",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 2.dp)
                        )

                        FoodItemList(foodItems = apiFoodItems)

                        Text(
                            text = "Promo Sekitar Lokasimu",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                        )

                        ImageSlider(images = images)
                    }

                    item {
                        Text(
                            text = "Promo Terdekat",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 10.dp, top = 16.dp)
                        )
                    }
                    items(apiFoodItems) { data ->
                        ListFood(foodItem = data)
                    }

                }
            }
        }
    )
}

