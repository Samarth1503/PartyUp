package com.example.partyfinder.model.uiEvent

sealed class RegisterUIEvent {
    data class EmailChanged(val email: String) : RegisterUIEvent()
    data class PasswordChanged(val password: String) : RegisterUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : RegisterUIEvent()

    data object RegisterButtonClicked : RegisterUIEvent()
}