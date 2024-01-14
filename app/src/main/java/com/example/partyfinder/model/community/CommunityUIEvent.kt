package com.example.partyfinder.model.community

sealed class CommunityUIEvent{
    data class ContentChanged(val content:String): CommunityUIEvent()
    object NewPostAdded : CommunityUIEvent()
}