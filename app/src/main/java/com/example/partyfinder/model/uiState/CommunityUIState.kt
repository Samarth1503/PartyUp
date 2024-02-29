package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.CommunitiesList
import com.example.partyfinder.model.Community
import com.example.partyfinder.model.CommunityPost
import java.sql.Types.NULL

data class CommunityUIState(
    var communityName: String = "Valorant",
    var communityObject:Community = Community(communityPosts = mapOf(Pair("String",CommunityPost(postId = "1", userName = "a", postContent = "a", userProfilepic = "s")))),
    var communityID: Int = NULL,
    var communityPostsNumber: Int = NULL,
    var newPostContent: String = "",
    var communityList: CommunitiesList? = datasource.tempCommunityList
)