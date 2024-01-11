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

    fun onNoOfPartyExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerDDExtended = newValue
        ) }
    }

    fun onNoOfPartyValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayersInParty = newValue
        ) }
    }

    fun onNoOfPartyDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerDDExtended = false
        )}
    }
    fun onNoOfPartyItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayersInParty = item,
            isNoOfPlayerDDExtended = false
        ) }
    }

    fun onNoOfRequiredExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerRequiredDDExtended = newValue
        ) }
    }
    fun onNoOfRequiredValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayerRequired = newValue
        ) }
    }

    fun onNoOfRequiredDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerRequiredDDExtended = false
        )}
    }
    fun onNoOfRequiredItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayerRequired = item,
            isNoOfPlayerRequiredDDExtended = false
        ) }
    }
}