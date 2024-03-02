package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.model.local.LocalUser
import com.example.partyfinder.model.uiEvent.LoginUIEvent
import com.example.partyfinder.model.uiState.LoginUIState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: LocalUserRepository) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName


    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState: StateFlow<LoginUIState> = _loginUIState.asStateFlow()

    var loginInProgress = mutableStateOf(false)

    var loginFailed = mutableStateOf(false)
    var loginIsSuccessful = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userEmail = userRepository.getUserEmail()
            if (userEmail != "null") {
                _loginUIState.update { currentState ->
                    currentState.copy(
                        email = userEmail
                    )
                }
            }
        }
    }

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                _loginUIState.update{ currentState -> currentState.copy(
                    email = event.email)}
                printState()
            }

            is LoginUIEvent.PasswordChanged -> {
                _loginUIState.update{ currentState -> currentState.copy(
                    password = event.password)}
                printState()
            }

            is LoginUIEvent.ForgotPasswordClicked -> {
                sendPasswordReset()
                Log.d(TAG, "ForgotPasswordComponent Clicked")
            }

            is LoginUIEvent.LoginButtonCLicked -> {
                viewModelScope.launch {
                    login()
                }
            }
        }
    }

    private suspend fun login() {
        loginInProgress.value = true
        val email = _loginUIState.value.email
        val password = _loginUIState.value.password
        var uid = ""
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login_success")
                Log.d(TAG, "${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value = false
                    loginIsSuccessful.value = true
                    uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                    Log.d(TAG, "User UID: $uid")
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login_failure")
                it.localizedMessage?.let { it1 -> Log.d(TAG, it1) }

                loginInProgress.value = false
                loginFailed.value = true
            }
        if (loginIsSuccessful.value){
            viewModelScope.launch {
                userRepository.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
            }
        }
    }

////    It is to run testCases easily
//    private suspend fun login() {
//        loginInProgress.value = true
//        val email = "samarthmehta633@gmail.com"
//        val password = "123456"
//        var uid = ""
//        FirebaseAuth.getInstance()
//            .signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                Log.d(TAG, "Inside_login_success")
//                Log.d(TAG, "${it.isSuccessful}")
//
//                if(it.isSuccessful){
//                    loginInProgress.value = false
//                    loginIsSuccessful.value = true
//                    uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
//                    Log.d(TAG, "User UID: $uid")
//                }
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Inside_login_failure")
//                it.localizedMessage?.let { it1 -> Log.d(TAG, it1) }
//
//                loginInProgress.value = false
//                loginFailed.value = true
//            }
//        if (loginIsSuccessful.value){
//            viewModelScope.launch {
//                userRepository.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
//            }
//        }
//    }

    private fun sendPasswordReset() {
        val email = _loginUIState.value.email

        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
                else{
                    Log.d(TAG, "Unable to send Email")
                }
            }
    }

    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, _loginUIState.value.toString())
    }
}