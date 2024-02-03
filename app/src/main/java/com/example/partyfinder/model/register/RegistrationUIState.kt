package com.example.partyfinder.model.register

data class RegistrationUIState(
    var name : String = "",
    var email : String = "",
    var password : String = "",
    var confirmPassword : String = ""
)