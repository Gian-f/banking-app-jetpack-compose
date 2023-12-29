package com.example.bankingapp.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

fun hideCreditCardNumber(creditCardNumber: String): String {
    if (creditCardNumber.length <= 4) {
        return creditCardNumber
    }
    return "**** **** **** " + creditCardNumber.substring(creditCardNumber.length - 4)
}

fun formatDateAgo(date: Date): String {
    val currentTime = System.currentTimeMillis()
    val diffMillis = currentTime - date.time
    val diffHours = TimeUnit.MILLISECONDS.toHours(diffMillis)

    return if (diffHours < 1) {
        "Agora mesmo"
    } else {
        "$diffHours horas atrás"
    }
}

fun Long?.toLongNotNull(): Long {
    return this ?: 0L
}

fun formatMoney(
    value: Double,
    format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR")),
): String {
    return format.format(value)
}

fun dateFormat(date: Date): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun isAlreadyShown(
    lastShown: State<Long?>,
): Boolean {
    return (System.currentTimeMillis() - lastShown.value.toLongNotNull()) < (24 * 60 * 60 * 1000)
}


fun bitmapToUri(context: Context, bitmap: Bitmap): Uri? {
    var uri: Uri? = null
    val bytes = ByteArrayOutputStream()

    try {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val imagePath: String = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            bitmap,
            "image",
            null
        )
        uri = Uri.parse(imagePath)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            bytes.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return uri
}
