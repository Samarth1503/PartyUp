package com.example.partyfinder.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GamerCallsList(

    @SerializedName("data") val gamerCalls: Map<String,GamerCalls>
)
