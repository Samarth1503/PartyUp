package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class LiveGamerCallRequest(
    val senderLiveGamerCallID:String,
    val receiverGamerID:String,
    val senderGamerID:String,
    val isAccepted:Boolean
)
