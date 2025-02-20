package com.mobilei.homework1.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mobilei.homework1.R

@Composable
fun welcomeScreen(navController: NavController){


        Box (modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
        AsyncImage(
            model = R.drawable.hello,
            contentDescription = "image back ground",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        navController.navigate("Register")
                              },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

    }