package com.example.bankingapp.ui.screens.home

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bankingapp.AuthActivity
import com.example.bankingapp.domain.viewmodels.HomeViewModel
import com.example.bankingapp.ui.navigation.OnBackPress
import com.example.bankingapp.ui.screens.home.sections.CardsSection
import com.example.bankingapp.ui.screens.home.sections.FinanceSection
import com.example.bankingapp.ui.screens.home.sections.GoalsSection
import com.example.bankingapp.ui.screens.home.sections.TransactionsSection
import com.example.bankingapp.ui.screens.home.sections.UserSection
import com.example.bankingapp.ui.screens.home.sections.WalletSection
import com.example.bankingapp.ui.widgets.BiometricBottomSheet
import com.example.bankingapp.ui.widgets.ConfirmDialog
import com.example.bankingapp.ui.widgets.ShowErrorSheet
import com.example.bankingapp.util.isAlreadyShown
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(true)
    val context = LocalContext.current as Activity
    val viewModel: HomeViewModel = hiltViewModel()
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    val errorSheetState = remember { mutableStateOf(false) }
    val biometricPreference = viewModel.biometricAccepted.collectAsState()
    val lastShown = viewModel.lastShown.collectAsState()
    val scope = rememberCoroutineScope()
    val exitDialog = remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                UserSection(navController)
            }
            item {
                WalletSection()
            }
            item {
                CardsSection()
            }
            item {
                GoalsSection(navController)
            }
            item {
                FinanceSection()
            }
            item {
                TransactionsSection(navController)
            }
        }
        if (biometricPreference.value == null && !isAlreadyShown(lastShown)) {
            viewModel.registerLastShown()
            BiometricBottomSheet(
                sheetState = sheetState,
                onAccept = {
                    scope.launch {
                        viewModel.acceptBiometricAuth()
                        sheetState.hide()
                        Toast.makeText(
                            context,
                            "A biometria foi ativada com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                        errorSheetState.value = false
                    }
                },
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        errorSheetState.value = true
                    }
                }
            )
        }
        if (errorSheetState.value) {
            ShowErrorSheet(
                message = "Permissões negadas podem resultar em funcionalidades indisponíveis.\uD83D\uDE14",
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            )
        }

        OnBackPress {
            exitDialog.value = true
        }

        ConfirmDialog(
            dialogState = exitDialog,
            message = "Tem certeza que deseja efetuar o logout?",
            onConfirm = {
                context.startActivity(Intent(context, AuthActivity::class.java))
                context.finish()
            }
        )
    }
}