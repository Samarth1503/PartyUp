package com.example.partyfinder.ui.theme.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.GamerCalls
import com.example.partyfinder.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel:ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()
    var gamerIDtextfieldValue by mutableStateOf("")
    var gamerBiotextfieldValue by mutableStateOf("")
    var selectedStatus by mutableStateOf(Pair(0,0))



    init {

        var GamerCallList : List<GamerCalls>?

//        viewModelScope.launch {
//            try {
//                GamerCallList = networkGamerCallsRepository.getGamerCalls()
//                _profileUiState.update { currentState -> currentState.copy(
//                    UserGamerCalls = GamerCallList
//                ) }
//            }
//            catch (e : HttpException){
//                Log.d(TAG,"error in fetching data")
//            }
//
//        }

    }


    fun onGamerIDtextFieldChanged(gamerID: String){
        gamerIDtextfieldValue=gamerID
    }

    fun onGamerBioFieldChanged(gamerBio:String){
        gamerBiotextfieldValue=gamerBio
    }
    fun updateGamerID(gamerID: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                gamerID = gamerID
            )
        }
    }

    fun updateBio(userBio: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                bio = userBio
            )
        }
    }

    fun onSaveChangesClicked(){
        updateGamerID(gamerIDtextfieldValue)
        updateBio(gamerBiotextfieldValue)
    }
    fun updateGamerTag(gamerTag: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                gamerTag = gamerTag
            )
        }
    }

    fun updateGamerStatus(status: Pair<Int, Int>) {
        _profileUiState.update { currentState ->
            currentState.copy(
                status = status
            )
        }
    }

    fun updateRank1(updatedgameName: String, updatedgameRank: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                rank1GameName = updatedgameName,
                rank1GameRank = updatedgameRank
            )
        }
    }
    fun updateRank2(updatedgameName: String, updatedgameRank: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                rank2GameName = updatedgameName,
                rank2GameRank = updatedgameRank
            )
        }
    }
    fun updateRank3(updatedgameName: String, updatedgameRank: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                rank3GameName = updatedgameName,
                rank3GameRank = updatedgameRank
            )
        }
    }
    fun updateStatus(changedStatus:Pair<Int,Int>){
        selectedStatus=changedStatus
    }

    fun onChangeStatusClicked(isExpanded:Boolean,selectedStatus:Pair<Int,Int>){
       if(isExpanded){
           _profileUiState.update { currentState -> currentState.copy(
            status = selectedStatus,
            isChangeStatusExpanded = !isExpanded,) }
       }
        else{
            _profileUiState.update{
                currentState -> currentState.copy(
                    isChangeStatusExpanded = !isExpanded
                )
            }
       }
    }
}



