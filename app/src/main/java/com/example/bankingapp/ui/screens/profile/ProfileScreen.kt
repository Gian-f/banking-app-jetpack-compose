package com.example.bankingapp.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bankingapp.R
import com.example.bankingapp.ui.screens.profile.sections.TopBarSection
import com.example.bankingapp.ui.widgets.ButtonComponent
import com.example.bankingapp.ui.widgets.ModalBottomSheetWithVerticalActions
import com.example.bankingapp.ui.widgets.MyTextFieldComponent

@Composable
fun ProfileScreen(navController: NavController) {
    var isModalSheetVisible by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf("") }
    val isSaving by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBarSection(navController)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            onClick = { isModalSheetVisible = true },
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape,
                        ) {
                            if (imageUri != "") {
                                isModalSheetVisible = false
                                AsyncImage(
                                    model = imageUri,
                                    modifier = Modifier.size(96.dp),
                                    contentScale = ContentScale.FillBounds,
                                    contentDescription = null,
                                    alignment = Alignment.Center
                                )
                            } else {
                                Box(
                                    modifier = Modifier.size(96.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CameraAlt,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                        Text("Adicione uma Foto")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        Icons.Outlined.Person,
                        fieldName = "Nome Completo",
                        onTextChanged = {
//                                signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = false
                    )
                    Spacer(modifier = Modifier.height(0.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.phone),
                        Icons.Outlined.PhoneAndroid,
                        fieldName = "Telefone",
                        onTextChanged = {
//                                signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = false
                    )
                    Spacer(modifier = Modifier.height(0.dp))
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.email),
                        painterResource = Icons.Outlined.Email,
                        onTextChanged = {
//                                signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                        },
                        fieldName = "E-mail",
                        errorStatus = false
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    ButtonComponent(
                        value = stringResource(id = R.string.save),
                        isLoading = isSaving,
                        onButtonClicked = {
//                                signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                        },
                        isEnabled = false
                    )
                    if (isModalSheetVisible) {
                        ModalBottomSheetWithVerticalActions(
                            isVisible = true,
                            onDismiss = {
                                isModalSheetVisible = false
                            },
                            context = LocalContext.current,
                            onImageSelected = { uri ->
                                if (uri != null) {
                                    imageUri = uri.toString()
//                            homeViewModel.localUserPhoto.value = uri
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}