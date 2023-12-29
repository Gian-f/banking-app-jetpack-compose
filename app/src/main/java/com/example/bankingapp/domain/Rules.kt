package com.example.bankingapp.domain

import android.util.Patterns


object Validator {


    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length >= 2)
        )

    }

    fun validatePhone(phone: String): ValidationResult {
        return ValidationResult(
            (!phone.isNullOrEmpty() && phone.length >= 2)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = Patterns.EMAIL_ADDRESS.toRegex()
        return ValidationResult(
            (email.isNotBlank() && (emailRegex.matches(email)))
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 6)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)