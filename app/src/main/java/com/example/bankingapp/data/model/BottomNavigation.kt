package com.example.bankingapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigation(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
