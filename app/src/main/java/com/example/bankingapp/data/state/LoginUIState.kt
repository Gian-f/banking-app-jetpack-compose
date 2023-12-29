package com.example.bankingapp.data.state

data class LoginUIState(
    var login: String = "",
    var password: String = "",

    var loginError: Boolean = false,
    var passwordError: Boolean = false,

    )
