package com.example.partyfinder.model.uiState

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
    var communityList: CommunitiesList? = CommunitiesList(communityList = mapOf(
        Pair("Valorant", Community(communityPosts = mapOf(
            Pair("String", CommunityPost(
                postId = "1",
                userName = "Kaizoku",
                userProfilepic = "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6",
                postContent = "Hello"))))
    )))
)