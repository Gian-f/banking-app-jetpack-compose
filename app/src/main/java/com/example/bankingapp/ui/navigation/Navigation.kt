package com.example.bankingapp.ui.navigation

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Balance
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity

sealed class Screen(val route: String, val title: String, icon: ImageVector? = null) {
    data object Home : Screen("home", "Home", Icons.Rounded.Home)
    data object Wallet : Screen("wallet", "Carteira", Icons.Rounded.Wallet)
    data object Transactions : Screen("transactions", "Transações", Icons.Rounded.Balance)
    data object Settings : Screen("settings", "Configurações")
    data object Notifications : Screen("news", "Novidades", Icons.Rounded.Notifications)
    data object Profile : Screen("profile", "Perfil")
    data object Login : Screen("login", "Login")
    data object SignUp : Screen("signUp", "Registrar-se")
    data object TermsConditions : Screen("termsConditions", "Termos e condições")
}

private val LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }


private class ComposableBackNavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }
}


@Composable
internal fun ComposableHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit,
) {
    val dispatcher = (LocalBackPressedDispatcher.current ?: return).onBackPressedDispatcher

    val handler = remember { ComposableBackNavigationHandler(enabled) }

    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        onDispose { handler.remove() }
    }

    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
        handler.onBackPressed = onBackPressed
    }
}

@Composable
internal fun OnBackPress(onBackPressed: () -> Unit) {
    val context = LocalContext.current
    val fragmentActivity = context as? FragmentActivity

    if (fragmentActivity != null) {
        CompositionLocalProvider(
            LocalBackPressedDispatcher provides fragmentActivity
        ) {
            ComposableHandler {
                onBackPressed()
            }
        }
    } else {
       Log.e("", "onBackPressed error")
    }
}

