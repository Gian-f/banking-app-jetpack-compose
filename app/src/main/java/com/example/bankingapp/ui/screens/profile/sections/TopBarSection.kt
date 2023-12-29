package com.example.bankingapp.ui.screens.profile.sections

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankingapp.ui.navigation.Screen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarSection(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = Screen.Profile.title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack(
                        route = Screen.Home.route,
                        inclusive = false,
                    )
                }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go back")
            }
        }
    )
}