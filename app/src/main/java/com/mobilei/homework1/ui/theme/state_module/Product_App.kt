package com.mobilei.homework1.ui.theme.state_module

import CartProductModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobilei.homework1.ui.theme.address.AddNewAddressScreen
import com.mobilei.homework1.ui.theme.cart.CartDetail
import com.mobilei.homework1.ui.theme.cart.CartViewModel
import com.mobilei.homework1.ui.theme.profile.EditProfile
import com.mobilei.homework1.ui.theme.address.MyAddressScreen
import com.mobilei.homework1.ui.theme.order.OrderHistory
import com.mobilei.homework1.ui.theme.LoginScreen.Login
import com.mobilei.homework1.ui.theme.LoginScreen.Register
import com.mobilei.homework1.product_module.CartButton
import com.mobilei.homework1.product_module.ContactUs
import com.mobilei.homework1.product_module.DrawerContent
import com.mobilei.homework1.product_module.ProductDetailScreen
import com.mobilei.homework1.data.ProductModel
import com.mobilei.homework1.product_module.ProductScreen
import com.mobilei.homework1.data.productList
import com.mobilei.homework1.ui.theme.screen.OrderConfirmationScreen
import com.mobilei.homework1.ui.theme.screen.PaymentScreen
import com.mobilei.homework1.ui.theme.ThemeViewModel
import com.mobilei.homework1.ui.theme.screen.WelcomeScreen

@Composable
fun Product_App(themeViewModel: ThemeViewModel){

        val navController = rememberNavController()
        val cartItems = remember { mutableListOf<CartProductModel>() }
        val cartViewModel: CartViewModel = viewModel()

        val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    NavHost(
            navController = navController,
            startDestination = "Welcome"
        ) {
            composable("welcome") {
                WelcomeScreen(navController = navController)
            }
            composable(
                "Product_Detail/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getInt("id")
                val selectedItem = productList.firstOrNull() { it.id == id }
                selectedItem?.let {
                    ProductDetailScreen(
                        navController,
                        item = it,
//                        cartItems = productList,
                        themeViewModel)
                }
            }

            composable("Register") { Register(navController, themeViewModel) }
            composable("Login") { Login(navController, themeViewModel) }
            composable("ProductScreen") { ProductScreen(navController, themeViewModel = viewModel()) }
            composable("paymentScreen") { PaymentScreen(navController) }
            composable("CartButton") { CartButton(navController) }
            composable("FoodScreen") { ProductScreen(navController, themeViewModel = viewModel()) }
            composable("EditProfile") { EditProfile(navController)  }
            composable("MyAddressScreen") { MyAddressScreen(navController) }
            composable("AddNewAddressScreen") { AddNewAddressScreen(navController)  }
            composable("paymentScreen") { PaymentScreen(navController) }
            composable("OrderConfirmationScreen") { OrderConfirmationScreen(navController) }
            composable("DrawerContent") {
            DrawerContent(navController = navController, isDarkMode = isDarkMode, modifier = Modifier, themeViewModel = viewModel())
        }
            composable("ContactUs") {ContactUs(navController,themeViewModel)}
            composable("OrderHistory") {
                OrderHistory(
                    navController = navController,
                    onEdit = {},
                    onDelete = {},
                    themeViewModel = viewModel(),
                    item = ProductModel()
                )
            }

            composable("ProductScreenDetail") {
                ProductDetailScreen(
                    navController,
                    item = ProductModel(),
                    themeViewModel
                )
            }
            composable("CartDetail") {
                CartDetail(
                    cartItems = cartItems,
                    navController = navController,
                    onEdit = {},
                    onDelete = {
                        cartViewModel.removeFromCart(it)
                    },
                    themeViewModel,
                    cartViewModel
                )
            }
            composable(
                "ProductScreenDetail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType }) // ✅ Corrected
            ) { backStackEntry ->
                val foodId = backStackEntry.arguments?.getInt("productId")
                val food = productList.firstOrNull { it.id == foodId }
                food?.let { ProductDetailScreen(navController,
                    ProductModel(),
                   themeViewModel
                ) }
            }
        }
}