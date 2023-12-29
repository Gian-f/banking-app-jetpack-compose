package com.example.bankingapp.ui.screens.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankingapp.ui.screens.notification.sections.TopbarSection
import com.example.bankingapp.ui.widgets.ConfirmDialog
import com.example.bankingapp.util.formatDateAgo
import java.util.Date

@Composable
fun NotificationsScreen(navController: NavController) {
    val formattedDateAgo = formatDateAgo(Date())
    val openDialog = remember { mutableStateOf(false) }
    val items = (0..20).map { it.toString() }
    var notifications by remember { mutableStateOf(items) }
    Scaffold(
        topBar = {
            TopbarSection(navController, notifications, openDialog)
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = notifications
                items(count = list.size) { index ->
                    NotificationItem(index, formattedDateAgo)
                }
            }
        }
    )

    ConfirmDialog(
        onConfirm = {
            notifications = emptyList()
        },
        dialogState = openDialog,
        message = "Você deseja excluir todas as notificações?"
    )
}

@Composable
private fun NotificationItem(index: Int, formattedDateAgo: String) {
    Column(modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp)) {
        Text(
            text = "Oferta imperdível $index",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Olha só o que você está perdendo..."
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = formattedDateAgo,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            color = Color.LightGray
        )
    }
}