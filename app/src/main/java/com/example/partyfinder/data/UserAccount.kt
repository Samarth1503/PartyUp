package com.example.partyfinder.data

data class UserAccount(
    val gamerID:String,
    val gamerTag:String,
    val profilePic:Int,
    val status:Pair<Int,Int>,
    val bio:String,
)