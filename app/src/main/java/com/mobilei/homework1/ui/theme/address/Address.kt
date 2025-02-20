package com.mobilei.homework1.ui.theme.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mobilei.homework1.R

@Composable
fun MyAddressScreen(navController: NavController) {
    val addresses = listOf(
        AddressItem(
            stringResource(id = R.string.home),
            stringResource(id = R.string.home_address)
        ),
        AddressItem(
            stringResource(id = R.string.work),
            stringResource(id = R.string.work_address)
        ),
        AddressItem(
            stringResource(id = R.string.school),
            stringResource(id = R.string.school_address)
        ),
        AddressItem(
            stringResource(id = R.string.other),
            stringResource(id = R.string.other_address)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.my_address),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Map Section
            AddressMap()

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Address List
        items(addresses) { address ->
            AddressCard(address, navController)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

            // Add Address Button
            Button(
                onClick = { navController.navigate("AddNewAddressScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF202320), Color(0xFF202320))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.add_new_address),
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun AddressMap() {
    val singapore = LatLng(1.3521, 103.8198) // Example Location
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = singapore),
            title = stringResource(id = R.string.current_location),
            snippet = stringResource(id = R.string.current_location_desc)
        )
    }
}

@Composable
fun AddressCard(item: AddressItem, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("FoodScreen")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                contentDescription = stringResource(id = R.string.current_location),
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = item.address, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

data class AddressItem(val title: String, val address: String)
