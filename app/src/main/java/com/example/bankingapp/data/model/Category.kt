package com.example.bankingapp.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.ui.graphics.vector.ImageVector

enum class Category(val description: String, val imageVector: ImageVector) {
    COMIDA("Comida", Icons.Filled.Fastfood),
    COMPRAS("Compras", Icons.Filled.ShoppingBag),
    CASA("Casa", Icons.Filled.Home),
    TRANSPORTE("Transporte", Icons.Filled.DirectionsBus),
    VIAGEM("Viagem", Icons.Filled.AirplanemodeActive),
    ENTRETENIMENTO("Entretenimento", Icons.Filled.SportsEsports),
    INVESTIMENTO("Investimento", Icons.Filled.MonetizationOn),
    COMUNICACOES("Comunicações", Icons.Filled.Headphones),
}