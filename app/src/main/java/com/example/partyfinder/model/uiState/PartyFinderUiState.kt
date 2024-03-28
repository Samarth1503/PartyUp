package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.LiveGamerCallSearchResult

data class PartyFinderUiState(
    val hideDetails:Boolean = false,
    val isGamerCallLive:Boolean = false,
    val isGameNameDDExtended:Boolean=false,
    val gameNameSelectedValue:String="",
    val isNoOfPlayerDDExtended:Boolean = false,
    val noOfPlayersInParty:String="",
    val noOfPlayerRequired:String = "",
    val isNoOfPlayerRequiredDDExtended:Boolean=false,
    val listOfGameNameDDitems:List<String> = datasource.FindPartyGamesMenuItems,
    val listOfNoOfPLayerDDitems:List<String> = datasource.FindPartyNoOfPlayerMenuItems,
    val liveGamerCallResultLits:List<LiveGamerCallSearchResult>? = null,
    val currentLiveGamerCallID:String =""
)