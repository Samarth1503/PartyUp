package com.example.partyfinder.model

import com.example.partyfinder.datasource.datasource

data class PartyFinderUiState(
    val isGameNameDDExtended:Boolean=false,
    val gameNameSelectedValue:String="",
    val isNoOfPlayerDDExtended:Boolean = false,
    val noOfPlayersInParty:String="",
    val noOfPlayerRequired:String = "",
    val isNoOfPlayerRequiredDDExtended:Boolean=false,
    val listOfGameNameDDitems:List<String> = datasource.FindPartyGamesMenuItems,
    val listOfNoOfPLayerDDitems:List<String> = datasource.FindPartyNoOfPlayerMenuItems
)