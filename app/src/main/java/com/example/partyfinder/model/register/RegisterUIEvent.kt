package com.example.partyfinder.model.register

sealed class RegisterUIEvent(){
    data class EmailChanged(val email: String) : RegisterUIEvent()
    data class PasswordChanged(val password: String) : RegisterUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterUIEvent()
    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : RegisterUIEvent()

    object RegisterButtonClicked : RegisterUIEvent()

}