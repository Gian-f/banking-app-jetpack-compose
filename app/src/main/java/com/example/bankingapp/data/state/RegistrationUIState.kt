package com.example.bankingapp.data.state

data class RegistrationUIState(
    var firstName :String = "",
    var email  :String = "",
    var phoneNumber  :String = "",
    var password  :String = "",
    var privacyPolicyAccepted :Boolean = false,


    var firstNameError :Boolean = false,
    var emailError :Boolean = false,
    var phoneError :Boolean = false,
    var passwordError : Boolean = false,
    var privacyPolicyError:Boolean = false


)