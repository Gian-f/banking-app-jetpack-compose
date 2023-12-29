package com.example.bankingapp.ui.screens.home.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WalletSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val eyeBtn = rememberSaveable { mutableStateOf(false) }
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Saldo",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                IconButton(
                    onClick = {
                        eyeBtn.value = !eyeBtn.value
                    }) {
                    Icon(
                        imageVector = if (eyeBtn.value) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = "see money"
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "R$ ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                if (eyeBtn.value) {
                    Text(
                        "44.000,00",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                } else {
                    Divider(
                        thickness = 8.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .width(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }
    }
}