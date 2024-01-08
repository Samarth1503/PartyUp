package com.example.partyfinder

data class RegistrationUIState(
    var userName : String = "",
    var name : String = "",
    var email : String = "",
    var password : String = "",
    var policyStatus : Boolean = false
)