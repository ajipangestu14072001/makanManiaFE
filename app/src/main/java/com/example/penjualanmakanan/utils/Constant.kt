package com.example.penjualanmakanan.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.model.DeliveryOption
import com.example.penjualanmakanan.model.FoodCategory
import com.example.penjualanmakanan.model.FoodItem
import com.example.penjualanmakanan.model.MenuProfile
import com.example.penjualanmakanan.model.Notification

object Constant {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "dd94649ecd254b8ca7c26800a5cbb3b6"

    private val appIcons = Icons.Rounded

    private const val imageUrl =
        "https://static.vecteezy.com/system/resources/thumbnails/009/345/591/small/stir-fry-salad-with-sushi-and-shrimps-in-a-bowl-with-a-slate-background-top-view-copy-space-shrimp-and-vegetables-served-with-salad-chopsticks-with-asian-food-vector-free-png.png"

    val optionsList = arrayListOf(
        MenuProfile(
            icon = appIcons.Person,
            title = "Akun",
        ),
        MenuProfile(
            icon = appIcons.AddCircle,
            title = "Berkas Tambahan",
        ),
        MenuProfile(
            icon = appIcons.Edit,
            title = "Edit Password",
        ),
        MenuProfile(
            icon = appIcons.AddCircle,
            title = "Syarat dan Ketentuan",
        )

    )

    val foodCategories = listOf(
        FoodCategory(name = "Near me", imageUrl = imageUrl),
        FoodCategory(name = "Promo", imageUrl = imageUrl),
        FoodCategory(name = "Favorit", imageUrl = imageUrl),
        FoodCategory(name = "Coffee", imageUrl = imageUrl),
        FoodCategory(name = "Breakfast", imageUrl = imageUrl),
        FoodCategory(name = "Top Rated", imageUrl = imageUrl),
        FoodCategory(name = "Quick Tasty", imageUrl = imageUrl),
        FoodCategory(name = "All Cuisines", imageUrl = imageUrl)
    )

    val foodItems = listOf(
        FoodItem(
            name = "Beef wellington",
            imageUrl = "https://www.marionskitchen.com/wp-content/uploads/2021/12/Sichuan-and-Shiitake-Beef-Wellington-03.jpg",
            deliveryTime = 10,
            distance = 1.0,
            rating = 4.9f
        ),
        FoodItem(
            name = "Fresh Sushi",
            imageUrl = "https://kurio-img.kurioapps.com/21/11/09/aab25559-c3cd-4106-9f94-38400ad07e73.jpe",
            deliveryTime = 6,
            distance = 0.5,
            rating = 3.5f
        ),
        FoodItem(
            name = "Pizza Italia",
            imageUrl = "https://www.foodierate.com/uploads/fullsize/292/deal/63c13a772ffbeOeYoT-promo-pizza-hut-2021.jpg",
            deliveryTime = 30,
            distance = 2.5,
            rating = 4.5f
        ),
        FoodItem(
            name = "Nasi Padang",
            imageUrl = "https://www.foodierate.com/uploads/fullsize/15259/deal/610fc8090c197eWn4q-promo-padang-merdeka-2021.jpg",
            deliveryTime = 28,
            distance = 4.5,
            rating = 4.6f
        ),
        FoodItem(
            name = "Burger MCD",
            imageUrl = "https://www.richeesefactory.com/sites/default/media/library/combo-fb-chicken.jpg",
            deliveryTime = 20,
            distance = 1.5,
            rating = 4.2f
        ),
        FoodItem(
            name = "Martabak Manis",
            imageUrl = "https://nibble-images.b-cdn.net/nibble/original_images/194498647_853797338880001_7056004262933511072_n.jpg",
            deliveryTime = 25,
            distance = 1.0,
            rating = 5.0f
        ),
        FoodItem(
            name = "Pecel Budhe",
            imageUrl = "https://sweetrip.id/wp-content/uploads/2022/04/cobamakansini_238344707_3702502503307530_3458034177042839692_n.jpg",
            deliveryTime = 10,
            distance = 5.0,
            rating = 5.0f
        ),
    )

    val images = listOf(
        "https://www.eezly.com/wp-content/uploads/2022/07/All-You-Need-To-Know-About-Food-Delivery-Industry-1200x675.webp",
        "https://images01.nicepagecdn.com/page/52/78/website-template-preview-52785.jpg",
        "https://gadgetsquad.id/wp-content/uploads/2022/07/airasia-food-1.jpg",
        "https://mmc.tirto.id/image/otf/1024x535/2021/05/20/713b6f5a13396c07924b26ca3986482e_ratio-16x9.jpg",
        "https://i0.wp.com/pointsgeek.id/wp-content/uploads/2021/07/Screen-Shot-2021-07-26-at-10.17.33.png?fit=759%2C408&ssl=1",
    )

    val deliveryOptions = listOf(
        DeliveryOption(name = "Delivery", icon = R.drawable.round_delivery_dining_24),
        DeliveryOption(name = "Dine-In", icon = R.drawable.round_delivery_dining_24),
        DeliveryOption(name = "Self Pick-Up", icon = R.drawable.round_lunch_dining_24),
    )

    val notifications = listOf(
        Notification(
            title = "Pakai OVO buat bayar dimanapun",
            description = "Ada cashback s.d. 5rb menantimu!",
            time = "Sat",
            isUnread = true
        ),
        Notification(
            title = "Bosen belanja capek & ribet?",
            description = "GrabMart aja, untukmu ada diskon 50%",
            time = "12 Jul",
            isUnread = false
        ),
        Notification(
            title = "Minta beliin GrabJastip!",
            description = "Ada diskon s.d. 30rb pakai GrabJastip",
            time = "10:00 AM",
            isUnread = true
        ),
        Notification(
            title = "Diskon s.d. 50rb bisa cobain rasa baru",
            description = "Sponsored by Unilever",
            time = "21 Jul",
            isUnread = true
        ),

        Notification(
            title = "Buat belanja di Blibli!",
            description = "Cashback 100%, kirim pakai GrabExpress",
            time = "21 Jul",
            isUnread = true
        ),

        Notification(
            title = "Kangen nih jalan bareng kamu",
            description = "Jalan lagi yuk! Ada promo spesial",
            time = "20 Jul",
            isUnread = true
        ),

        Notification(
            title = "\uD83D\uDC49\uD83D\uDC48 Gapapa kok dibandingin",
            description = "Soalnya berani di adu ini termurah \uD83D\uDE0F",
            time = "Thu",
            isUnread = true
        ),

        Notification(
            title = "Sayang banget kamu kelewatan",
            description = "Diskon jalan & gratin ongkir \uD83D\uDC49",
            time = "Thu",
            isUnread = true
        ),
        Notification(
            title = "Kirim GrabGifts yuk!",
            description = "Nikmati voucher s.d. Rp25rb-nya",
            time = "Thu",
            isUnread = true
        ),
        Notification(
            title = "Jajan semaunya bebas pilih",
            description = "Selalu ada bebas ongkir di GrabFood",
            time = "19 Jul",
            isUnread = true
        ),
        Notification(
            title = "Ambil diskonya pakai blu",
            description = "Tambahkan bluVirtual \uD83D\uDCB3 sekarang",
            time = "19 Jul",
            isUnread = true
        ),

        Notification(
            title = "Cobain Menu Let's Go Box!",
            description = "Serbu diskon 50% & diskon lainnya!",
            time = "19 Jul",
            isUnread = true
        ),
    )
}