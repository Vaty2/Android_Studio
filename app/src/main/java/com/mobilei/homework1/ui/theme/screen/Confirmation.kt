package com.mobilei.homework1.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobilei.homework1.R

@Composable
fun OrderConfirmationScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.confirmation_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
//                painter = painterResource(id = R.draw),
                imageVector = Icons.Default.CheckCircleOutline,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
//            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(24.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(id = R.string.your_order_number),
                    fontSize = 18.sp,
                    color = Color.Gray
                )
//                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = {}) {
                            Text(
                                text = "#1234123",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.order_confirmation_message),
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.thank_you_message),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {navController.navigate("FoodScreen")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF313434)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.continue_shopping),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}