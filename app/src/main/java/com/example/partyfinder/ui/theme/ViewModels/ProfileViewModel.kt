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
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.uiState.FirebaseProfileUiState
import com.example.partyfinder.model.uiState.ProfileUiState
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@Suppress("NAME_SHADOWING")
class ProfileViewModel( val userRepository: LocalUserRepository) : ViewModel() {

//    val userUID = "dtU0UnHSqgaM5BMsdsVqyQwaHW22"

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    var dataFetchingInProgress by mutableStateOf(true)

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
                    delay(1000)
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
            val db = FirebaseDatabase.getInstance().getReference("users").child("data").child(localUID)

            db.child("gameID").setValue(_profileUiState.value.gamerID)
            db.child("bio").setValue(_profileUiState.value.bio)
            db.child("rank1GameName").setValue(_profileUiState.value.rank1GameName)
            db.child("rank1GameRank").setValue(_profileUiState.value.rank1GameRank)
            db.child("rank2GameName").setValue(_profileUiState.value.rank2GameName)
            db.child("rank2GameRank").setValue(_profileUiState.value.rank2GameRank)
            db.child("rank3GameName").setValue(_profileUiState.value.rank3GameName)
            db.child("rank3GameRank").setValue(_profileUiState.value.rank3GameRank)
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
    }

    suspend fun fetchData() {
        Log.d("ProfileViewModel TestCase","FetchData() called")
        val db = FirebaseDatabase.getInstance().getReference("users")
            .child("data")
            .child(localUID).get()
            .addOnSuccessListener { dataSnapshot ->
                val profileData = dataSnapshot.getValue(FirebaseProfileUiState::class.java)
                if (profileData != null) {
                    // Map FirebaseProfileUiState back to ProfileUiState
                    val mappedProfileData = ProfileUiState(
                        gamerID = profileData.gamerID,
                        gamerTag = profileData.gamerTag,
                        status = Pair(profileData.status.first, profileData.status.second),
                        bio = profileData.bio,
                        profileImageLink = profileData.profileImageLink,
                        rank1GameName = profileData.rank1GameName,
                        rank1GameRank = profileData.rank1GameRank,
                        rank2GameName = profileData.rank2GameName,
                        rank2GameRank = profileData.rank2GameRank,
                        rank3GameName = profileData.rank3GameName,
                        rank3GameRank = profileData.rank3GameRank,
                        isChangeStatusExpanded = profileData.isChangeStatusExpanded,
                        UserGamerCalls = profileData.UserGamerCalls
                    )
                    Log.d("ProfileVM FetchDataTrial TestCase", profileData.toString())
                    updatingFetchedData(mappedProfileData)
                } else {
                    Log.e("FetchDataTrial TestCase","No data available")
                }
            }.addOnFailureListener {
                Log.e("FetchDataTrial TestCase","Error fetching data")
        }
    }

    private fun updatingFetchedData(mappedProfileData: ProfileUiState) {
        _profileUiState.update { currentState -> currentState.copy(
                gamerID = mappedProfileData.gamerID,
                bio = mappedProfileData.bio,
                rank1GameName = mappedProfileData.rank1GameName,
                rank1GameRank = mappedProfileData.rank1GameRank,
                rank2GameName = mappedProfileData.rank2GameName,
                rank2GameRank = mappedProfileData.rank2GameRank,
                rank3GameName = mappedProfileData.rank3GameName,
                rank3GameRank = mappedProfileData.rank3GameRank
            )
        }
        Log.d("UpdatingDataTrial TestCase 1", mappedProfileData.toString())
    }
}