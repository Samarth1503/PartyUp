package com.example.partyfinder.model

data class ChatItem(
    val author: String,
    val content: String,
    val timeStamp: String
) {
    // No-argument constructor for Firebase
    constructor() : this("", "", "")
}