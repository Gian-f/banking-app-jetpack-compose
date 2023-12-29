package com.example.bankingapp.ui.screens.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankingapp.ui.navigation.Screen

@Composable
fun UserSection(router: NavController) {
    val username = "Gian Felipe da Silva"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { router.navigate(Screen.Profile.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp),
                )
            }
            Spacer(modifier = Modifier.width(9.dp))
            Text(text = "Olá,\n$username")
            Spacer(modifier = Modifier.weight(1f))
            SearchBar()
            Spacer(modifier = Modifier.width(10.dp))
            NotificationsBar(router)
        }
    }
}

@Composable
private fun NotificationsBar(router: NavController) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { router.navigate(Screen.Notifications.route) }
            .padding(10.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Notifications,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun SearchBar() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { }
            .padding(10.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
