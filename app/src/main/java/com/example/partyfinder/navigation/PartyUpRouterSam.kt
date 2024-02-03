package com.example.partyfinder.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screens {
    data object RegisterScreen : Screens()
    data object TermsAndConditionsScreen : Screens()
    data object LoginScreen : Screens()
    data object HomeScreen : Screens()
}

object PartyUpRouterSam{

    var currentScreen : MutableState<Screens> = mutableStateOf(Screens.RegisterScreen)

    fun navigateTo(destination : Screens){
        currentScreen.value = destination
    }
}