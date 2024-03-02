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

////            OG Code, Don't delete, needs to be uncommented for the app to work
////            It has been commented just to run testCases easily
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


            //NEED TO ADD LOGCAT TO EACH STEP TPO CHECK WHICH STEP IS THE PROCESS STUCK IN
            is RegisterUIEvent.RegisterButtonClicked -> {
                    viewModelScope.launch {
//                        Log.d("RegistrationView TestCase", "RegisterUIEvent Started")
                        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                        registrationInProgress.value = true
                        var procedureSuccessful by mutableStateOf(false)
                        try {
                            Log.d("RegistrationView TestCase", "RegisterUIEvent try Started")
                            withContext(Dispatchers.IO) {
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword("sammehta063@gmail.com", "123456").await()
                                Log.d("RegistrationView TestCase", "RegisterUIEvent withContext ended")
                            }
                            mAuth.uid?.let { uid ->
                                Log.d("RegistrationView TestCase", "RegisterUIEvent mAuth.uid Started")
                                localUID = uid
                                Log.d("RegistrationView TestCase 2", "$localUID, $uid")
                                procedureSuccessful = addUserToDatabase("sammehta063@gmail.com", uid)
                                Log.d("RegistrationView TestCase", "RegisterUIEvent mAuth.uid ended")
                            }
                            registrationInProgress.value = false
                            registrationSuccessful.value = true
                            Log.d("RegistrationView TestCase", "RegisterUIEvent try ended")
                        } catch (e: Exception) {
                            Log.d(TAG, "Failure")
                        }
                        if (procedureSuccessful){
                            Log.d("RegistrationView TestCase", "RegisterUIEvent if started")
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
            Log.d("RegisterationView TestCase 1", "$localUID, $uid")

            withContext(Dispatchers.IO) {
                userRepository!!.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
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
        Log.d("RegisterationView TestCase 2", "return $procedureSuccessful")
        return procedureSuccessful
    }



//    private suspend fun addUserToDatabase(email: String, uid: String): Boolean {
//        var userAdded by mutableStateOf(false)
//        Log.d("RegistrationViewTestCase", "addUserToDatabase Started")
//        try {
//            Log.d("RegistrationViewTestCase", "try{} Started")
//            localUID = uid
//
//            mDbRef = FirebaseDatabase.getInstance().reference
//            val task = mDbRef.child("users").child("data").child(uid).setValue(UserAccount(email, uid))
//            Log.d("RegistrationViewTestCase", "task Started")
//
//            runBlocking {
//                Log.d("RegistrationViewTestCase", "runBlock Started")
//                task.await()
//                userAdded = task.isComplete && task.exception == null
//                if(userAdded){
//                    Log.d("RegistrationViewTestCase", "User-added Started")
//                    var repEmail: String
//                    if (userRepository != null){
//                        userRepository.upsert(LocalUser(id = 0, userEmail = email, userUID = uid))
//                        repEmail = userRepository.getUserEmail()
//                        Log.d("RegistrationViewTestCase", "Checking the values added to Local DB with the input : $email, DB value : $repEmail")
//                    }
//                }
//                Log.d("RegistrationViewTestCase", "User-added ended")
//            }
//            Log.d("RegistrationViewTestCase", "runBlock ended")
//        } catch(e: Exception) {
//            Log.e("DatabaseError", "Error updating local database", e)
//        }
//        Log.d("RegistrationViewTestCase", "return Triggered")
//        return userAdded
//    }




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
