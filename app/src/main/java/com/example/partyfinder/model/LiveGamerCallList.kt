package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class LiveGamerCallList(
    val liveGamerCallList: Map<String,LiveGamerCall>
)
