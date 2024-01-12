package com.example.partyfinder.model.Community

sealed class PostUIEvent{
    object PostLiked: PostUIEvent()
    object PostUnLiked: PostUIEvent()
    object PostReported : PostUIEvent()
    object PostShared: PostUIEvent()
}