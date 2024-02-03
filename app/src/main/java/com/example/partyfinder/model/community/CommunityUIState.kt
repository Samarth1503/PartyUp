package com.example.partyfinder.model.community

import java.sql.Types.NULL

data class CommunityUIState(
    var communityName: String = "",
    var communityID: Int = NULL,
    var communityPostsNumber: Int = NULL,
    var newPostContent: String = ""
)