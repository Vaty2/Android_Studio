package com.mobilei.homework1.product_module

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mobilei.homework1.R
import com.mobilei.homework1.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUs(navController: NavController, themeViewModel: ThemeViewModel){

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.Black else Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                    text = stringResource(id = R.string.contact_us),
                    color = textColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarColor),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("FoodScreen") }) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = textColor)
                    }
                },
            )
        },
        containerColor = backgroundColor
    ) {
        paddingValues ->  Box(modifier = Modifier
            .padding(paddingValues)
        .background(backgroundColor),
            contentAlignment = Alignment.TopCenter){
            AboutUsScreen(navController, themeViewModel = viewModel())
    }
    }
}


@Composable
fun AboutUsScreen(navController: NavController, themeViewModel: ThemeViewModel) {

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.Black else Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(backgroundColor)

    ) {
        Text(
            text = stringResource(id = R.string.please),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        ContactItem(
            icon = Icons.Default.Email,
            title = stringResource(id = R.string.Emailus),
            content = "info@funiturekh.com",
            onClick = { openEmail("info@ten11kh.com") },
            iconTint = textColor
        )
        Spacer(modifier = Modifier.height(30.dp))
        Divider(modifier = Modifier.width(1.dp))

        ContactItem(
            icon = Icons.Default.Phone,
            title = stringResource(id = R.string.Phoneus),
            content = "(+855) 088 9999 888",
            onClick = { openPhone("+855889999888") },
            iconTint = textColor
        )
        Spacer(modifier = Modifier.height(30.dp))
        Divider(modifier = Modifier.width(1.dp))
        ContactItem(
            icon = Icons.Default.Send,
            title = stringResource(id = R.string.Telegram),
            content = "t.me/funiturecambodia_bot",
            onClick = { openTelegram("https://t.me/furniturecambodia_bot") },
            iconTint = textColor
        )
    }
}

@Composable
fun ContactItem(
    icon: ImageVector,
    title: String,
    content: String,
    onClick: @Composable () -> Unit,
    iconTint: Color = Color.White) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {onClick}
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title,
            modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = content, color = Color.Blue)
        }
    }
}

// Functions to handle actions
@Composable
fun openEmail(email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    }
    startActivity(LocalContext.current, intent, null)
}

@Composable
fun openPhone(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phone")
    }
    startActivity(LocalContext.current, intent, null)
}

@Composable
fun openTelegram(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(LocalContext.current, intent, null)
}
