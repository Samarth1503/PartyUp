package com.example.partyfinder.model.Community

sealed class CommunityUIEvent{
    data class ContentChanged(val content:String): CommunityUIEvent()
    object NewPostAdded : CommunityUIEvent()
}