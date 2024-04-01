package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.GamerCallsList

data class FilteredGamerCallUiState(
    val isFGameNameDropDownExpanded :Boolean = false,
    val FGameNameDropDownValue :String ="",
    val isFNoOfGamersDropDownExpanded:Boolean = false,
    val FNoOfGamersDropDownvalue :String ="",
    val listOfGameNameItems:List<String> = datasource.FindPartyGamesMenuItems,
    val listofNoOfGamersItems:List<String> = datasource.FindPartyNoOfPlayerMenuItems,
    val isFilterOn: Boolean = false,
    val listOfGamersCall: GamerCallsList = datasource.gamerCallsList
)
