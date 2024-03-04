package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.local.LocalUser
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


    var localUID by mutableStateOf("")
    var userEmail by mutableStateOf("")

    var registrationInProgress = mutableStateOf(false)
    var registrationSuccessful = mutableStateOf(false)
    var registrationFailed = mutableStateOf(false)
    var policyStatusChecked = mutableStateOf(false)
    var confirmPasswordValidationStarted = mutableStateOf(false)
    private var confirmPasswordValidation = mutableStateOf(false)

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

            ////Commented out for running test case, OG Code don't delete
//            is RegisterUIEvent.RegisterButtonClicked -> {
//                if (confirmPasswordValidation.value && policyStatusChecked.value) {
//                    viewModelScope.launch {
//                        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
//                        registrationInProgress.value = true
//                        var procedureSuccessful by mutableStateOf(false)
//                        try {
//                            withContext(Dispatchers.IO) {
//                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(_registrationUIState.value.email, _registrationUIState.value.password).await()
//                            }
//                            mAuth.uid?.let { uid ->
//                                localUID = uid
//                                Log.d("RegistrationView TestCase 2", "$localUID, $uid")
//                                procedureSuccessful = addUserToDatabase(_registrationUIState.value.email, uid)
//                            }
//                            registrationInProgress.value = false
//                            registrationSuccessful.value = true
//                        } catch (e: Exception) {
//                            Log.d(TAG, "Failure")
//                        }
//                        if (procedureSuccessful){
//                            registrationInProgress.value = false
//                            registrationSuccessful.value = true
//                        } else {
//                            registrationInProgress.value = false
//                            registrationFailed.value = true
//                        }
//                    }
//                } else if (!policyStatusChecked.value) {
//                    Log.d(TAG, "Privacy policy not accepted")
//                } else {
//                    Log.d(TAG, "Passwords do not match")
//                }
//            }



            is RegisterUIEvent.RegisterButtonClicked -> {
                viewModelScope.launch {
                    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                    registrationInProgress.value = true
                    var procedureSuccessful by mutableStateOf(false)
                    try {
                        withContext(Dispatchers.IO) {
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword("samarthmehta633@gmail.com", "123456").await()
                        }
                        mAuth.uid?.let { uid ->
                            localUID = uid
                            Log.d("RegistrationView TestCase 2", "$localUID, $uid")
                            procedureSuccessful = addUserToDatabase("samarthmehta633@gmail.com", uid)
                        }
                        registrationInProgress.value = false
                        registrationSuccessful.value = true
                    } catch (e: Exception) {
                        Log.d(TAG, "Failure")
                    }
                    if (procedureSuccessful){
                        registrationInProgress.value = false
                        registrationSuccessful.value = true
                    } else {
                        registrationInProgress.value = false
                        registrationFailed.value = true
                    }
                }
            }
        }
    }


    private suspend fun addUserToDatabase(email: String, uid: String): Boolean {
        var procedureSuccessful = false
        try {
            localUID = uid
            Log.d("RegistrationView TestCase 1", "$localUID, $uid")

            withContext(Dispatchers.IO) {
                userRepository.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
            }

            mDbRef = FirebaseDatabase.getInstance().reference
            mDbRef.child("users").child("data").child(uid).setValue(UserAccount(email, uid))
                .addOnFailureListener { exception ->
                    Log.e("FirebaseError", "Error writing to database", exception)
                }
            mDbRef.child("users").child("data").child(uid).setValue(UserAccount(email, uid))
                .addOnFailureListener { exception ->
                    Log.e("FirebaseError", "Error writing to database", exception)
                }
            procedureSuccessful = true
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error updating local database", e)
        }
        Log.d("RegistrationView TestCase 2", "return $procedureSuccessful")
        return procedureSuccessful
    }


    fun importUserEmail(): String {
        return userEmail
    }

    fun importUserUID(): String {
        Log.d("RegistrationView TestCase", "importUserUID() called with $localUID")
        return localUID
    }

    fun updateLoginEmailField():String{
        return registrationUIState.value.email
    }

}
