package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChannel(
    var channelID: String,
    val channelName: String,
    val channelProfile: String,
    var content: List<ChatItem>,
    val isGroupChat: Boolean,
    val memberTags: List<String>
) {
    // No-argument constructor for Firebase
    constructor() : this("", "", "", emptyList(), false, emptyList())
}