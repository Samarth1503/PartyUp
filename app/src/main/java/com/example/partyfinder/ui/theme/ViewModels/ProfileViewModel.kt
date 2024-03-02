package com.example.partyfinder.ui.theme.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.UserViewModel
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.uiState.ProfileUiState
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Suppress("NAME_SHADOWING")
class ProfileViewModel(userViewModel: UserViewModel) : ViewModel() {

//    val userUID = "dtU0UnHSqgaM5BMsdsVqyQwaHW22"

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    // Database Variable
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app")
    private lateinit var mDbRef: DatabaseReference

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> get() = _snackbarMessage

    private var localUID: String = ""

    var selectedStatus by mutableStateOf(Pair(0,0))

    private var _gamerCallList = MutableLiveData<GamerCallsList>()
    val gamerCallList: LiveData<GamerCallsList> get() = _gamerCallList

    init {
        viewModelScope.launch {
            while(isActive){
                    backGroundGetGamerCall()
                    delay(10000000000000)
            }
        }
    }

    suspend fun backGroundGetGamerCall() {
        _gamerCallList.value = networkGamerCallsRepository.getGamerCalls().value
        val response = _gamerCallList.value
        if (response != null) {
            gamerCallList.observeForever { response ->
                _profileUiState.update { currentState -> currentState.copy(
                    UserGamerCalls = response
                ) }
            }
        }
    }

    fun onGamerIDChanged(gamerID: String) {
        _profileUiState.update { currentState -> currentState.copy(
            gamerID = gamerID
        )}
    }

    fun onBioChanged(gamerBio: String) {
        _profileUiState.update { currentState -> currentState.copy(
            bio = gamerBio
        )}
    }

    fun updateRank1(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank1GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank1GameRank = updatedGameRank
            )}
        }
    }
    fun updateRank2(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank2GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank2GameRank = updatedGameRank
            )}
        }
    }
    fun updateRank3(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank3GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank3GameRank = updatedGameRank
            )}
        }
    }

    fun onSaveChangesClicked(navigateToHomeScreen: () -> Unit) {
        if (localUID != "") {
            val db = FirebaseDatabase.getInstance().getReference("users")

            db.child("data").child(localUID).child("gameID").setValue(_profileUiState.value.gamerID)
            db.child("data").child(localUID).child("bio").setValue(_profileUiState.value.bio)
            db.child("data").child(localUID).child("rank1GameName").setValue(_profileUiState.value.rank1GameName)
            db.child("data").child(localUID).child("rank1GameRank").setValue(_profileUiState.value.rank1GameRank)
            db.child("data").child(localUID).child("rank2GameName").setValue(_profileUiState.value.rank2GameName)
            db.child("data").child(localUID).child("rank2GameRank").setValue(_profileUiState.value.rank2GameRank)
            db.child("data").child(localUID).child("rank3GameName").setValue(_profileUiState.value.rank3GameName)
            db.child("data").child(localUID).child("rank3GameRank").setValue(_profileUiState.value.rank3GameRank)
                .addOnSuccessListener { Log.d(TAG, "Realtime Database update successful!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating Realtime Database", e) }

            val editedProfileData = db.child("data").child(localUID).get()
            Log.d(TAG, editedProfileData.toString())
        } else {
            Log.d(TAG, "No local UID found")
        }
        navigateToHomeScreen()
    }


    fun updateStatus(changedStatus: Pair<Int, Int>) {
        _profileUiState.update { currentState -> currentState.copy(
            status = changedStatus
        )}
    }

    fun onChangeStatusClicked(isExpanded: Boolean, selectedStatus: Pair<Int, Int>) {
        if (isExpanded) {
            _profileUiState.update { currentState -> currentState.copy(
                status = selectedStatus,
                isChangeStatusExpanded = !isExpanded
            )}
        } else {
            _profileUiState.update { currentState -> currentState.copy(
                isChangeStatusExpanded = !isExpanded
            )}
        }
    }

    fun fetchUID(uid: String){
        localUID = uid
        Log.d("ProfileViewModel TestCase","fetchUID(), $localUID, $uid")
    }

    fun fetchData() {
        Log.d("ProfileViewModel TestCase","FetchData() called")
        val db = FirebaseDatabase.getInstance().getReference("users")
        db.child("data").child(localUID).get().addOnSuccessListener { dataSnapshot ->
            val profileData = dataSnapshot.getValue(ProfileUiState::class.java)
            if (profileData != null) {
                Log.d("ProfileVM FetchDataTrial TestCase", profileData.toString())
                runBlocking {
                    updatingFetchedData()
                }
            } else {
                Log.e("FetchDataTrial TestCase","No data available")
            }
        }.addOnFailureListener {
            Log.e("FetchDataTrial TestCase","Error fetching data")
        }
    }

    private suspend fun updatingFetchedData() {

//        val db = FirebaseDatabase.getInstance().getReference("users")
//
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(gamerID = db.child("data").child(localUID).child("gamerID").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase12", profileState.value.gamerID)
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(bio = db.child("data").child(localUID).child("bio").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank1GameName = db.child("data").child(localUID).child("rank1GameName").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank1GameRank = db.child("data").child(localUID).child("rank1GameRank").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank2GameName = db.child("data").child(localUID).child("rank2GameName").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank2GameRank = db.child("data").child(localUID).child("rank2GameRank").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank3GameName = db.c hild("data").child(localUID).child("rank3GameName").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
//        _profileUiState.update { currentState ->
//            val updatedState = currentState.copy(rank3GameRank = db.child("data").child(localUID).child("rank3GameRank").get().await().value.toString())
//            Log.d("UpdatingDataTrial TestCase", updatedState.toString())
//            updatedState
//        }
    }
}