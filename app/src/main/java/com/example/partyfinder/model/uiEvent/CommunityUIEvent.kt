package com.example.partyfinder.model.uiEvent

sealed class CommunityUIEvent{
    data class ContentChanged(val content:String): CommunityUIEvent()
    object NewPostAdded : CommunityUIEvent()
}