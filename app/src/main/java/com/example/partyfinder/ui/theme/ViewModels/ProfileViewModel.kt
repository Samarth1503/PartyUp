package com.example.partyfinder.ui.theme.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.uiState.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()
    var gamerIDtextfieldValue by mutableStateOf("")
    var gamerBiotextfieldValue by mutableStateOf("")
    var selectedStatus by mutableStateOf(Pair(0,0))
    private var _gamerCallList = MutableLiveData<GamerCallsList>()
    val gamerCallList: LiveData<GamerCallsList> get() = _gamerCallList

    init {
        viewModelScope.launch {
            while (isActive) {
                backGroundGetGamerCall()
            }
        }
    }

    suspend fun backGroundGetGamerCall() {
        _gamerCallList.value = networkGamerCallsRepository.getGamerCalls().value
        var response = _gamerCallList.value
        if (response != null) {
            gamerCallList.observeForever { response ->
                _profileUiState.update { currentState -> currentState.copy(
                    UserGamerCalls = response
                ) }
            }
        }
    }

    fun onGamerIDtextFieldChanged(gamerID: String) {
        _profileUiState.update { currentState -> currentState.copy(
            gamerID = gamerID
        )}
    }

    fun onGamerBioFieldChanged(gamerBio: String) {
        _profileUiState.update { currentState -> currentState.copy(
            bio = gamerBio
        )}
    }

    fun onSaveChangesClicked(userAccount: UserAccount) {
        userAccount.gamerID = _profileUiState.value.gamerID
        userAccount.bio = _profileUiState.value.bio
        userAccount.rank1GameName = _profileUiState.value.rank1GameName
        userAccount.rank1GameRank = _profileUiState.value.rank1GameRank
        userAccount.rank2GameName = _profileUiState.value.rank2GameName
        userAccount.rank2GameRank = _profileUiState.value.rank2GameRank
        userAccount.rank3GameName = _profileUiState.value.rank3GameName
        userAccount.rank3GameRank = _profileUiState.value.rank3GameRank
        userAccount.status = _profileUiState.value.status
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
}