package com.example.bankingapp.ui.screens.auth.login

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bankingapp.MainActivity
import com.example.bankingapp.R
import com.example.bankingapp.data.state.LoginUIEvent
import com.example.bankingapp.domain.viewmodels.LoginViewModel
import com.example.bankingapp.ui.navigation.Screen
import com.example.bankingapp.ui.widgets.ButtonComponent
import com.example.bankingapp.ui.widgets.ClickableLoginTextComponent
import com.example.bankingapp.ui.widgets.DividerTextComponent
import com.example.bankingapp.ui.widgets.HeadingTextComponent
import com.example.bankingapp.ui.widgets.MyTextFieldComponent
import com.example.bankingapp.ui.widgets.PasswordTextFieldComponent


@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current as Activity
    val executor = remember { ContextCompat.getMainExecutor(context) }
    val biometricPreference = loginViewModel.biometricAccepted.collectAsState()
    context.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    val intent = Intent(context, MainActivity::class.java)


    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                HeadingTextComponent(value = stringResource(id = R.string.login))

                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    Icons.Outlined.Email,
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    fieldName = "E-mail",
                    errorStatus = loginViewModel.loginUIState.value.loginError,
                )
                Spacer(modifier = Modifier.height(20.dp))
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    Icons.Outlined.EnhancedEncryption,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError,
                )

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                        context.startActivity(intent)
                        context.finish()
                    },
                    isLoading = loginViewModel.loginError.value || loginViewModel.loginInProgress.value,
                    isEnabled = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                Spacer(modifier = Modifier.height(20.dp))

                ClickableLoginTextComponent(
                    tryingToLogin = false,
                    onTextSelected = {
                        navController.navigate(Screen.SignUp.route)
                    }
                )
            }
        }
        val biometricPrompt = BiometricPrompt(
            context as FragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // handle authentication error here
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    context.startActivity(intent)
                    context.finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // handle authentication failure here
                }
            }
        )
        LaunchedEffect(biometricPreference.value) {
            if (biometricPreference.value !== null) {
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setAllowedAuthenticators(
                        BiometricManager.Authenticators.BIOMETRIC_STRONG
                                or
                                BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                    .setTitle("Autenticação Biométrica")
                    .setSubtitle("Entre usando sua biometria")
                    .build()
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }
}