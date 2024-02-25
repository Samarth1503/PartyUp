package com.example.partyfinder.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LiveGamerCallList(
    @SerializedName("data") val liveGamerCallList: Map<String,LiveGamerCall>
)
