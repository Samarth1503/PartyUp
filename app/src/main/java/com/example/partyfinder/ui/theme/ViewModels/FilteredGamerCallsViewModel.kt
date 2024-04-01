package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.uiState.FilteredGamerCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilteredGamerCallsViewModel(val gamerCallsViewModel: GamerCallsViewModel,val chatScreenViewModel:chatScreenViewModel) : ViewModel(){

    private val _FilteredGamerCallsUiState = MutableStateFlow(FilteredGamerCallUiState())
    val FilteredGamerCallUiState:StateFlow<FilteredGamerCallUiState> =_FilteredGamerCallsUiState.asStateFlow()

    private var _unfilteredList = MutableLiveData<List<GamerCalls>>()
    val unfilteredList: LiveData<List<GamerCalls>> get() = _unfilteredList

    val userID = gamerCallsViewModel._currentUserUID

//    For the form
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

    fun onFilterButtonClicked() {
        viewModelScope.launch {
            gamerCallsViewModel.getGamerCallsToDisplay()

            // Extract the GamerCalls from the GamerCallsList and assign it to _gamerCallListToDisplay
            _unfilteredList.value = gamerCallsViewModel._gamerCallListToDisplay.value?.gamerCalls?.values?.toList()

            val fGamerCallMap = mutableMapOf<String,GamerCalls>()

            val filteredList = _unfilteredList.value?.filter { gamerCall ->
                val matchesGameName = if (_FilteredGamerCallsUiState.value.FGameNameDropDownValue.isNotEmpty()) {
                    gamerCall.gameName == _FilteredGamerCallsUiState.value.FGameNameDropDownValue
                } else {
                    true
                }

                val matchesNoOfGamers = if (_FilteredGamerCallsUiState.value.FNoOfGamersDropDownvalue.isNotEmpty()) {
                    gamerCall.partySize == _FilteredGamerCallsUiState.value.FNoOfGamersDropDownvalue.toInt()
                } else {
                    true
                }

                matchesGameName && matchesNoOfGamers
            }

            if (filteredList != null) {
                filteredList.forEach { gamerCalls ->
                    fGamerCallMap.put(gamerCalls.gamerCallID, gamerCalls)
                }
            }
            val finalGamerCall = GamerCallsList(fGamerCallMap)

            _FilteredGamerCallsUiState.update { currentState -> currentState.copy(
                listOfGamersCall = finalGamerCall
            ) }
        }
    }

}