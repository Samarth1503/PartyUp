package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatChannel

data class ChatScreenUiState(
    val channelList:List<ChatChannel> =datasource.ChatChannels,
    val isMenuClicked:Boolean=false,
    val isDmTopbarMenuClicked:Boolean=false,

    )