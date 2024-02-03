package com.example.partyfinder.data

import java.sql.Time

data class Posts(
    val postID: Int,
    val content: String,
    val likes: Int,
    val reports: Int,
    val sharableLink: String,
    val usernameOfGamer: String,
    val postAge: Time,
    val communitysID: Int
)
