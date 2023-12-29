package com.example.bankingapp.ui.screens.auth.signup

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.EnhancedEncryption
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankingapp.R
import com.example.bankingapp.data.state.SignupUIEvent
import com.example.bankingapp.domain.viewmodels.SignUpViewModel
import com.example.bankingapp.ui.navigation.Screen
import com.example.bankingapp.ui.widgets.ButtonComponent
import com.example.bankingapp.ui.widgets.CheckboxComponent
import com.example.bankingapp.ui.widgets.ClickableLoginTextComponent
import com.example.bankingapp.ui.widgets.DividerTextComponent
import com.example.bankingapp.ui.widgets.HeadingTextComponent
import com.example.bankingapp.ui.widgets.MyTextFieldComponent
import com.example.bankingapp.ui.widgets.NormalTextComponent
import com.example.bankingapp.ui.widgets.PasswordTextFieldComponent

@Composable
fun SignUpScreen(navController: NavController) {
    val signUpViewModel: SignUpViewModel = viewModel()
    val context = LocalContext.current as Activity
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            verticalArrangement = Arrangement.Top
        ) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                Icons.Outlined.Person,
                fieldName = "Primeiro nome",
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.firstNameError
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = Icons.Outlined.Email,
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                fieldName = "E-mail",
                errorStatus = signUpViewModel.registrationUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.phone),
                Icons.Outlined.PhoneAndroid,
                fieldName = "Telefone",
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.firstNameError
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = Icons.Outlined.EnhancedEncryption,
                onTextSelected = {
                    signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(20.dp))
            CheckboxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
//                        Router.navigateTo(Screen.TermsAndConditionsScreen)
                },
                onCheckedChange = {
                    signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(
                value = stringResource(id = R.string.register),
                isLoading = signUpViewModel.signUpInProgress.value,
                onButtonClicked = {
                    signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    navController.navigate(Screen.Home.route)
                },
                isEnabled = signUpViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(15.dp))

            DividerTextComponent()

            Spacer(modifier = Modifier.height(15.dp))

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                navController.navigate(Screen.Login.route)
            })
        }
    }
}