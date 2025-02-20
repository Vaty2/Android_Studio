package com.mobilei.homework1.ui.theme.address

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mobilei.homework1.R

@Composable
fun AddNewAddressScreen(navController: NavController) {
    var title by remember { mutableStateOf("Current Address") }
    var address by remember { mutableStateOf("3646 S 58th Ave, Cicero, IL 60804, USA") }
    var useCurrentLocation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Map Section
        AddressMaps()

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = stringResource(id = R.string.add_new_address),
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title Input
            Text(stringResource(id = R.string.title_label),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Black)
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Address Input
            Text(stringResource(id = R.string.new_address_label),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Gray)
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Use Current Location Checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { useCurrentLocation = !useCurrentLocation }) {
                    Icon(
                        imageVector = if (useCurrentLocation) Icons.Default.RadioButtonChecked else Icons.Default.RadioButtonUnchecked,
                        contentDescription = "Use Current Location"
                    )
                }
                Text(text = stringResource(id = R.string.use_current_location),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                onClick = { navController.navigate("MyAddressScreen")},
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
                                colors = listOf(Color(0xFF202320),
                                    Color(0xFF202320))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {navController.navigate("MyAddressScreen")}) {
                    Text(text = stringResource(id = R.string.add_save),
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun AddressMaps() {
    val location = LatLng(41.8415, -87.7593) // Cicero, IL
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 14f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = location),
            title = "Selected Location",
            snippet = "3646 S 58th Ave, Cicero, IL 60804, USA"
        )
    }
}
