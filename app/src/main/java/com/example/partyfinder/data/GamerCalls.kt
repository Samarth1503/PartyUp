package com.example.partyfinder.data

import androidx.annotation.DrawableRes

data class GamerCalls(
    val gamerID:String,
    val gamerTag:String,
    val partySize:Int,
    val callDes:String,
    val gameName:String,
    @DrawableRes val ProfilePic:Int
)