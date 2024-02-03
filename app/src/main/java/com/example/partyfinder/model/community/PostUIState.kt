package com.example.partyfinder.model.community

import java.sql.Types.NULL


data class PostUIState(
    val postID: Int = NULL,
    val content: String = "",
    val likes: Int = NULL,
    val reports: Int = NULL,
    val sharableLink: String = "",
    val shareCount: Int = NULL,
    val usernameOfGamer: String = "",
//    val postAge: Time,
    val communitysID: Int = NULL
)