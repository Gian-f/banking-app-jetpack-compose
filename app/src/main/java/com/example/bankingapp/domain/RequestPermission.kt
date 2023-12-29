package com.example.bankingapp.domain

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bankingapp.ui.widgets.ShowErrorSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun RequestPermission() {
    var isModalSheetVisible by remember { mutableStateOf(false) }
    val locationPermissions = arrayOf(
        Manifest.permission.USE_BIOMETRIC,
    )

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                acc && isPermissionGranted
            }
            if (!permissionsGranted) {
                isModalSheetVisible = true
            } else {
                // TESTE
            }
        }
    )
    if (isModalSheetVisible) {
        ShowErrorSheet(
            message = "Permissões negadas podem resultar em funcionalidades indisponíveis.\uD83D\uDE14",
            onDismiss = {
                isModalSheetVisible = false
            }
        )
    }
    DisposableEffect(Unit) {
        locationPermissionLauncher.launch(locationPermissions)
        onDispose { /* Cleanup, if needed */ }
    }
}