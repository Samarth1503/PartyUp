package com.example.partyfinder.model

import com.example.partyfinder.datasource.datasource

data class ProfileUiState(
    val gamerID:String="GamerID",
    val gamerTag:String="#123",
    val status:Pair<Int,Int> = datasource.userStatusOption.get(0),
    val bio:String="Hey,I like to play games",
    val rank1GameName :String="",
    val rank1GameRank:String="",
    val rank2GameName :String="",
    val rank2GameRank:String="",
    val rank3GameName :String="",
    val rank3GameRank:String="",

    val UserGamerCalls:List<String> = listOf()

)
