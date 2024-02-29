package com.example.partyfinder.model

import kotlinx.serialization.Serializable


@Serializable
data class CommunityPost(
    var postId: String = "",
    val userName: String = "",
    val userProfilepic: String = "",
    val postContent: String = ""
)