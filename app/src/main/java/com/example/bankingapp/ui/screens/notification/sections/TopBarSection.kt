package com.example.bankingapp.ui.screens.notification.sections

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.bankingapp.ui.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopbarSection(
    navController: NavController,
    notifications: List<String>,
    openDialog: MutableState<Boolean>,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(Screen.Notifications.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack(Screen.Home.route, false) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            if (notifications.isNotEmpty()) {
                IconButton(
                    onClick = {
                        openDialog.value = true
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete"
                        )
                    }
                )
            }
        }
    )
}