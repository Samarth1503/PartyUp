package com.example.partyfinder.data

import kotlinx.serialization.Serializable

@Serializable
data class GamerCalls(
    val gamerID:String,
    val gamerTag:String,
    val partySize:Int,
    val callDes:String,
    val gameName:String,
    val ProfilePic:String
)