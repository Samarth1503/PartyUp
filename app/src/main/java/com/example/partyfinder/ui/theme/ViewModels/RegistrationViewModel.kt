package com.example.partyfinder.ui.theme.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class RegistrationViewModel(private val context: Context) : ViewModel() {

    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    private val _registrationInProgress = MutableStateFlow(false)
    val registrationInProgress: StateFlow<Boolean> = _registrationInProgress.asStateFlow()

    private val _registrationSuccessful = MutableStateFlow(false)
    val registrationSuccessful: StateFlow<Boolean> = _registrationSuccessful.asStateFlow()

    private val _policyStatusChecked = MutableStateFlow(false)
    val policyStatusChecked: StateFlow<Boolean> = _policyStatusChecked.asStateFlow()

    private val _confirmPasswordValidationStarted = MutableStateFlow(false)
    val confirmPasswordValidationStarted: StateFlow<Boolean> = _confirmPasswordValidationStarted.asStateFlow()

    private val _confirmPasswordValidation = MutableStateFlow(false)
    val confirmPasswordValidation: StateFlow<Boolean> = _confirmPasswordValidation.asStateFlow()

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
                    _confirmPasswordValidation.value = true
                    _confirmPasswordValidationStarted.value = false
                } else {
                    _confirmPasswordValidationStarted.value = true
                }
            }
            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                _policyStatusChecked.value = !_policyStatusChecked.value
            }
            is RegisterUIEvent.RegisterButtonClicked -> {
                if (_confirmPasswordValidation.value && _policyStatusChecked.value) {
                    viewModelScope.launch {
                        registerToFireBase()
                    }
                } else if (!_policyStatusChecked.value) {
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
        _registrationInProgress.value = true
        try {
            withContext(Dispatchers.IO) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            }
            _registrationInProgress.value = false
            _registrationSuccessful.value = true
            mAuth.uid?.let { uid ->
                addUserToDatabase(email, uid)

                val sharedPreferences = context.getSharedPreferences("PartyUp", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("userEmail", email)
                editor.apply()
            }
        } catch (e: Exception) {
            Log.d(TAG, "Failure")
        }
    }

    private fun addUserToDatabase(email: String, uid: String) {
        val userAccount = UserAccount()

        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("users").child(uid).setValue(UserAccount(email, uid))
        userAccount.printData()
    }
}
