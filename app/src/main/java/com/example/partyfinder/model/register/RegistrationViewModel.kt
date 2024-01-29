package com.example.partyfinder.model.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.UserAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel : ViewModel() {

    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    var registrationInProgress = mutableStateOf(false)
    var registrationSuccessful = mutableStateOf(false)
    var policyStatusChecked = mutableStateOf(false)
    var confirmPasswordValidationStarted = mutableStateOf(false)
    private var confirmPasswordValidation = mutableStateOf(false)

    // Database Variable
    val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app")
    private lateinit var mDbRef: DatabaseReference

    private var TAG = RegistrationViewModel::class.simpleName

    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.EmailChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(
                    email = event.email)
                }
                printState()
            }
            is RegisterUIEvent.PasswordChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(
                    password = event.password)
                }
                printState()
            }
            is RegisterUIEvent.ConfirmPasswordChanged -> {
                _registrationUIState.update { currentState -> currentState.copy(
                    confirmPassword = event.confirmPassword
                )}

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
                    Log.d(TAG, "Inside Register Stack")
                    Log.d(TAG, _registrationUIState.value.toString())
                    registerToFireBase()
                } else if (!policyStatusChecked.value) {
                    Log.d(TAG, "Privacy policy not accepted")
                } else {
                    Log.d(TAG, "Passwords do not match")
                }
            }
        }
    }

    private fun registerToFireBase() {
        Log.d(TAG, "Inside Register")

        createUserInFireBase(
            email = _registrationUIState.value.email,
            password = _registrationUIState.value.password
        )
    }

    private fun printState() {
        Log.d(TAG, "Inside Stack")
        Log.d(TAG, _registrationUIState.value.toString())
    }

    private fun createUserInFireBase(email: String, password: String) {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app")
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        registrationInProgress.value = true

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                registrationInProgress.value = false
                if (it.isSuccessful) {
                    registrationSuccessful.value = true
                    mAuth.uid?.let { it1 -> addUserToDatabase(email, it1) }
                }
            }

            .addOnFailureListener { Log.d(TAG, "Failure") }
    }

    private fun addUserToDatabase(email: String, uid: String) {
        val userAccount = UserAccount()

        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("users").child(uid).setValue(UserAccount(email, uid))
        userAccount.printData()
    }
}