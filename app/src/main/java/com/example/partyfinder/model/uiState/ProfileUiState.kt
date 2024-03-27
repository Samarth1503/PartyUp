package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.Status

data class ProfileUiState(
    val gamerID:String = "GamerID",
    val gamerTag:String = "#123",
    val status: Status = datasource.userStatusOption.get(0),
    val bio:String = "Hey,I like to play games",
    val profileImageLink: String = "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6",
    val rank1GameName :String = "",
    val rank1GameRank:String = "",
    val rank2GameName :String = "",
    val rank2GameRank:String = "",
    val rank3GameName :String = "",
    val rank3GameRank:String = "",
    val isChangeStatusExpanded:Boolean = false,
    val UserGamerCalls: GamerCallsList ?= datasource.gamerCallsList
)
