package com.mobilei.homework1.product_module

import CartProductModel
import addToCart
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsPower
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.mobilei.homework1.R
import com.mobilei.homework1.data.CategoryModel
import com.mobilei.homework1.data.ProductModel
import com.mobilei.homework1.data.categoryList
import com.mobilei.homework1.data.productList
import com.mobilei.homework1.ui.theme.ThemeViewModel
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun PreviewProductScreen(){
    val navController = rememberNavController()
    ProductScreen(navController, themeViewModel = viewModel())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel,
) {

    val welcometext = stringResource(id = R.string.welcome_message)

    var selectedScreen by remember { mutableStateOf("home") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val cartItems = remember { mutableStateListOf<ProductModel>() }

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.DarkGray else Color.White

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                navController = navController,
                isDarkMode = isDarkMode,
                themeViewModel = viewModel()) }
    ) {
        Scaffold(
            containerColor = backgroundColor,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (isDarkMode) Color(0xFF202020) else Color(0xFFFFFFFF)
                    ),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = if (isDarkMode) Color.White else Color.Black,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 8.dp)
                                .size(28.dp)
                                .clickable {
                                    scope.launch {
                                        if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                    }
                                }
                        )
                    },
                    title = {
                        // Using translated string for title
                        Text(
                            text = welcometext,
//                            text = "WELCOME", // Translated text for "WELCOME"
                            style = TextStyle(
                                color = if (isDarkMode) Color.White else Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                )
            },
            bottomBar = {
                FoodBottomAppBar(
                    selectedScreen = selectedScreen,
                    onScreenSelected = { screen -> selectedScreen = screen },
                    navController = navController,
                    themeViewModel
                )
            },
            floatingActionButton = {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CartButton(navController)
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { paddingValues ->
            HomeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                themeViewModel = themeViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    cartItems: List<ProductModel> = productList,
    modifier: Modifier = Modifier,
    navController: NavController,
    themeViewModel: ThemeViewModel
) {
    var search by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(categoryList.first()) }
    remember { mutableStateListOf<CartProductModel>() }
    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)
    val filteredProductList = productList

        .filter { product ->
            product.type.equals(selectedCategory.name, ignoreCase = true) // Filter by category name
        }
        .filter { product ->
            product.title.contains(search, ignoreCase = true) // Search filter
        }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Welcome Text
        item {
            Spacer(modifier = Modifier.padding(40.dp))
            Text(
                text = stringResource(id = R.string.find_text),
                fontSize = 30.sp,
                color = if (isDarkMode) Color.White else Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        // Search Bar
        item {
            Spacer(modifier = Modifier.height(10.dp))

            val textColor = if (isDarkMode) Color.White else Color.Black

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = {
                    Text(text = stringResource(id = R.string.search_label),
                        color = if(isDarkMode) Color.White else Color.Black)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = textColor,  // Label color when focused
                    unfocusedLabelColor = textColor, // Label color when unfocused
                    focusedBorderColor = textColor, // Border color when focused
                    unfocusedBorderColor = textColor, // Border color when unfocused
                    cursorColor = textColor // Cursor color
                ),
                trailingIcon = {
                    IconButton(
                        onClick = { /* Handle search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = textColor // Ensure icon color matches theme
                        )
                    }
                }
            )
        }

        // Categories Section
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(id = R.string.categories),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkMode) Color.White else Color.Black)
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }

        // Category Selection (Horizontal Row of Buttons)
        item {
            LazyRow {
                items(categoryList) { category ->
                    val isSelected = selectedCategory == category
                    val translatedName = stringResource(
                        id = when (category.name) {
                            "Chairs" -> R.string.chairs
                            "Sofas" -> R.string.sofa
                            "Cabinet" -> R.string.cabinet
                            "Dining Chairs" -> R.string.diningChairs
                            "Chest Of Drawers" -> R.string.chestOfDrawers
                            "Bed" -> R.string.bed
                            "Striped Pattern" -> R.string.stripedPattern
                            "Lams" -> R.string.Lams
                            else -> R.string.chairs// default fallback
                        }
                    )
                    Button(
                        onClick = { selectedCategory = category },
                        modifier = Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected)
                                Color(0xFFDBDEDE)
                            else
                                Color(0xFFEAEAEA)
                        )

                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(category.image),
                                contentDescription = category.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = translatedName,
                                color = Color.Black)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
        }

        // Display Filtered Products in Rows
        items(filteredProductList.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (product in rowItems) {
                    ProductItemCard(
                        productModel = product,
                        navController = navController,
                        modifier = Modifier.weight(1f),
                        category = CategoryModel()
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f)) // Empty space for odd rows
                }
            }
        }

        item {
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

//This is Drawer Slide
@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    isDarkMode: Boolean,
    themeViewModel: ThemeViewModel
) {
    val context = LocalContext.current

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black

    Column(
        modifier = modifier
            .width(300.dp)
            .fillMaxHeight()
            .background(backgroundColor)
            .padding(16.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
    ) {
        LazyColumn (
//            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Profile()

                    Spacer(modifier = Modifier.width(20.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.profile_name),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )

                        // Second line of Text
                        Text(
                            text = "bohn@gmail.com",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.weight(1f))
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("OrderHistory")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = stringResource(id = R.string.my_orders),
                            modifier = Modifier.size(40.dp),
                            tint = textColor
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.my_orders),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("EditProfile")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person2,
                            contentDescription = "My Profile",
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.my_profile),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("MyAddressScreen")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Delivery Address",
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.delivery_address),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {navController.navigate("paymentScreen")},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Payment,
                            contentDescription = stringResource(id = R.string.payment_method),
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.payment_method),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {navController.navigate("ContactUs")},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Contacts,
                            contentDescription = "About US",
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.contact_us),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Setting",
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.settings),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp)
                ) {
                    TextButton(
                        onClick = {},
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = textColor,
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Help,
                            contentDescription = "Help",
                            tint = textColor,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = stringResource(id = R.string.help_faqs),
                            style = TextStyle(
                                color = textColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp) // Adds spacing between the icon and the text
                                .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.darkmode),
                        style = TextStyle(
                            color = textColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { themeViewModel.toggleDarkMode() }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))
            }
            item {
                Button(
                    onClick = {
                        navController.navigate("Login")
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFFE1643A),
                        contentColor = textColor,
                        disabledContentColor = Color.Gray
                    )) {
                    Icon(
                        imageVector = Icons.Default.SettingsPower,
                        contentDescription = "logout",
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(id = R.string.logout),
                        style = TextStyle(
                            color = textColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(start = 8.dp) // Adds spacing between the icon and the text
                            .align(Alignment.CenterVertically) // Vertically centers the text with the icon
                    )
                }
            }

        }
    }
}

@Composable
fun CartButton(navController: NavController) {
    var count by remember { mutableStateOf(0) }

    Box(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    count +=1
                    navController.navigate("CartDetail")
                          },
                modifier = Modifier.size(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF202320)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCartCheckout,
                    contentDescription = "Add Shopping",
                )
            }
        }
    }
}

@Composable
fun FoodBottomAppBar(
    selectedScreen: String,
    onScreenSelected: (String) -> Unit,
    navController: NavController,
    themeViewModel: ThemeViewModel
    ){

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)
    val backgroundColor = if (isDarkMode) Color.Black else Color.White

    NavigationBar(
        containerColor = backgroundColor
    ) {
        NavigationBarItem(
            selected = selectedScreen == "home",
            onClick = { onScreenSelected("home") },
            icon = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selectedScreen == "home")
                                Color.Black else Color.LightGray,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedScreen == "home")
                            Color.White else Color(0xFF393B3B)
                    )
                }
            }
        )
        NavigationBarItem(
            selected = selectedScreen == "grid",
            onClick = { onScreenSelected("grid") },
            icon = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selectedScreen == "grid")
                                Color.Black else Color.LightGray, // Change color when selected
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.GridView,
                        contentDescription = "Grid",
                        tint = if (selectedScreen == "Grid")
                            Color.White else Color(0xFF393B3B)
                    )
                }
            }
        )
        NavigationBarItem(
            selected = selectedScreen == "shopping",
            onClick = {
                onScreenSelected("shopping")
                navController.navigate("CartDetail")
                      },
            icon = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selectedScreen == "Cart") Color.Black else Color.LightGray, // Change color when selected
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ){
                Icon(Icons.Default.AddShoppingCart,
                    contentDescription = "Shopping",
                    tint = if (selectedScreen == "Cart") Color.White else Color(0xFF393B3B)

                ) }}
        )
        NavigationBarItem(
            selected = selectedScreen == "profile",
            onClick = {
                onScreenSelected("profile")
                navController.navigate("EditProfile")
                      },
            icon = {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (selectedScreen == "Profile") Color.Black else Color.LightGray, // Change color when selected
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.PersonOutline,
                        contentDescription = "Profile",
                        tint = if (selectedScreen == "Profile") Color.White else Color(0xFF393B3B)
                    )
                }
            }
        )
    }
}

@Composable
fun Profile(){
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = "Profile",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, Color.LightGray, CircleShape)
            .background(Color.White),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductItemCard(
    productModel: ProductModel,
    modifier: Modifier = Modifier,
    navController: NavController,
    category: CategoryModel
) {

    val tranproductcategory = stringResource(id = when(productModel.title){
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
        else -> R.string.product_title_1
    })

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "Product_Detail/${productModel.id}")
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(productModel.img),
//                contentDescription = productModel.title,
                contentDescription = stringResource(id = R.string.chairs),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = tranproductcategory,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = productModel.rate.toString(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "$${productModel.price}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // 🛒 Button for Adding to Cart
            Button(
                onClick = { addToCart(product = CartProductModel(
                    id = productModel.id,
                    title = tranproductcategory,
                    price = productModel.price,
                    img = productModel.img,
                    type = productModel.type)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF202320), // Green color for add to cart
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.addtocart),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
            }

        }
    }
}

fun getProductListForCategory(category: CategoryModel): List<ProductModel> {
    return productList
}

