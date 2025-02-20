package com.mobilei.homework1.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobilei.homework1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    var cardholderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var saveCard by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    stringResource(id = R.string.payment_title),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        item {
            // Cardholder Name
            Text(
                text = stringResource(id = R.string.cardholder_name),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cardholderName,
                onValueChange = { cardholderName = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFE5DCCC),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFFECE4CE)
                )
            )
        }

        item { Spacer(modifier = Modifier.height(10.dp)) }

        item {
            // Card Number
            Text(
                text = stringResource(id = R.string.card_number),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFE5DCCC),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFFECE4CE)
                )
            )
        }

        item { Spacer(modifier = Modifier.height(10.dp)) }

        item {
            // Expiration & CVV Fields in a Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.expiration),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = expiration,
                        onValueChange = { expiration = it },
                        label = { Text(text = stringResource(id = R.string.mm_yy)) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFE5DCCC),
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = Color(0xFFECE4CE)
                        )
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.cvv),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cvv,
                        onValueChange = { cvv = it },
                        label = { Text(text = stringResource(id = R.string.cvv)) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFE5DCCC),
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = Color(0xFFECE4CE)
                        )
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.save_this_card),
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = saveCard,
                        onCheckedChange = { saveCard = it },  colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White, // Thumb color when checked
                            uncheckedThumbColor = Color.Black, // Thumb color when unchecked
                            checkedTrackColor = Color(0xFFDBDEDE), // Green track when checked
                            uncheckedTrackColor = Color.Black // Dark gray track when unchecked
                        ))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item { Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text =stringResource(id = R.string.card_safety_note)
            )
        } }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Column (modifier = Modifier.fillMaxWidth()) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text =stringResource(id = R.string.total_price),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "US \$6.59",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.shipping_fee),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "US \$6.59",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.promo_code),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = stringResource(id = R.string.enter_promo_code),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.grand_total),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "US \$6.59",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(180.dp))
        }
        item {
            Row {
                Text(
                    text = "US $7.61",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        navController.navigate("OrderConfirmationScreen")
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF202320))
                ) {
                    Text(
                        text = stringResource(id = R.string.checkout),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
