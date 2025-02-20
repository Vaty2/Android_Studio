package com.mobilei.homework1.ui.theme.LoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mobilei.homework1.R
import com.mobilei.homework1.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController, themeViewModel: ThemeViewModel) {
    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color(0xFFDBDEDE)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.DarkGray else Color.White
    Scaffold (
        topBar = { TopAppBar(
            title = {
                IconButton(onClick = {navController.navigate("welcome")}) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            ),
            actions = {
                TextButton(onClick = {}) {
                    Text( text = stringResource(id = R.string.help),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black

                        )
                    )
                }
            }
        )
        }
    ){
        Box(
            modifier = Modifier
                .padding(it)
                .background(Color.White)
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                Spacer(modifier = Modifier.padding(10.dp))
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColor
                    )
                ){
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column (
                        modifier = Modifier
                            .padding(25.dp)
                    ){
                        Text(text = stringResource(id = R.string.get),
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor

                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = stringResource(id = R.string.create),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = textColor
                            )
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Row (

                        ){
                            IconButton(onClick = {}) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://i.pinimg.com/736x/db/19/33/db19332a23a44f772c5a1855a8aabf70.jpg"),
                                    contentDescription = "Facebook",
                                    modifier = Modifier.size(150.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = {}) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://i.pinimg.com/736x/c8/b8/12/c8b8129127bada9fa699aeba388b3b2b.jpg"),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(150.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = {}) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://i.pinimg.com/736x/bb/62/af/bb62afe819b11af8384f2fb814acf28a.jpg"),
                                    contentDescription = "Twitter",
                                    modifier = Modifier.size(150.dp)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        SignUpSide(navController, themeViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpSide(navController: NavController, themeViewModel: ThemeViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf("") }
    var hidePassword by remember { mutableStateOf(true) }

    val isDarkMode by themeViewModel.isDarkMode.observeAsState(initial = false)

    val backgroundColor = if (isDarkMode) Color.Black else Color(0xFFDBDEDE)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val topBarColor = if (isDarkMode) Color.DarkGray else Color.White

    Box(
        modifier = Modifier.fillMaxWidth()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.enter_email)) },
                shape = CircleShape,
                placeholder = { "Enter Email" },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Key")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it},
                label = { Text(text = stringResource(id = R.string.enter_username)) },
                shape = CircleShape,
                placeholder = {"Enter userName"},
                leadingIcon = {
                    Icon(Icons.Default.Person,
                        contentDescription = "user")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )

            Spacer(modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.enter_password)) },
                shape = CircleShape,
                placeholder = { "Enter Password" },
                leadingIcon = {
                    Icon(Icons.Default.Password, contentDescription = "Key")
                },
                trailingIcon = {
                    IconButton(onClick = {
//                       if (!hidePassword == false){
//                           hidePassword = true
//                       }else{
//                           hidePassword = false
//                       }
                        hidePassword =!hidePassword
                    }) {
                        Icon(
                            if (hidePassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))
            Row( modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.reminder),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    )
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White, // Thumb color when checked
                        uncheckedThumbColor = Color.Red, // Thumb color when unchecked
                        checkedTrackColor = Color(0xFF2BD02B), // Track color when checked
                        uncheckedTrackColor = Color.DarkGray // Track color when unchecked
                    )
                )
            }

            Column(
                    modifier = Modifier
                    .fillMaxSize(),
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = {
                        if(email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()){
                            result = "SignUp Successfully"
                            navController.navigate("foodScreen")
                        }
                        else{
                            result = "SignUp Failed";
                        }
                    },

                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(0xFFEFEDED),
                            containerColor = Color(0xFF807C7C)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.register),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isDarkMode) Color.White else Color.Black
                            ))
                    }

                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Text(text = stringResource(id = R.string.already_have_account),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        ))
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        navController.navigate("Login")
                    }) {
                        Text(text = stringResource(id = R.string.register),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            ))
                    }
                }
            }
        }
    }
}


