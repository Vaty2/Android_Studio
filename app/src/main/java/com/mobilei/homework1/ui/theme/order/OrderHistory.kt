package com.mobilei.homework1.ui.theme.order

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mobilei.homework1.R
import com.mobilei.homework1.product_module.FoodBottomAppBar
import com.mobilei.homework1.data.ProductModel
import com.mobilei.homework1.data.productList
import com.mobilei.homework1.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistory(
    cartItems: List<ProductModel> = productList,
    item: ProductModel,
    navController: NavController,
    onEdit: (ProductModel) -> Unit,
    onDelete: (ProductModel) -> Unit,
    themeViewModel: ThemeViewModel
) {
    val isDarkMode by themeViewModel.isDarkMode.observeAsState()
    val backgroundColor = if (isDarkMode ?: false) Color.Black else Color.White
    val textColor = if (isDarkMode ?: false) Color.White else Color.Black

    var selectedScreen by remember { mutableStateOf("home") }

    val tranproductorderhistory = stringResource(id = when(item.title){
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
        "StoreName" -> R.string.StoreName
        "order_history_title" -> R.string.order_history_title
        "reorder_button" -> R.string.reorder_button
        "survey_button" -> R.string.survey_button
        else -> R.string.total_price
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = tranproductorderhistory,
                        style = TextStyle(fontSize = 20.sp, color = textColor)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                    navigationIconContentColor = textColor
                ),
                navigationIcon = {
                    IconButton(
                        onClick =
                        {
//                            navController.navigate("FoodScreen"
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = textColor)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FilterAlt, contentDescription = "Filter", tint = textColor)
                    }
                }
            )
        },
        bottomBar = {
            FoodBottomAppBar(
                selectedScreen = selectedScreen,
                onScreenSelected = { screen -> selectedScreen = screen },
                navController = navController,
                themeViewModel = themeViewModel
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(cartItems) { item ->
                    CartHistoryItem(
                        item = item,
                        onEdit = { onEdit(item) },
                        onDelete = { onDelete(item) },
                        isDarkMode = isDarkMode ?: false,

                    )
                }
            }
        }
    }
}

@Composable
fun CartHistoryItem(
    item: ProductModel,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    isDarkMode: Boolean
) {
    val textColor = if (isDarkMode) Color.White else Color.Black
    val backgroundColor = if (isDarkMode) Color.DarkGray else Color.White

    val tranproductorderhistory = stringResource(id = when(item.title){
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
        "StoreName" -> R.string.StoreName
        "order_history_title" -> R.string.order_history_title
        "reorder_button" -> R.string.reorder_button
        "survey_button" -> R.string.survey_button
        else -> R.string.total_price
    })
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Store Name and Ordered Text
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = android.R.drawable.star_on),
                    contentDescription = "Store Icon",
                    tint = textColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.order_history_title), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = textColor)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = tranproductorderhistory, fontSize = 14.sp, color = textColor, fontWeight = FontWeight.SemiBold)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Product Image & Details
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = item.img,
                    contentDescription = item.title,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = tranproductorderhistory,
//                        item.title,
                        fontSize = 18.sp, color = textColor, fontWeight = FontWeight.SemiBold)
                    Text(
                        item.type, fontSize = 16.sp, color = textColor)
                }
            }

            // Order Info & Total
            Row(modifier = Modifier.padding(5.dp)) {
                Text(text = "13/02/2025 09:13", color = textColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = tranproductorderhistory, fontSize = 14.sp, color = textColor)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = tranproductorderhistory +
                        "$${item.price}", fontSize = 18.sp, color = textColor, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        containerColor = Color(0xFFFAFDFA)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.reorder_button),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.survey_button),
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}