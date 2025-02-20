package com.mobilei.homework1.ui.theme.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilei.homework1.R

@Composable
fun SidebarMenu(isOpen: Boolean, onClose: () -> Unit) {
    val offsetX = animateDpAsState(targetValue = if (isOpen) 0.dp else (-250).dp, label = "")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isOpen) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable { onClose() }
            )
        }

        Column(
            modifier = Modifier
                .offset(x = offsetX.value)
                .fillMaxHeight()
                .width(250.dp)
                .background(Color(0xFFFF6F40), RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
                .padding(16.dp)
        ) {
            ProfileSection()
            Spacer(modifier = Modifier.height(16.dp))
            MenuItems(
                items = listOf(
                    "My Orders",
                    "My Profile",
                    "Delivery Address",
                    "Payment Methods",
                    "Contact Us",
                    "Settings",
                    "Help & FAQs"
                ),
                onItemClick = { /* Handle navigation */ }
            )
            Spacer(modifier = Modifier.weight(1f))
            LogoutButton()
        }
    }
}

@Composable
fun ProfileSection() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Steven Smiths", fontWeight = FontWeight.Bold, color = Color.White)
            Text("stevensmith@gmail.com", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun MenuItems(items: List<String>, onItemClick: (String) -> Unit) {
    items.forEach { item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(item, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun LogoutButton() {
    Button(
        onClick = { /* Handle logout */ },
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50)
    ) {
        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null, tint = Color(0xFFFF6F40))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Log Out", color = Color(0xFFFF6F40))
    }

}
