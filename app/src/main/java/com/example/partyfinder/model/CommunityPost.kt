package com.example.partyfinder.model

import kotlinx.serialization.Serializable
import java.sql.Types.NULL


@Serializable
data class CommunityPost(
    var postId: String = "",
    val postContent: String = "",
    val userName: String = "",
    val userProfilepic: String = "",
    val shareCount: Int = NULL,
    val likes: Int = NULL,
    val reports: Int = NULL,
    val communityName: String = "",
)