package com.example.bankingapp.data.model

import java.util.Date

data class Budget(
    val name: String,
    val interval: String,
    val value: Double,
    val category: Category,
    val initialDate: Date,
    val finalDate: Date,
)
