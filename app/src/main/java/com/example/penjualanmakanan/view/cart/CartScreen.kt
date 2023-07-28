package com.example.penjualanmakanan.view.cart

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.navigation.Screen
import com.example.penjualanmakanan.repository.DataStoreRepository
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.ui.theme.thirdColor
import com.example.penjualanmakanan.utils.Chip
import com.example.penjualanmakanan.utils.Constant
import com.example.penjualanmakanan.utils.CustomCard
import com.example.penjualanmakanan.utils.InputBoxShape
import com.example.penjualanmakanan.utils.NoRippleTheme
import com.example.penjualanmakanan.utils.buttonColor
import com.example.penjualanmakanan.viewmodel.FoodViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController,
    foodViewModel: FoodViewModel = hiltViewModel()
) {
    val state by foodViewModel.state.collectAsState()
    val context = LocalContext.current
    val dataStore = DataStoreRepository(context)
    val token = dataStore.getToken.collectAsState(initial = "")
    val scope = rememberCoroutineScope()
    val items = listOf("7feb418a-f59d-4f95-b651-32c97c73a5a6", "b0687f4b-c161-4cfd-b430-9306b0c9eca8")
    val selectedOptionState = remember { mutableStateOf(Constant.deliveryOptions[0]) }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Ayam Geprek Gold Chick - Jatibening",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Delivery fee calculated at 08:47:45",
                                fontSize = 14.sp,
                            )

                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Rounded.KeyboardArrowLeft, contentDescription = "backIcon")
                        }
                    },
                    colors = topAppBarColors(
                        containerColor = primaryColor,
                        titleContentColor = Color.White
                    ),
                )
            },
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState())

                    ) {
                        LazyRow(
                            modifier = Modifier
                                .padding(all = 14.dp)
                                .fillMaxWidth()
                        ) {
                            items(Constant.deliveryOptions) { option ->
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

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 14.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.round_location_on_24),
                                    contentDescription = "Notification Icon",
                                    colorFilter = ColorFilter.tint(color = primaryColor),
                                    modifier = Modifier
                                        .size(size = 20.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .weight(weight = 1f)
                                ) {
                                    Text(
                                        text = "No.BL H/08 Jalan Hanjuang 2",
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 2.dp),
                                        color = Color.Gray,
                                        fontSize = 11.sp,
                                        text = "Jl. Hanjuang II No. BL H/08, Jatibening Baru, Pondokgede",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                                ) {
                                    Icon(
                                        modifier = Modifier.size(size = 15.dp),
                                        imageVector = Icons.Rounded.KeyboardArrowRight,
                                        contentDescription = null
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height = 50.dp)
                                    .padding(all = 10.dp),
                                shape = RoundedCornerShape(size = 6.dp),
                                colors = CardDefaults.cardColors(containerColor = thirdColor)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 12.dp),
                                        text = "Add address detail and delivery instructions",
                                        fontSize = 12.sp,
                                    )
                                    TextButton(
                                        contentPadding = PaddingValues(0.dp),
                                        onClick = { /*TODO*/ },
                                    ) {
                                        Text(text = "Edit", fontSize = 12.sp)
                                    }
                                }
                            }
                            Divider(color = thirdColor, thickness = 0.5.dp)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(size = 40.dp)
                                        .clip(shape = CircleShape)
                                        .background(color = thirdColor)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.round_rocket_launch_24),
                                        contentDescription = "Notification Icon",
                                        colorFilter = ColorFilter.tint(color = primaryColor),
                                        modifier = Modifier
                                            .size(size = 25.dp)
                                            .align(alignment = Alignment.Center)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .weight(weight = 1f)
                                ) {
                                    Text(
                                        text = "Delivery options",
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis,
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(top = 2.dp),
                                        color = Color.Gray,
                                        fontSize = 11.sp,
                                        text = "Distance from you: 1.3 km",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                }
                            }
                            Card(
                                shape = RoundedCornerShape(size = 6.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                border = BorderStroke(width = 0.5.dp, color = thirdColor)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp, vertical = 12.dp),
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Priority  ● < 25 Mins",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                        )
                                        Row {
                                            Text(
                                                text = "13.000 ",
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 12.sp,
                                                color = primaryColor
                                            )
                                            Text(
                                                text = "14.000",
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 12.sp,
                                                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                            )
                                        }
                                    }
                                    Text(
                                        text = "Delivered delivery to you with no stops in\nbetween",
                                        fontSize = 11.sp,
                                        color = Color.Gray,
                                        lineHeight = 14.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(height = 10.dp))
                            CustomCard(
                                text = "Standard ● 25 mins",
                                price = "11.000",
                                borderColor = primaryColor,
                                cornerRadius = 6.dp
                            )
                            Spacer(modifier = Modifier.height(height = 10.dp))
                            CustomCard(
                                text = "Saver ● 40 mins",
                                price = "8.000",
                                borderColor = thirdColor,
                                cornerRadius = 6.dp
                            )
                            Spacer(modifier = Modifier.height(height = 10.dp))
                            CustomCard(
                                text = "Order for later",
                                borderColor = thirdColor,
                                cornerRadius = 6.dp
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Order summary",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                TextButton(
                                    contentPadding = PaddingValues(0.dp),
                                    onClick = { /*TODO*/ },
                                ) {
                                    Text(text = "Add items", fontSize = 12.sp)
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "2x",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor,
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .weight(weight = 1f)
                                ) {
                                    Text(
                                        text = "Paket Ayam Geprek Sambal Matah",
                                        fontWeight = FontWeight.Bold,
                                        overflow = TextOverflow.Ellipsis,
                                        fontSize = 12.sp
                                    )

                                    Text(
                                        modifier = Modifier.padding(top = 7.dp),
                                        text = "Edit",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 12.sp
                                    )

                                }
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.align(alignment = Alignment.Top)
                                ) {
                                    Text(
                                        text = "13.000",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                    )
                                }
                            }

                            Divider(
                                color = thirdColor,
                                thickness = 0.5.dp,
                                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Subtotal",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "Rp49.000",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                )

                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Delivery fee",
                                    fontSize = 12.sp,
                                    color = primaryColor
                                )
                                Row {
                                    Text(
                                        text = "11.000",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp,
                                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                    )
                                    Text(
                                        text = " 5.000",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp,
                                        color = primaryColor
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Order fee",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "2.000",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                )

                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Restaurant packaging charge",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "2.000",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                )

                            }

                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(horizontal = 14.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "Total (incl. tax)", fontSize = 15.sp)
                            Column(
                                horizontalAlignment = Alignment.End,
                                modifier = Modifier.align(alignment = Alignment.CenterVertically)
                            ) {
                                Text(
                                    text = "Rp.58.980",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "Rp.64.980",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 10.sp,
                                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                )
                            }
                        }
                        Button(
                            onClick = {
                               scope.launch {
                                   foodViewModel.geyBuyFood(token = "Bearer ${token.value.toString()}", deliveryStatus = "In Progress", items = items)
                                   if (state.buyResult != null){
                                       Toast.makeText(context, state.buyResult?.message, Toast.LENGTH_SHORT).show()
                                       navController.navigate(route = Screen.Main.route)
                                   }
                               }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            colors = ButtonDefaults.buttonColor(),
                            shape = InputBoxShape.medium,
                            contentPadding = PaddingValues(vertical = 14.dp)
                        ) {
                            Text(text = "Place Order")
                        }
                    }

                }
            }
        )
    }
}