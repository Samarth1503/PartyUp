package com.example.partyfinder.data

data class ChatChannel(
    val channelID:Int,
    val channelName:String,
    val isGroupChat:Boolean,
    val gamerTag:String,
    val memberTags:List<String>,
    val channelProfile:Int,
)