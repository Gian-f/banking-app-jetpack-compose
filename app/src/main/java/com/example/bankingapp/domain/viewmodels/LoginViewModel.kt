package com.example.bankingapp.domain.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bankingapp.data.state.LoginUIEvent
import com.example.bankingapp.data.state.LoginUIState
import com.example.bankingapp.domain.ValidationResult
import com.example.bankingapp.domain.Validator
import io.paperdb.Paper
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

    private val acceptedBiometry: Boolean? = Paper.book().read("acceptBiometric")
    val biometricAccepted = MutableStateFlow(acceptedBiometry)

    var loginError = mutableStateOf(false)
    private var errorMessage = mutableStateOf<String?>(null)
    private var isEmailFieldTouched = mutableStateOf(false)
    private var isPasswordFieldTouched = mutableStateOf(false)
    private var allValidationsPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)
    var loginUIState = mutableStateOf(
        LoginUIState(
            loginError = false,
            passwordError = false
        )
    )

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    login = event.email
                )
                isEmailFieldTouched.value = true
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
                isPasswordFieldTouched.value = true
            }

            is LoginUIEvent.DismissError -> {
                loginError.value = false
                errorMessage.value = null
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = if (isEmailFieldTouched.value && loginUIState.value.login.isNotEmpty()) {
            Validator.validateEmail(loginUIState.value.login)
        } else {
            ValidationResult(status = false)
        }

        val passwordResult =
            if (isPasswordFieldTouched.value && loginUIState.value.password.isNotEmpty()) {
                Validator.validatePassword(loginUIState.value.password)
            } else {
                ValidationResult(status = false)
            }

        loginUIState.value = loginUIState.value.copy(
            loginError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status == true && passwordResult.status == true
    }


    private fun login() {
        loginInProgress.value = true
        val email = loginUIState.value.login.trim()
        val password = loginUIState.value.password.trim()
    }
}