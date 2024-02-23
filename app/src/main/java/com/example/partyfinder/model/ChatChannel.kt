package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannel(
    val channelID:Int,
    val channelName:String,
    val channelProfile:Int,
    var content:Array<ChatItem>,
    val isGroupChat:Boolean,
    val gamerTag:String,
    val memberTags:Array<String>,
)