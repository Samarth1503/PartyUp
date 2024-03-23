package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.uiState.CreateGamerCallsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CreateGamerCallsViewModel(val userUIDSharedViewModel : UserUIDSharedViewModel, val retrievedUserUID:String?) :ViewModel() {
    private val _CreateGamerCallsUiState = MutableStateFlow(CreateGamerCallsUiState())
    val CreateGamerCallUiState:StateFlow<CreateGamerCallsUiState> = _CreateGamerCallsUiState.asStateFlow()

    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID
    init {
        if (retrievedUserUID == null){
            viewModelScope.launch {
                while (isActive){
                    _currentUserUID.value = userUIDSharedViewModel.currentUserUID.value
                    delay(1000)
                }
            }
        }
        else{
            _currentUserUID.value = retrievedUserUID!!
        }
    }

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
        var gamerCall = GamerCalls(
            gamerCallID = "",
            ProfilePic = "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6",
            callDes = _CreateGamerCallsUiState.value.DescriptionOfCall,
            gameName = _CreateGamerCallsUiState.value.gameName,
            userUID = currentUserUID.value!!,
            gamerID = "Kaizoku",
            gamerTag = "#123",
            partySize = _CreateGamerCallsUiState.value.noOfGamers.toInt(),
            callDuration = _CreateGamerCallsUiState.value.CallDuration
        )

        viewModelScope.launch {
           val response = networkGamerCallsRepository.postGamerCall(gamerCall)
            if (response.isSuccessful){
                Log.d("Posting GamerCall", "GamerCall Posted Successfully")

                val gamerCallID = response.body()!!.name
                gamerCall.gamerCallID = gamerCallID

                val updateResponse = networkGamerCallsRepository.updateGamerCall(gamerCallID = gamerCallID, gamerCall = gamerCall)
                if (updateResponse.isSuccessful){
                    Log.d("Posting GamerCall","GamerCall Updated Successfully")
                }
            }
        }
    }
}