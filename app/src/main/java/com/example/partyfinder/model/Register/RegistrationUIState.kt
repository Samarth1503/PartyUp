package com.example.partyfinder.model.Register

data class RegistrationUIState(
    var name : String = "",
    var email : String = "",
    var password : String = "",
    var confirmPassword : String = ""
)