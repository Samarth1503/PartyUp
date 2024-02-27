package com.example.partyfinder.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CommunitiesList(
    @SerializedName("data")
    val communityList:Map<String,Community>
)
