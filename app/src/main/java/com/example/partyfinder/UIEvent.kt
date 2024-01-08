package com.example.partyfinder

sealed class UIEvent(){
    data class NameChanged(val userName: String) : UIEvent()
    data class EmailChanged(val email: String) : UIEvent()
    data class PasswordChanged(val password: String) : UIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status:Boolean) : UIEvent()

    object RegisterButtonClicked : UIEvent()

}
