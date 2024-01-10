package com.example.partyfinder.Data.Register

data class RegistrationUIState(
    var userName : String = "",
    var name : String = "",
    var email : String = "",
    var password : String = "",
    var policyStatus : Boolean = true
)