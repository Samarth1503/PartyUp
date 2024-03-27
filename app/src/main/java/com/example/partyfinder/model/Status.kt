package com.example.partyfinder.model

import com.example.partyfinder.R
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    var first: Int = R.string.ONLINE,
    var second: Int = R.drawable.user_online_logo
)
