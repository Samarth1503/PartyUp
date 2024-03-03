package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.model.uiState.FilteredGamerCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilteredGamerCallsViewModel(userRepository: LocalUserRepository) : ViewModel(){
    private val _FilteredGamerCallsUiState = MutableStateFlow(FilteredGamerCallUiState())
    val FilteredGamerCallUiState:StateFlow<FilteredGamerCallUiState> =_FilteredGamerCallsUiState.asStateFlow()


    fun onGameNameDismiss(){
        _FilteredGamerCallsUiState.update {currentState -> currentState.copy(
            isFGameNameDropDownExpanded = false
        ) }
    }

    fun onNoOfGamersDismiss(){
        _FilteredGamerCallsUiState.update {currentState -> currentState.copy(
            isFNoOfGamersDropDownExpanded = false
        ) }
    }
    fun onGameNameExapandedChange(newValue:Boolean){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            isFGameNameDropDownExpanded = newValue
        ) }
    }

    fun onNoOfGamersExapandedChange(newValue:Boolean){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            isFNoOfGamersDropDownExpanded = newValue
        ) }
    }
    fun onGameNameValueChange(newValue:String){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            FGameNameDropDownValue = newValue
        )  }
    }

    fun onNoOfPlayersvalueChange(newValue: String){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            FNoOfGamersDropDownvalue = newValue
        ) }
    }

    fun onFGameNameItemClick(newValue: String){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            FGameNameDropDownValue = newValue
        ) }
        onGameNameDismiss()
    }
    fun onFNoOfGamersItemClick(newValue: String){
        _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
            FNoOfGamersDropDownvalue = newValue
        ) }
        onNoOfGamersDismiss()
    }

}