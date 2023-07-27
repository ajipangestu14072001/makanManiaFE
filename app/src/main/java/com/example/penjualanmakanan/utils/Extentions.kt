package com.example.penjualanmakanan.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.DataSource
import coil.request.ImageRequest
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.model.Article
import com.example.penjualanmakanan.model.Border
import com.example.penjualanmakanan.model.FoodItem
import com.example.penjualanmakanan.model.MenuProfile
import com.example.penjualanmakanan.model.Notification
import com.example.penjualanmakanan.ui.theme.primaryColor
import com.example.penjualanmakanan.ui.theme.secondaryColor
import com.example.penjualanmakanan.ui.theme.thirdColor
import com.example.penjualanmakanan.viewmodel.EverythingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

val bottomBarHeight = 60.dp

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}

val InputBoxShape = Shapes(
    medium = RoundedCornerShape(14.dp)
)

fun Modifier.icon() = this.size(24.dp)


fun Modifier.buttonModifier() = this
    .fillMaxWidth()
    .padding(top = 30.dp)
    .padding(horizontal = 20.dp)


@Composable
fun ButtonDefaults.buttonColor() = this.buttonColors(
    containerColor = primaryColor,
    contentColor = White
)

fun Modifier.textFieldModifier() = this
    .fillMaxWidth()
    .padding(horizontal = 20.dp)
    .padding(top = 20.dp)


@Composable
fun TextFieldDefaults.textFieldColor() = colors(
    focusedContainerColor = thirdColor,
    unfocusedContainerColor = thirdColor,
    disabledContainerColor = thirdColor,
    focusedIndicatorColor = Transparent,
    unfocusedIndicatorColor = Transparent,
)

@Composable
fun DefaultSpacer() {
    Spacer(modifier = Modifier.width(6.dp))
    Spacer(
        modifier = Modifier
            .width(1.dp)
            .height(24.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(localDate: LocalDate): String {
    val dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id", "ID"))
    return localDate.format(dateFormat)
}


@Composable
fun Chip(
    deliveryOptions: String,
    selected: Boolean,
    onClick: () -> Unit,
    icon: Int
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .border(
                width = 1.dp,
                color = if (selected) Transparent else primaryColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(containerColor = if (selected) primaryColor else White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = if (selected) White else primaryColor,
                modifier = Modifier
                    .padding(start = 12.dp, end = 6.dp)
                    .size(size = 20.dp)
            )
            Text(
                modifier = Modifier.padding(end = 12.dp, top = 6.dp, bottom = 6.dp),
                text = deliveryOptions,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) White else primaryColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImageSlider(images: List<Any>) {
    var currentImageIndex by remember { mutableStateOf(0) }
    var isAnimating by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val stableCurrentImageIndex = rememberUpdatedState(currentImageIndex)
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
        ),
    ) {
        itemsIndexed(images) { index, image ->
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .clickable {
                        if (index != stableCurrentImageIndex.value && !isAnimating) {
                            isAnimating = true
                            coroutineScope.launch {
                                val delayMillis = 500L
                                delay(timeMillis = delayMillis / 2)
                                currentImageIndex = index
                                delay(delayMillis)
                                isAnimating = false
                            }
                        }
                    },
            ) {
                ShimmerImage(
                    imageUrl = image as String,
                    placeholderResId = R.drawable.image_not_available,
                    modifier = Modifier
                        .width(300.dp)
                        .height(150.dp)
                )
            }
        }
    }
}

@Composable
fun FoodItemList(foodItems: List<FoodItem>) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
        ),
    ) {
        items(foodItems) { foodItem ->
            FoodItemCard(foodItem)
        }
    }
}

