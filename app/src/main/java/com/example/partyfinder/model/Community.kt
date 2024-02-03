package com.example.partyfinder.model

data class Community(
    val communityID: Int,
    val communityName: String,
    val communityBannerImgLink: String,
    val noOfPosts: Int,
    val postsID: List<Int>
)
