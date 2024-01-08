package com.example.partyfinder.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.PartyFinderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PartyFinderViewModel :ViewModel(){
    private val _partyFinderScreenUiState = MutableStateFlow(PartyFinderUiState())
    val partyFinderUiState:StateFlow<PartyFinderUiState> = _partyFinderScreenUiState.asStateFlow()

    var isGameDDExpanded by mutableStateOf(false)
    var gameNameSelectedValue by mutableStateOf("")
    var selecteditem by mutableStateOf("")
    fun onGameNameExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGameNameDDExtended = newValue
        ) }
    }

    fun onGameNameValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue = newValue
        ) }
    }

    fun onGameNameDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGameNameDDExtended = false
        )}
    }

    fun onGameNameItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue=item,
            isGameNameDDExtended = false
        ) }
    }
}