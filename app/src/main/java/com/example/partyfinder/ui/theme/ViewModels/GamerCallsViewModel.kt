package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.uiState.GamersCallUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GamerCallsViewModel (val userUIDSharedViewModel : UserUIDSharedViewModel, val retrievedUserUID:String?,val chatScreenViewModel:chatScreenViewModel): ViewModel() {
    private val _GamerCallsUiState = MutableStateFlow(GamersCallUiState())
    val GamerCallsUiState:StateFlow<GamersCallUiState> =_GamerCallsUiState.asStateFlow()

    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID

    var _gamerCallListToDisplay = MutableLiveData<GamerCallsList>()
    val gamerCallListToDisplay: LiveData<GamerCallsList> get() = _gamerCallListToDisplay

    init{
         viewModelScope.launch {
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
        viewModelScope.launch {
            if(currentUserUID.value != null){
                getGamerCallsToDisplay()
                delay(5000)
            }
        }
    }

    fun fetchUserID(): String {
        return _currentUserUID.value!!
    }

    fun onPartyUpClicked(user2UID:String):String{
        var channelId = chatScreenViewModel.onNewChatClicked(currentUserGamerID = currentUserUID.value!!, user2UUID = user2UID,isGroupChatpara = false)
        return channelId
    }

    suspend fun getGamerCallsToDisplay(){
        _gamerCallListToDisplay.value = networkGamerCallsRepository.getGamerCallsToDisplay(currentUserUID = currentUserUID.value!!).value

        val response = gamerCallListToDisplay.value
        if (response != null ){
            gamerCallListToDisplay.observeForever { response ->
                _GamerCallsUiState.update { currentState -> currentState.copy(
                    listOfGamersCall = response
                ) }
            }
        }
    }
}

