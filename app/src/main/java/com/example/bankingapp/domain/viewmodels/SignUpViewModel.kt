package com.example.bankingapp.domain.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bankingapp.data.state.RegistrationUIState
import com.example.bankingapp.data.state.SignupUIEvent
import com.example.bankingapp.domain.Validator

class SignUpViewModel : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName


    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignupUIEvent.PhoneChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    phoneNumber = event.phone
                )
                printState()
            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val phoneResult = Validator.validatePhone(
            phone = registrationUIState.value.phoneNumber
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "phoneResult= $phoneResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            phoneError = phoneResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value = fNameResult.status &&
                emailResult.status &&
                passwordResult.status &&
                privacyPolicyResult.status &&
                phoneResult.status
    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }
}