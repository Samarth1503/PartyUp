package com.example.partyfinder.Navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screens(){
    object RegisterScreen : Screens()
    object TermsAndConditionsScreen : Screens()

//    object LoginScreen : Screen()
//    object HomeScreen : Screen()
}

object PartyUpRouterSam{

    var currentScreen : MutableState<Screens> = mutableStateOf(Screens.RegisterScreen)

    fun navigateTo(destination : Screens){
        currentScreen.value = destination
    }
}