package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatItem(
    val author: String,
    val content: String,
    val timeStamp: String
) {
    // No-argument constructor for Firebase
    constructor() : this("", "", "")
}
