package com.example.bankingapp.data.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val photo: String,
    val phoneNumber: String,
    val password: String,
    val termsConditions: Boolean = false,
    val biometricAuth: Boolean = false,
)
