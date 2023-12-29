package com.example.bankingapp.ui.screens.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankingapp.R
import com.example.bankingapp.data.model.Card
import com.example.bankingapp.ui.theme.BlueEnd
import com.example.bankingapp.ui.theme.BlueStart
import com.example.bankingapp.ui.theme.GreenEnd
import com.example.bankingapp.ui.theme.GreenStart
import com.example.bankingapp.ui.theme.OrangeEnd
import com.example.bankingapp.ui.theme.OrangeStart
import com.example.bankingapp.ui.theme.PurpleEnd
import com.example.bankingapp.ui.theme.PurpleStart
import com.example.bankingapp.util.getGradient
import com.example.bankingapp.util.hideCreditCardNumber
import java.text.NumberFormat
import java.util.Locale

val cards = listOf(

    Card(
        cardType = "VISA",
        cardNumber = "3664 7865 3786 3976",
        cardName = "Business",
        balance = 456.467,
        color = getGradient(PurpleStart, PurpleEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "234 7583 7899 2223",
        cardName = "Savings",
        balance = 62.467,
        color = getGradient(BlueStart, BlueEnd),
    ),

    Card(
        cardType = "VISA",
        cardNumber = "0078 3467 3446 7899",
        cardName = "School",
        balance = 80.467,
        color = getGradient(OrangeStart, OrangeEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "3567 7865 3786 3976",
        cardName = "Trips",
        balance = 600.47,
        color = getGradient(GreenStart, GreenEnd),
    ),
)

@Preview
@Composable
fun CardsSection() {
    LazyRow {
        items(cards.size) { index ->
            CardItem(index)
        }
        item {
            Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(getGradient(OrangeStart, OrangeEnd))
                        .width(250.dp)
                        .height(160.dp)
                        .clickable { }
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Icon(
                        imageVector = Icons.Outlined.AddCard,
                        modifier = Modifier.size(44.dp),
                        tint = Color.White,
                        contentDescription = null
                    )

                    Text(
                        text = "Adicionar",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun CardItem(
    index: Int,
) {
    val card = cards[index]
    val format = remember { NumberFormat.getCurrencyInstance(Locale("pt", "BR")) }
    val balance = format.format(card.balance)


    var image = painterResource(id = R.drawable.ic_visa)
    if (card.cardType == "MASTER CARD") {
        image = painterResource(id = R.drawable.ic_mastercard)
    }

    Box(modifier = Modifier.padding(start = 16.dp)) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(250.dp)
                .height(160.dp)
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = image,
                contentDescription = card.cardName,
                modifier = Modifier.width(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.cardName,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = balance,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = hideCreditCardNumber(card.cardNumber),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}