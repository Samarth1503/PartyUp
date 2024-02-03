package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.uiState.PartyFinderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PartyFinderViewModel :ViewModel(){
    private val _partyFinderScreenUiState = MutableStateFlow(PartyFinderUiState())
    val partyFinderUiState:StateFlow<PartyFinderUiState> = _partyFinderScreenUiState.asStateFlow()


    fun onHideDetailsClicked(currentValue:Boolean){
        var updateTo =  !currentValue
        _partyFinderScreenUiState.update {currentState -> currentState.copy(
            hideDetails = updateTo
        )
        }
    }

    fun onClickClearDetails () {
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue = "",
            noOfPlayerRequired =  "",
            noOfPlayersInParty = "",
        ) }
        onStopCallClick()
    }
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

    fun onSearchClick(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGamerCallLive = true
        ) }
    }

    fun onStopCallClick(){
        _partyFinderScreenUiState.update { currentState ->currentState.copy(
            isGamerCallLive = false
        ) }
    }
}