package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class Community(
    val communityPosts : Map<String,CommunityPost>
)
