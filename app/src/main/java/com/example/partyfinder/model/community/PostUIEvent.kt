package com.example.partyfinder.model.community

sealed class PostUIEvent{
    data object PostLiked: PostUIEvent()
    data object PostUnLiked: PostUIEvent()
    data object PostReported : PostUIEvent()
    data object PostShared: PostUIEvent()
}