package com.example.partyfinder.model.uiEvent

sealed class LoginUIEvent{

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    data object ForgotPasswordClicked : LoginUIEvent()

}