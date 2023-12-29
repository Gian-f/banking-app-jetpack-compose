package com.example.bankingapp.ui.screens.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankingapp.data.model.Transaction
import com.example.bankingapp.data.model.TransactionType
import com.example.bankingapp.ui.navigation.Screen
import com.example.bankingapp.util.formatDateAgo
import java.text.NumberFormat
import java.util.Date
import java.util.Locale

val transactions = listOf(
    Transaction(
        name = "Salário",
        description = "Salário fixo",
        category = "Trabalho",
        type = TransactionType.ENTRADA,
        icon = Icons.Filled.Fastfood,
        price = 687.0,
        date = Date()
    ),
    Transaction(
        name = "Ifood",
        description = "Fast Food",
        category = "Comida",
        type = TransactionType.SAIDA,
        icon = Icons.Filled.Fastfood,
        price = 200.0,
        date = Date()
    ),
    Transaction(
        name = "Academia",
        description = "Basquete",
        category = "Saúde",
        type =  TransactionType.SAIDA,
        icon = Icons.Filled.SportsBasketball,
        price = 80.0,
        date = Date()
    ),
    Transaction(
        name = "PS5",
        description = "Console",
        category = "Video Games",
        type =  TransactionType.SAIDA,
        icon = Icons.Filled.SportsEsports,
        price = 600.0,
        date = Date()

    ),
    Transaction(
        name = "Academia",
        description = "Musculação",
        category = "Saúde",
        type = TransactionType.SAIDA,
        icon = Icons.Filled.FitnessCenter,
        price = 100.0,
        date = Date()

    ),
    Transaction(
        name = "Taxa do lixo",
        description = "Imposto",
        category = "Gasto",
        type =  TransactionType.SAIDA,
        icon = Icons.Filled.CleaningServices,
        price = 100.0,
        date = Date()
    )
)

@Composable
fun TransactionsSection(navController: NavController) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Transações",
                fontSize = 24.sp,

                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            if (transactions.isNotEmpty()) {
                Text(
                    text = "Ver mais",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .clickable { navController.navigate(Screen.Transactions.route) }
                )
            }
        }
        if (transactions.isEmpty()) {
            EmptyTransactionState()
        } else {
            transactions.forEachIndexed { index, _ ->
                TransactionItem(index)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun TransactionItem(index: Int) {
    val transactions = transactions[index]
    val format = remember { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }
    val price = format.format(transactions.price)

    Box(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(85.dp)
                .clickable {}
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = transactions.icon, contentDescription = transactions.name)
            Spacer(modifier = Modifier.width(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = transactions.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(
                        text = transactions.description,
                        fontWeight = FontWeight.W300,
                        fontSize = 17.sp
                    )
                    Text(
                        text = transactions.category,
                        fontWeight = FontWeight.W300,
                        fontSize = 12.sp
                    )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    if(transactions.type == TransactionType.ENTRADA) {
                        Text(text = "+ $price")
                    } else {
                        Text(text = "- $price")
                    }
                    Text(text = formatDateAgo(transactions.date))
                }
            }
        }
    }
}

@Composable
fun EmptyTransactionState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountBalanceWallet,
            contentDescription = "",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Nenhuma transação foi encontrada!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 22.dp)
        )
    }
}