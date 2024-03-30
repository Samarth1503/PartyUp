package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.Status

data class ProfileUiState(
    val gamerID:String = "GamerID",
    val gamerTag:String = "#TAG",
    val status: Status = Status(),
    val bio:String = "Hey,I like to play games",
    val profilePic: String = "",
    val coverImageLink: String = "",
    val rank1GameName :String = "",
    val rank1GameRank:String = "",
    val rank2GameName :String = "",
    val rank2GameRank:String = "",
    val rank3GameName :String = "",
    val rank3GameRank:String = "",
    val isChangeStatusExpanded:Boolean = false,
    val UserGamerCalls: GamerCallsList?= datasource.gamerCallsList,
    val random4GamerCallsOnHomeScreen : GamerCallsList? = datasource.gamerCallsList
)

data class Ranks(
    val gameNumber: Int,
    val gameName: String,
    val gameRank: String
)