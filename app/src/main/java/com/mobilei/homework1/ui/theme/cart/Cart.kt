package com.mobilei.homework1.ui.theme.cart

import CartProductModel
import addToCart
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import cartList
import coil3.compose.AsyncImage
import com.mobilei.homework1.R
import com.mobilei.homework1.ui.theme.ThemeViewModel
import kotlin.math.roundToInt

// ✅ CartDetail Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDetail(
    cartItems: List<CartProductModel>,
    navController: NavController,
    onEdit: (CartProductModel) -> Unit,
    onDelete: (CartProductModel) -> Unit,
    themeViewModel: ThemeViewModel,
    cartViewModel: CartViewModel
) {
    var cartItems by remember { mutableStateOf(cartList) }

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.DarkGray else Color.White

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text(
                    text = stringResource(id = R.string.cart),
                    color = textColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("FoodScreen") }) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = textColor)
                    }
                },
            )
        },
        bottomBar = {
            TotalSide(
                navController = navController,
                cartItems = cartItems,
                onQuantityChange = { item, newQuantity ->
                    cartItems = cartItems.map {
                        if (it.id == item.id) it.copy(quantity = newQuantity) else it
                    }
                },
                isDark = isDarkMode,
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(cartItems) { item ->
                    CartItemView(
                        item = item,
                        navController = navController,
                        onQuantityChange = { newQuantity ->
                            cartItems = cartItems.map {
                                if (it.id == item.id) it.copy(quantity = newQuantity) else it
                            }
                        },
                        onAddToCart = { addToCart(item) },
                        onEdit = { onEdit(item) },
                        onDelete = { onDelete(item) },
                        themeViewModel = themeViewModel
//                        onChangeTheme = onChangeTheme
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CartItemView(
    item: CartProductModel,
    navController: NavController,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onQuantityChange: (Int) -> Unit,
    onAddToCart: (CartProductModel) -> Unit,
    themeViewModel: ThemeViewModel
) {
    var quantity by remember { mutableStateOf(item.quantity) }
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val anchors = mapOf(0f to 0, -250f to 1)

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.DarkGray else Color.White
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
    ) {
        // Background Actions (Delete & Edit Buttons)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(end = 15.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red)
            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = onEdit,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Blue)
            }
        }

        // Foreground (Main Item Card)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color(0xFFDBDEDE),
                containerColor = Color(0xFFDBDEDE)
//                Color(0xFFDBDEDE)
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    Text(item.title, fontSize = 20.sp,
                        color = if(isDarkMode) Color.Black else Color.White,
//                        Color.Black,
                        fontWeight = FontWeight.SemiBold)
                    Text(item.type, fontSize = 16.sp,
                        color = if(isDarkMode) Color.Black else Color.White,
//                        Color(0xFF565D56),
                        fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
            Row (modifier = Modifier.fillMaxWidth()) {
                QuantitySelector(
                    product = item,
                    onQuantityChange = { _, newQuantity ->
                        quantity = newQuantity
                        onQuantityChange(newQuantity)
                    }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}



// ✅ Total Side Composable
@Composable
fun TotalSide(
    navController: NavController,
    cartItems: List<CartProductModel>,
    onQuantityChange: (CartProductModel, Int) -> Unit,
    isDark: Boolean
) {
    val totalPrice = remember(cartItems) {
        cartItems.sumOf { it.price * it.quantity }
    }

    val cardColor = if (isDark) Color(0xFF202020) else Color(0xFF676C72)
    val textColor = if (isDark) Color.White else Color.Black

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
//            containerColor = Color(0xFF676C72)
            containerColor = cardColor
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.total_price) +
                        " $${"%.2f".format(totalPrice)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Button(
                onClick = { navController.navigate("PaymentScreen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDark) Color.Gray else Color(0xFF202320))
//                    Color(0xFF202320))
            ) {
                Text(
                    text = stringResource(id = R.string.checkout_button),
                    color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

// ✅ Quantity Selector
@Composable
fun QuantitySelector(
    product: CartProductModel,
    onQuantityChange: (CartProductModel, Int) -> Unit
) {
    var quantity by remember { mutableStateOf(product.quantity) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Card (
            modifier = Modifier
                .width(100.dp)
                .height(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFDBDEDE)
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (quantity > 1) {
                            quantity--
                            onQuantityChange(product,quantity) // Update quantity
                        }
                    }) {
                    Text(
                        "-",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color(0xFF202320),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(
                    text = quantity.toString(),
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color(0xFF202320),
                        fontWeight = FontWeight.Bold
                    )
                )

                IconButton(onClick = {
                    quantity++
                    onQuantityChange(product,quantity) // Update quantity
                }) {
                    Text(
                        "+",
                        style = TextStyle(
                            fontSize = 17.sp,
                            color = Color(0xFF202320),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

