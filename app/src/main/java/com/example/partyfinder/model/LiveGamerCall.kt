package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class LiveGamerCall(
    val gameName:String,
    val noOfPlayersinParty:Int,
    val noOfPlayersRequired:Int,
    val gamerId:String,
    val requestsSent:List<LiveGamerCallRequest>,
    val requestsReceived:List<LiveGamerCallRequest>,
    val gamerCallAccepted:Boolean,
    val isGamerCallLive:Boolean,
    var liveGamerCallID:String
)
