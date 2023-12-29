package com.example.bankingapp.ui.screens.transactions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bankingapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = Screen.Transactions.title) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}