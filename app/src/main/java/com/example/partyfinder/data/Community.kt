package com.example.partyfinder.data

data class Community(
    val communityID: Int,
    val communityName: String,
    val communityBannerImgLink: String,
    val noOfPosts: Int,
    val postsID: List<Int>
)
