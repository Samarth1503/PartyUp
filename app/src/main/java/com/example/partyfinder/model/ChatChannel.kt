package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannel(
    val channelID: Int,
    val channelName: String,
    val channelProfile: Int,
    var content: List<ChatItem>,
    val isGroupChat: Boolean,
    val gamerTag: String,
    val memberTags: List<String>
) {
    // No-argument constructor for Firebase
    constructor() : this(0, "", 0, emptyList(), false, "", emptyList())
}