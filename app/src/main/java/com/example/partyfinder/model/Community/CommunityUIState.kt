package com.example.partyfinder.model.Community

import java.sql.Types.NULL

data class CommunityUIState(
    var communityName: String = "",
    var communityID: Int = NULL,
    var communityPostsNumber: Int = NULL,
    var newPostContent: String = ""
)