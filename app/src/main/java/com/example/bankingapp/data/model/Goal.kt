package com.example.bankingapp.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class Goal(
    val name: String,
    val icon: ImageVector,
    val expectedDate: Date,
    val currentProgress: Number,
    val goalNumber: Number
)
