package com.example.partyfinder.data

data class ChatChannel(
    val channelID:Int,
    val isGroupChat:Boolean,
    val gamerTag:String,
    val memberTags:List<String>,
)