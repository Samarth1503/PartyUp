package com.example.partyfinder.model

import kotlinx.serialization.Serializable

@Serializable
data class LiveGamerCallSearchResult(
    val liveGamerCallObject:LiveGamerCall,
    val userAccount: UserAccount
)
