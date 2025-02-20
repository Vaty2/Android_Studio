package com.mobilei.homework1

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mobilei.homework1.ui.theme.state_module.Product_App
import com.mobilei.homework1.ui.theme.Homework1Theme
import com.mobilei.homework1.ui.theme.ThemeViewModel
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var language: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable full-screen edge-to-edge layout

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            // Observe state
            val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)
            Homework1Theme(
                //                colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
                darkTheme = isDarkMode
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent(themeViewModel)
                }
            }
        }
    }
}

@Composable
fun AppContent(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Product_App(themeViewModel = themeViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val themeViewModel: ThemeViewModel = viewModel()

    Homework1Theme(
        darkTheme = false
    ) { Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Product_App(themeViewModel = themeViewModel)
    } }

}

@Composable
fun LanguageSwitcher() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val currentLocale = Locale.getDefault() // Current system locale

    // Set the current language to remember
    var selectedLanguage = remember { mutableStateOf(currentLocale.language) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display current language
        Text("Current Language: ${selectedLanguage.value.toUpperCase()}")

        // Button to change language
        Button(onClick = {
            val newLanguage = if (selectedLanguage.value == "en") "es" else "en" // Toggle between English and Spanish
            selectedLanguage.value = newLanguage
            setLocale(newLanguage, context)
        }) {
            Text("Switch Language")
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Show content based on the selected language
        Text(text = if (selectedLanguage.value == "en") "WELCOME" else "សូមស្វាគមន៍")
    }
}

fun setLocale(language: String, context: Context) {
    val locale = Locale(language)
    Locale.setDefault(locale)

    val config = Configuration()
    config.setLocale(locale)  // Update the locale
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

@Preview
@Composable
fun PreviewLanguageSwitcher() {
    LanguageSwitcher()
}


//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.lightColorScheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.rememberNavController
//import com.google.firebase.auth.FirebaseAuth
//import com.mobilei.homework1.Cart_Screen.CartViewModel
//import com.mobilei.homework1.state_module.Product_App
//import com.mobilei.homework1.ui.theme.Homework1Theme
//import com.mobilei.homework1.ui.theme.ThemeViewModel
////
////@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        enableEdgeToEdge()
//        setContent {
//
////            var isDark by remember {mutableStateOf(false)}
//            val themeViewModel: ThemeViewModel = viewModel()
//            val isDark = themeViewModel.isDarkMode.collectAsState().value
//
//            Homework1Theme(
////                darkTheme = isDark
//                colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
//            ) {
//                Surface(
//                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                ) {
//                    GreetingPreview()
//                }
//            }
//
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    val navController = rememberNavController()
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ){
//        Product_App()
////        InfoReceiver(navController)
////        paymentScreen(navController)
////        FoodScreen(navController)
////        PreviewFoodDetailScreen()
////        CartDetail(cartItems = foodList, navController,
////            item = FoodModel(),
////            onEdit = { },
////            onDelete = { }
////            )
////        Screen()
////        Screen()
////        SidebarMenu(isOpen = true, onClose = {})
////        EditProfile(navController)
////        OrderHistory(cartItems = foodList, navController,
////            item = FoodModel(),
////            onEdit = { },
////            onDelete = { })
//
////        MyAddressScreen()
////        AddNewAddressScreen()
////        PaymentScreen(navController = navController)
////        ProductDetailScreen(navController, item = productList[0])
//    }
//}

