package com.mobilei.homework1.ui.theme.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import coil3.compose.AsyncImage
import com.mobilei.homework1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.edit_profile_title),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("FoodScreen")
                        },
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            EditProfileScreen()
        }
    }
}

@Composable
fun EditProfileScreen() {
    var name by remember { mutableStateOf("Bohn") }
    var email by remember { mutableStateOf("bohn@gmail.com") }
    var region by remember { mutableStateOf("Khmer") }
    var birthday by remember { mutableStateOf("20 - June - 2003") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = "https://via.placeholder.com/150",
                contentDescription = stringResource(id = R.string.profile_picture_placeholder),
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_edit),
                contentDescription = stringResource(id = R.string.edit_icon_description),
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Magenta, CircleShape)
                    .padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        ProfileTextField(label = stringResource(id = R.string.your_name), value = name) { name = it }
        Spacer(modifier = Modifier.height(10.dp))
        ProfileTextField(label = stringResource(id = R.string.email), value = email) { email = it }
        Spacer(modifier = Modifier.height(10.dp))
        ProfileTextField(label = stringResource(id = R.string.region), value = region) { region = it }
        Spacer(modifier = Modifier.height(10.dp))
        ProfileTextField(label = stringResource(id = R.string.your_birthday), value = birthday) { birthday = it }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF202320))
        ) {
            Text(
                text = stringResource(id = R.string.save),
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
    }
}
