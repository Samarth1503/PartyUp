package com.example.partyfinder.model.Community

sealed class CommunityUIEvent{
//    data class PostLiked(val postLiked: Boolean): CommunityUIEvent()
    data class PostAdded(val newPostAdded: Int): CommunityUIEvent()
    object NewPostAdded : CommunityUIEvent()
}