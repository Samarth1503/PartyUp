package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class LoginViewModel(val userUIDSharedViewModel : UserUIDSharedViewModel, private val userRepository: LocalUserRepository) : ViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState: StateFlow<LoginUIState> = _loginUIState.asStateFlow()

    var loginInProgress = mutableStateOf(false)

    var loginFailed = mutableStateOf(false)
    var loginIsSuccessful = mutableStateOf(false)


    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID : LiveData<String> get() = _currentUserUID

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userEmail = userRepository.getUserEmail()
            if (userEmail != null) {
                _loginUIState.update { currentState ->
                    currentState.copy(
                        email = userEmail
                    )
                }
            }
            else{
                _loginUIState.update { currentState ->
                    currentState.copy(
                        email = ""
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
                Log.d("LoginProcess TestCase", "ForgotPasswordComponent Clicked")
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
                Log.d("LoginProcessTestCase", "Inside_login_success")
                Log.d("LoginProcessTestCase", "${it.isSuccessful}")

                if(it.isSuccessful){
                    uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                    // Update the value of currentUserUID in shared ViewModel with the UID
                    _currentUserUID.postValue(uid)
                    Log.d("LoginProcessTestCase", "User UID: $uid")

                    viewModelScope.launch(Dispatchers.IO) {
                        Log.d("LoginProcessTestCase", "upsert() started")
                        userRepository.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
                        Log.d("LoginProcessTestCase", "upsert() ended")
                    }

                    loginIsSuccessful.value = true
                }
            }
            .addOnFailureListener {
                Log.d("LoginProcessTestCase", "Inside_login_failure")
                it.localizedMessage?.let { it1 -> Log.d("LoginProcessTestCase", it1) }

                loginInProgress.value = false
                loginFailed.value = true
            }
    }

    private fun sendPasswordReset() {
        val email = _loginUIState.value.email

        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("LoginProcessTestCase", "Email sent.")
                }
                else{
                    Log.d("LoginProcessTestCase", "Unable to send Email")
                }
            }
    }

    private fun printState(){
        Log.d("LoginProcessTestCase", "InsideStack")
        Log.d("LoginProcessTestCase", _loginUIState.value.toString())
    }
}