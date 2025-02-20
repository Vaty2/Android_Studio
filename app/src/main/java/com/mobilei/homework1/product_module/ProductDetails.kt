package com.mobilei.homework1.product_module

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.AutoFixOff
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.mobilei.homework1.R
import com.mobilei.homework1.data.ProductModel
import com.mobilei.homework1.data.categoryList
import com.mobilei.homework1.data.productList
import com.mobilei.homework1.ui.theme.ThemeViewModel

@Preview(showSystemUi = true)
@Composable
fun PreviewProductDetailScreen(){

    val navController = rememberNavController()

    ProductDetailScreen(
        navController,
        productList[0],
        themeViewModel = viewModel()
    )
}

@Composable
fun ProductDetailScreen(
    navController: NavController,
    item: ProductModel,
    themeViewModel: ThemeViewModel
) {

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val tranproductdetail = stringResource(id = when(item.title){
        "ArmChairs" -> R.string.product_title_1
        "Cabinet" -> R.string.product_title_2
        "Sofa 1" -> R.string.product_title_3
        "Sofa 2" -> R.string.product_title_4
        "BookCase" -> R.string.product_title_5
        "Range Chair" -> R.string.product_title_6
        "Kendall Velvet Office Chair" -> R.string.product_title_7
        "Beauty Chairs" -> R.string.product_title_8
        "2 Seater Sofas" -> R.string.product_title_9
        "Moderns Chairs" -> R.string.product_title_10
        "Des" -> R.string.Des
        else -> R.string.product_title_1
    })
    val des = stringResource(id = when(item.title){

        "Des" -> R.string.Des
        else -> R.string.Des
    })

    key(isDarkMode) {
        val backgroundColor = if (isDarkMode) Color.Black else Color.White
        val textColor = if (isDarkMode) Color.White else Color.Black
        val topBarColor = if (isDarkMode) Color.DarkGray else Color.White
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            item {
                Box(modifier = Modifier.background(backgroundColor)) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) { // Use popBackStack()
                                Icon(
                                    Icons.Default.ArrowBackIosNew,
                                    contentDescription = "Back",
                                    tint = textColor
                                )
                            }
                            IconButton(onClick = { /* Handle favorite */ }) {
                                Icon(
                                    Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = textColor // Apply dark mode color
                                )
                            }
                        }

                        Row(modifier = Modifier.padding(20.dp)) {
                            AsyncImage(
                                model = item.img,
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .background(backgroundColor),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(modifier = Modifier.padding(10.dp)) {
                                AttributeChip(
                                    text = stringResource(id = R.string.size_75cm),
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowRightAlt,
                                            contentDescription = "Size",
                                            modifier = Modifier.size(16.dp),
                                            tint = textColor // Apply dark mode color
                                        )
                                    },
                                    isDarkMode = isDarkMode
                                )
                            }
                            Box(modifier = Modifier.padding(10.dp)) {
                                AttributeChip(
                                    text = stringResource(id = R.string.size_75cm),
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDownward,
                                            contentDescription = "Size",
                                            modifier = Modifier.size(16.dp),
                                            tint = textColor
                                        )
                                    },
                                    isDarkMode = isDarkMode
                                )
                            }
                            Box(modifier = Modifier.padding(10.dp)) {
                                AttributeChip(
                                    text = stringResource(id = R.string.Wool),
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.AutoFixOff,
                                            contentDescription = "Material",
                                            modifier = Modifier.size(16.dp),
                                            tint = textColor
                                        )
                                    },
                                    isDarkMode = isDarkMode
                                )
                            }
                            Box(modifier = Modifier.padding(10.dp)) {
                                AttributeChip(
                                    text = stringResource(id = R.string.wood),
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.CrueltyFree,
                                            contentDescription = "Material",
                                            modifier = Modifier.size(16.dp),
                                            tint = textColor
                                        )
                                    },
                                    isDarkMode = isDarkMode
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item(categoryList) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(backgroundColor)
                        .height(380.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
                    colors = CardDefaults.cardColors(
                        contentColor = textColor, // Apply dark mode text color
                        containerColor = backgroundColor // Apply dark mode background
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row() {

                            Text(
                                text = tranproductdetail,
//                            stringResource(id = R.string.product_title_1),
//                            stringResource(id = R.string.chairs),
                                fontSize = 20.sp,
                                color = textColor // Apply dark mode text color
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Circle,
                                    contentDescription = "Color Option 1",
                                    tint = textColor
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Circle,
                                    contentDescription = "Color Option 2",
                                    tint = textColor
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Default.Circle,
                                    contentDescription = "Color Option 3",
                                    tint = textColor
                                )
                            }
                        }

                        Text(
                            text = tranproductdetail,
//                        stringResource(id = R.string.chairs),
                            color = textColor,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = des,
//                        item.description,
                            fontSize = 20.sp,
                            color = textColor
                        )

                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "$${item.price}",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor // Apply dark mode text color
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                onClick = { /* Handle add to cart */ },
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = textColor, // Apply dark mode color
                                    contentColor = backgroundColor // Reverse for contrast
                                )
                            ) {
                                Text(
                                    stringResource(id = R.string.addtocart),
//                                "Add to cart",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AttributeChip(text: String, icon: @Composable (() -> Unit)? = null, isDarkMode: Boolean) {

    val chipBackgroundColor = if (isDarkMode) Color.DarkGray else Color(0xFFDBDEDE)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(chipBackgroundColor) // Apply dark mode color
            .padding(
                vertical = 25.dp, horizontal = 22.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,  // Align horizontally in the center
            verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between icon and text
        ) {
            // If an icon is provided, it will be displayed
            icon?.invoke()

            Text(
                text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkMode) Color.White else Color.Black // Apply text color
            )
        }
    }
}

