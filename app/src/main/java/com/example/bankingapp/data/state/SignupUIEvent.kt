package com.example.bankingapp.data.state

sealed class SignupUIEvent {

    data class FirstNameChanged(val firstName: String) : SignupUIEvent()
    data class EmailChanged(val email: String) : SignupUIEvent()
    data class PhoneChanged(val phone: String) : SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()
    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignupUIEvent()
    data object RegisterButtonClicked : SignupUIEvent()
}