package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannel(
    val channelID:Int,
    val channelName:String,
    val isGroupChat:Boolean,
    val gamerTag:String,
    val memberTags:List<String>,
    val channelProfile:Int,
    val content:Array<ChatItem>
)