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
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
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
class ProfileViewModel : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    private var localUID: String = ""

    private var profilePicLink: String = ""

    var selectedStatus by mutableStateOf(Pair(0,0))

    private var _gamerCallList = MutableLiveData<GamerCallsList>()
    val gamerCallList: LiveData<GamerCallsList> get() = _gamerCallList

    init {
        viewModelScope.launch {
                while(isActive){
                    backGroundGetGamerCall()
                    delay(5000)
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

    fun onSaveChangesClicked() {
        if (localUID != "") {
            val profileData = mapOf(
                "gamerID" to _profileUiState.value.gamerID,
                "bio" to _profileUiState.value.bio,
                "rank1GameName" to _profileUiState.value.rank1GameName,
                "rank1GameRank" to _profileUiState.value.rank1GameRank,
                "rank2GameName" to _profileUiState.value.rank2GameName,
                "rank2GameRank" to _profileUiState.value.rank2GameRank,
                "rank3GameName" to _profileUiState.value.rank3GameName,
                "rank3GameRank" to _profileUiState.value.rank3GameRank
            )

            Log.d(TAG, profileData.toString())

            val db = FirebaseDatabase.getInstance().getReference("users")
            db.child(localUID).setValue(profileData)
                .addOnSuccessListener { Log.d(TAG, "Realtime Database update successful!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating Realtime Database", e) }
        } else {
            Log.d(TAG, "No local UID found")
        }
    }



    fun updateRank(gameNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (gameNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank1GameName = updatedGameName,
                rank1GameRank = updatedGameRank
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank2GameName = updatedGameName,
                rank2GameRank = updatedGameRank
            )}
            3 -> _profileUiState.update { currentState -> currentState.copy(
                rank3GameName = updatedGameName,
                rank3GameRank = updatedGameRank
            )}
        }
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

//    fun getUserUID(uid: String){
//        if (uid != ""){
//            localUID = uid
//        } else {
//            Log.d("UID Not Found", "UID not found in the Profile View Model")
//        }
//    }
}