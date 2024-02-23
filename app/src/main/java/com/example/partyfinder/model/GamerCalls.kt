package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class GamerCalls(
    val gamerCallID:String,
    val gamerID:String,
    val gamerTag:String,
    val partySize:Int,
    val callDes:String,
    val gameName:String,
    val ProfilePic:String,
    val callDuration:String
)