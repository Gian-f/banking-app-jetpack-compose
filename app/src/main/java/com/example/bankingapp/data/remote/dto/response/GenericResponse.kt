package com.example.bankingapp.data.remote.dto.response

data class GenericResponse(
    val result: Any,
    val message: String,
    val status: Boolean
)
