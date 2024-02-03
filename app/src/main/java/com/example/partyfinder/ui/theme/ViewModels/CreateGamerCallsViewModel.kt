package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.uiState.CreateGamerCallsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateGamerCallsViewModel :ViewModel() {
    private val _CreateGamerCallsUiState = MutableStateFlow(CreateGamerCallsUiState())
    val CreateGamerCallUiState:StateFlow<CreateGamerCallsUiState> = _CreateGamerCallsUiState.asStateFlow()

    fun onGameNameValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            gameName = newValue
        ) }
    }
    fun onNoOfGamersValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            noOfGamers = newValue
        ) }
    }
    fun onCallDescriptionValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            DescriptionOfCall = newValue
        ) }
    }
    fun onCallDurationValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            CallDuration = newValue
        ) }
    }

    fun postGamerCall(){
        val gamerCall = GamerCalls(

            ProfilePic = "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6",
            callDes = "Need players to play Overwatch",
            gameName = "OverWatch",
            gamerID = "Sam",
            gamerTag = "#123",
            partySize = 2
        )

        viewModelScope.launch {
            networkGamerCallsRepository.postGamerCall(gamerCall)
        }
    }
}