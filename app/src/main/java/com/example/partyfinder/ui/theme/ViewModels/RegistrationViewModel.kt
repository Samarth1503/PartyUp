package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.Register.RegisterUIEvent
import com.example.partyfinder.model.Register.RegistrationUIState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

public class RegistrationViewModel : ViewModel() {

    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    var registrationInProgress = mutableStateOf(false)

    var registrationSuccessful = mutableStateOf(false)

    var policyStatusChecked = mutableStateOf(false)

    var confirmPasswordValidationStarted = mutableStateOf(false)

    var confirmPasswordValidation = mutableStateOf(false)


    private var TAG = RegistrationViewModel::class.simpleName

    fun onEvent(event : RegisterUIEvent){
        when(event){
            is RegisterUIEvent.EmailChanged -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    email = event.email )}
                printState()
            }
            is RegisterUIEvent.PasswordChanged -> {
                _registrationUIState.update{ currentState -> currentState.copy(
                    password = event.password )}
                printState()
            }
            is RegisterUIEvent.ConfirmPasswordChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(
                    confirmPassword = event.confirmPassword
                ) }

                if (_registrationUIState.value.password.equals(_registrationUIState.value.confirmPassword)){
                    confirmPasswordValidation.value = true
                    confirmPasswordValidationStarted.value = false
                }
                else{
                    confirmPasswordValidationStarted.value = true
                }
            }
            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                policyStatusChecked.value = !policyStatusChecked.value
            }


        }
    }

    fun OnRegisterButtonClicked(navigateToHomeScreen: () -> Unit){
        if (confirmPasswordValidation.value && policyStatusChecked.value) {
            Log.d(TAG, "Inside **Register** Stack")
            Log.d(TAG, registrationUIState.value.toString())
            registerToFireBase(navigateToHomeScreen=navigateToHomeScreen)
        } else if (!policyStatusChecked.value){
            Log.d(TAG, "Privacy policy not accepted")
        } else {
            Log.d(TAG, "Passwords do not match")
        }
    }

    private fun registerToFireBase(
        navigateToHomeScreen: () -> Unit
    ){
        Log.d(TAG, "InsideRegister")

        createUserInFireBase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            navigateToHomeScreen = navigateToHomeScreen
        )
    }

    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun createUserInFireBase(
        email: String,
        password: String,
        navigateToHomeScreen:() -> Unit) {

        registrationInProgress.value = true

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                registrationInProgress.value = false
                if (it.isSuccessful) {
                    registrationSuccessful.value = true
                    navigateToHomeScreen()
                }
            }

            .addOnFailureListener{ Log.d(TAG, "Failure") }
    }
}