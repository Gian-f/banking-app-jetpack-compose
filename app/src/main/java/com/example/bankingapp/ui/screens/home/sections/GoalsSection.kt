package com.example.bankingapp.ui.screens.home.sections

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankingapp.data.model.Goal
import com.example.bankingapp.ui.widgets.PagerIndicator
import com.example.bankingapp.util.dateFormat
import com.example.bankingapp.util.formatMoney
import java.util.Date

val goals = listOf<Goal>(
    Goal(
        name = "Nova casa",
        icon = Icons.Filled.Home,
        currentProgress = 32000,
        goalNumber = 200000,
        expectedDate = Date()
    ),
    Goal(
        name = "Novo Celular",
        icon = Icons.Filled.PhoneAndroid,
        currentProgress = 1400,
        goalNumber = 2300,
        expectedDate = Date()
    ),
    Goal(
        name = "Novo Veículo",
        icon = Icons.Filled.DirectionsCar,
        currentProgress = 10000,
        goalNumber = 20000,
        expectedDate = Date()
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoalsSection(navController: NavController) {
    val horizontalState = rememberPagerState { goals.size }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Objetivos",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            if (goals.isNotEmpty()) {
                Text(
                    text = "Ver mais",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
            }
        }
        if (goals.isEmpty()) {
            EmptyGoalsState()
        } else {
            HorizontalPager(
                state = horizontalState,
                modifier = Modifier.fillMaxWidth(),
            ) {
                GoalItem(index = it)
            }
            PagerIndicator(goals, horizontalState)
        }
    }
}

@Composable
fun GoalItem(index: Int) {
    val goals = goals[index]
    val progressFraction by animateFloatAsState(
        targetValue = goals.currentProgress.toFloat() / goals.goalNumber.toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
    )
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { }
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = goals.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = goals.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Data prevista: " + dateFormat(goals.expectedDate),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box {
                LinearProgressIndicator(
                    progress = progressFraction,
                    modifier = Modifier
                        .height(22.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Text(
                    text = "${(progressFraction * 100).toInt()}%",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    fontSize = 14.sp,
                    text = "Salvo: ${formatMoney(goals.currentProgress.toDouble())}"
                )
                Text(
                    fontSize = 14.sp,
                    text = "Objetivo: ${formatMoney(goals.goalNumber.toDouble())}"
                )
            }
        }
    }
}

@Composable
private fun EmptyGoalsState() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { }
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Para adicionar uma meta, clique aqui!")
        }
    }
}