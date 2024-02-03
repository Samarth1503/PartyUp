package com.example.partyfinder.model.login

sealed class LoginUIEvent{

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    data object LoginButtonClicked : LoginUIEvent()
    data object ForgotPasswordClicked : LoginUIEvent()

}