package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList

data class ChatScreenUiState(
    val channelList:ChatChannelList = datasource.chatChannelList,
    val isMenuClicked:Boolean=false,
    val isDmTopbarMenuClicked:Boolean=false,
    val currentChannel:ChatChannel = datasource.ChatChannels.get(0)
    )