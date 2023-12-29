package com.example.bankingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bankingapp.ui.screens.auth.login.LoginScreen
import com.example.bankingapp.ui.screens.auth.signup.SignUpScreen
import com.example.bankingapp.ui.screens.auth.terms_conditions.TermsConditionsScreen
import com.example.bankingapp.ui.screens.home.HomeScreen
import com.example.bankingapp.ui.screens.notification.NotificationsScreen
import com.example.bankingapp.ui.screens.profile.ProfileScreen
import com.example.bankingapp.ui.screens.transactions.TransactionScreen
import com.example.bankingapp.ui.screens.wallet.WalletScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Wallet.route) {
            WalletScreen()
        }
        composable(Screen.Transactions.route) {
            TransactionScreen()
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}

@Composable
fun AuthNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.TermsConditions.route) {
            TermsConditionsScreen()
        }
    }
}