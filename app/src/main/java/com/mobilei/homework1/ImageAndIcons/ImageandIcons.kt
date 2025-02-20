package com.mobilei.homework1.ImageAndIcons

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.size.Size


data class IconsModel(
    var id: Int = 0,
    var title: String = "no string",
    var iconUrl: String = "url",
    var size: Dp = 100.dp
    )

var iconList: List<IconsModel> = listOf(
    IconsModel(
        id = 1,
        title = "Google",
        iconUrl = "https://i.pinimg.com/736x/89/73/d4/8973d4473f428cb78cca39f82c15af3e.jpg",
        size =  100.dp
    ),
    IconsModel(
        id = 2,
        title = "Facebook",
        iconUrl = "https://i.pinimg.com/736x/4a/4c/22/4a4c224a0c6667178bebdfa3b6bdb92b.jpg",
        size =  100.dp
    ),
    IconsModel(
        id = 3,
        title = "Twitter",
        iconUrl = "https://i.pinimg.com/736x/91/8b/20/918b20dc0aa716e09fd0a58f9dd8e720.jpg",
        size =  100.dp
    ),

)