@Composable
fun FoodItemCard(foodItem: FoodItem) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        ShimmerImage(
            imageUrl = foodItem.imageUrl,
            placeholderResId = R.drawable.image_not_available,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(text = foodItem.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "${foodItem.deliveryTime} mins |", fontSize = 11.sp)
            Text(text = " ${foodItem.distance} km | ", fontSize = 11.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(10.dp),
                    tint = primaryColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${foodItem.rating}", fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun OptionsItemStyle(item: MenuProfile) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = item.icon,
                contentDescription = item.title,
                tint = primaryColor
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 3f, fill = false)
                        .padding(start = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = item.title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = (0.8).sp,
                            color = Color.Gray
                        )
                    )

                }

                Icon(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false),
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = item.title,
                    tint = Color.Black.copy(alpha = 0.70f)
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    autoFocus: Boolean,
    viewModel: EverythingViewModel = hiltViewModel(),
    onSearch: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .height(54.dp)
    ) {
        var searchInput: String by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(key1 = searchInput) {
            if (viewModel.searchParam.value.trim().isNotEmpty() &&
                viewModel.searchParam.value.trim().length != viewModel.previousSearch.value.length
            ) {
                delay(750)
                onSearch()
                viewModel.previousSearch.value = searchInput.trim()
            }
        }

        TextField(
            value = searchInput,
            onValueChange = { newValue ->
                searchInput = if (newValue.trim().isNotEmpty()) newValue else ""
                viewModel.searchParam.value = searchInput
            },
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search...",
                    color = Color.Gray
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                disabledTextColor = Color.LightGray,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ), keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            ),
            trailingIcon = {
                LaunchedEffect(Unit) {
                    if (autoFocus) {
                        focusRequester.requestFocus()
                    }
                }
                Row {
                    AnimatedVisibility(visible = searchInput.trim().isNotEmpty()) {
                        IconButton(onClick = {
                            focusManager.clearFocus()
                            searchInput = ""
                            viewModel.searchParam.value = "Food"
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
fun ListFood(it: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Top
        ) {

            ShimmerImage(
                imageUrl = Constant.foodItems[it].imageUrl,
                placeholderResId = R.drawable.image_not_available,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = Constant.foodItems[it].name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${Constant.foodItems[it].deliveryTime} mins |",
                        fontSize = 11.sp
                    )
                    Text(
                        text = " ${Constant.foodItems[it].distance} km | ",
                        fontSize = 11.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(10.dp),
                            tint = primaryColor
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${Constant.foodItems[it].rating}", fontSize = 11.sp)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    lineHeight = 13.sp,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce consequat, arcu in malesuada scelerisque, lacus odio malesuada elit.",
                    fontSize = 11.sp
                )

            }
        }
    }
}

@Composable
fun ArticleItem(
    article: Article?,
) {
    article?.let {
        Column {
            Box(
                modifier = Modifier
                    .aspectRatio(16 / 9f)
                    .padding(vertical = 10.dp)
            ) {

                ShimmerImage(
                    imageUrl = article.urlToImage,
                    placeholderResId = R.drawable.image_not_available,
                    border = Border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(text = it.title, modifier = Modifier.fillMaxWidth(), maxLines = 4)
        }
    }
}

fun Modifier.shimmerBackground(
    targetValue: Float = 1000f,
    showShimmer: Boolean,
    shape: Shape = RoundedCornerShape(8.dp)
): Modifier = composed {

    val transition = rememberInfiniteTransition()
    lateinit var brush: Brush
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800), repeatMode = RepeatMode.Reverse
        )
    )
    brush = if (showShimmer) {
        linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation, y = translateAnimation)
        )
    } else {
        linearGradient(
            colors = listOf(Transparent, Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
    return@composed this.then(background(brush, shape))
}

@Composable
fun ShimmerImage(
    imageUrl: String? = null,
    placeholderResId: Int,
    border: Border? = null,
    modifier: Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = placeholderResId)
    )
    val showShimmer = remember { mutableStateOf(false) }
    showShimmer.value = painter.state is AsyncImagePainter.State.Loading

    val imageModifier = if (border != null) {
        modifier
            .shimmerBackground(showShimmer = showShimmer.value)
            .fillMaxWidth()
            .border(
                width = border.width,
                color = border.color,
                shape = border.shape ?: RoundedCornerShape(0.dp)
            )
            .clip(border.shape ?: RoundedCornerShape(0.dp))
    } else {
        modifier
            .shimmerBackground(showShimmer = showShimmer.value)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
}

@Composable
fun NotificationItem(
    notification: Notification
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape)
                .background(color = thirdColor)
        ) {
            Image(
                painter = painterResource(id = R.drawable.round_electric_bolt_24),
                contentDescription = "Notification Icon",
                colorFilter = ColorFilter.tint(color = primaryColor),
                modifier = Modifier
                    .size(size = 25.dp)
                    .align(alignment = Alignment.Center)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(weight = 1f)
            ) {
                Text(
                    color = primaryColor,
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(height = 6.dp))
                Text(
                    fontSize = 12.sp,
                    text = notification.description,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    color = primaryColor,
                    text = notification.time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(height = 6.dp))
                if (notification.isUnread) {
                    Box(
                        modifier = Modifier
                            .size(size = 8.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.Red)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationList(notifications: List<Notification>) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 10.dp
        )
    ) {
        items(items = notifications) { notification ->
            NotificationItem(notification = notification)
            Spacer(modifier = Modifier.height(height = 10.dp))
        }
    }
}

@Composable
fun CustomCard(
    text: String,
    price: String = "",
    containerColor: Color = Transparent,
    borderColor: Color = Color.Gray,
    cornerRadius: Dp = 6.dp,
    elevation: Dp = 0.dp
) {
    Card(
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        border = BorderStroke(width = 0.5.dp, color = borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            )
            Text(
                text = price,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
        }
    }
}









