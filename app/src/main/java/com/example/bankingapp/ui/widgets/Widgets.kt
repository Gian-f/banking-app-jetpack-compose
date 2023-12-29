package com.example.bankingapp.ui.widgets

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankingapp.R
import com.example.bankingapp.ui.theme.GrayColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextChanged: (String) -> Unit,
    fieldName: String,
    errorStatus: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    var textValue by rememberSaveable { mutableStateOf("") }

    val localFocusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp)),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp
        ),
        label = {
            Text(
                text = labelValue, color = MaterialTheme.colorScheme.onBackground
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = Color.Gray,
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(imageVector = painterResource, contentDescription = "")
        },
//        supportingText = {
//            Text(text = if (isFocused) if (!errorStatus) "Digite uma senha válida" else "" else "")
//        },
//        isError = isFocused && errorStatus,
        visualTransformation = VisualTransformation.None, // You may customize this based on your needs
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
    )
}


@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: ImageVector,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val localFocusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp))
            .onFocusChanged { isFocused = it.isFocused },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            disabledTextColor = Color.Gray,
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp
        ),
        label = {
            Text(
                text = labelValue, color = MaterialTheme.colorScheme.onBackground
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password,
        onValueChange = {
            password = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(imageVector = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
//        supportingText = {
//            Text(text = if (isFocused) if (!errorStatus) "Digite uma senha válida" else "" else "")
//        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//        isError = isFocused && errorStatus,
    )
}


@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "Ao continuar você aceita nossa "
    val privacyPolicyText = "Politica de privacidade"
    val andText = " e "
    val termsAndConditionsText = "Termos de uso"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground
        ), onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                        onTextSelected(span.item)
                    }
                }

        })
}

@Composable
fun ButtonComponent(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
    isLoading: Boolean = false,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(15.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onTertiary
                )
            } else {
                Text(
                    text = value,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground

        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText = if (tryingToLogin) "Já possui uma conta? " else "Não tem uma conta? "
    val loginText = if (tryingToLogin) "Entrar" else "Registre-se"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    list: List<Any>,
    pagerState: PagerState,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(list.size) { index ->
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        color = if (index == pagerState.currentPage)
                            MaterialTheme.colorScheme.primary else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                    .padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}
