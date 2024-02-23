package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.datasource.LocalUser
import com.example.partyfinder.datasource.LocalUserRepository
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.uiEvent.RegisterUIEvent
import com.example.partyfinder.model.uiState.RegistrationUIState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val userRepository: LocalUserRepository) : ViewModel() {

    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    var userEmail by mutableStateOf("null")

    var registrationInProgress = mutableStateOf(false)
    var registrationSuccessful = mutableStateOf(false)
    var policyStatusChecked = mutableStateOf(false)
    var confirmPasswordValidationStarted = mutableStateOf(false)
    private var confirmPasswordValidation = mutableStateOf(false)

    // Database Variable
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app")
    private lateinit var mDbRef: DatabaseReference

    private val TAG = RegistrationViewModel::class.simpleName

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.EmailChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(email = event.email) }
            }
            is RegisterUIEvent.PasswordChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(password = event.password) }
            }
            is RegisterUIEvent.ConfirmPasswordChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(confirmPassword = event.confirmPassword) }

                if (_registrationUIState.value.password == _registrationUIState.value.confirmPassword) {
                    confirmPasswordValidation.value = true
                    confirmPasswordValidationStarted.value = false
                } else {
                    confirmPasswordValidationStarted.value = true
                }
            }
            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                policyStatusChecked.value = !policyStatusChecked.value
            }
            is RegisterUIEvent.RegisterButtonClicked -> {
                if (confirmPasswordValidation.value && policyStatusChecked.value) {
                    viewModelScope.launch {
                        registerToFireBase()
                    }
                } else if (!policyStatusChecked.value) {
                    Log.d(TAG, "Privacy policy not accepted")
                } else {
                    Log.d(TAG, "Passwords do not match")
                }
            }
        }
    }

    private suspend fun registerToFireBase() {
        createUserInFireBase(
            email = _registrationUIState.value.email,
            password = _registrationUIState.value.password
        )
    }

    private suspend fun createUserInFireBase(email: String, password: String) {
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        registrationInProgress.value = true
        try {
            withContext(Dispatchers.IO) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            }
            registrationInProgress.value = false
            registrationSuccessful.value = true
            mAuth.uid?.let { uid ->
                addUserToDatabase(email, uid)
            }
        } catch (e: Exception) {
            Log.d(TAG, "Failure")
        }
    }

    private suspend fun addUserToDatabase(email: String, uid: String) {
        userRepository.upsert(LocalUser(id = 0, userEmail = email))
        Log.d("TestCase-Prompt", "addUserToDatabase() started")
        val retrievedUserEmail = userRepository.getUser()
        if (retrievedUserEmail != null) {
            userEmail = retrievedUserEmail.toString() // Update userEmail
            Log.d("AddUserUserDataTestCase", retrievedUserEmail.toString())
        } else {
            userEmail = "null"
            Log.d("AddUserUserDataTestCase", "No user email found")
        }
        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("users").child(uid).setValue(UserAccount(email, uid))
    }

    suspend fun getUserEmail(): String {
        var userEmailLocal = "null"
        withContext(Dispatchers.IO) {
            val retrievedUserEmail = userRepository.getUser()
            if (retrievedUserEmail != null) {
                userEmailLocal = retrievedUserEmail.toString()
            }
        }
        // Update userEmail on the main thread
        withContext(Dispatchers.Main) {
            userEmail = userEmailLocal
        }
        return userEmailLocal
    }


}
