package com.example.partyfinder.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ChatChannelList(
    @SerializedName("data") val chatChannels:Map<String,ChatChannel>
)
