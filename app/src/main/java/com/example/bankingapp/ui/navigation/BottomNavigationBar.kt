package com.example.bankingapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bankingapp.data.model.BottomNavigation

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val items = listOf(
            BottomNavigation(
                Screen.Home.title,
                Screen.Home.route,
                Icons.Filled.Home,
                Icons.Outlined.Home
            ),
            BottomNavigation(
                Screen.Transactions.title,
                Screen.Transactions.route,
                Icons.Filled.AccountBalance,
                Icons.Outlined.AccountBalance
            ),
            BottomNavigation(
                Screen.Wallet.title,
                Screen.Wallet.route,
                Icons.Filled.AccountBalanceWallet,
                Icons.Outlined.AccountBalanceWallet
            ),
            BottomNavigation(
                Screen.Profile.title,
                Screen.Profile.route,
                Icons.Filled.Person,
                Icons.Outlined.Person
            ),
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    if (currentRoute == item.route) {
                        Icon(item.selectedIcon, contentDescription = null)
                    } else {
                        Icon(item.unselectedIcon, contentDescription = null)
                    }
                },
                label = { Text(item.title) }
            )
        }
    }
}
