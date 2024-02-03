package com.example.partyfinder.model.uiState

import java.sql.Types.NULL

data class CommunityUIState(
    var communityName: String = "",
    var communityID: Int = NULL,
    var communityPostsNumber: Int = NULL,
    var newPostContent: String = ""
)