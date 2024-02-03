package com.example.partyfinder.data

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GamerCalls(
    val gamerCallID:String=UUID.randomUUID().toString(),
    val gamerID:String,
    val gamerTag:String,
    val partySize:Int,
    val callDes:String,
    val gameName:String,
    val ProfilePic:String
)