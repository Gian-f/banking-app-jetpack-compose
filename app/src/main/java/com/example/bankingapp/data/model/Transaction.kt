package com.example.bankingapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class Transaction(
    val name: String,
    val description: String,
    val category: String,
    val type: TransactionType,
    val icon: ImageVector,
    val price: Double,
    val date: Date,
)
