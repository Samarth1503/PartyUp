package com.example.partyfinder.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.ChatChannel
import com.example.partyfinder.data.UserAccount
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class chatScreenViewModel:ViewModel(){
    private val _chatsScreenUiState = MutableStateFlow(ChatScreenUiState())
    val chatsScreenUiState:StateFlow<ChatScreenUiState> =_chatsScreenUiState.asStateFlow()
    var currentChannel by mutableStateOf(datasource.ChatChannels.get(0))
    var isMenuClicked by mutableStateOf(false)
    var isDmScreenMenuClicked by mutableStateOf(false)



    fun onChatsScreenMenuClick(){
        isMenuClicked=!isMenuClicked
        _chatsScreenUiState.update { currentState -> currentState.copy(
            isMenuClicked = isMenuClicked
        ) }
    }

    fun retreiveCurrentChannel(currentChannelId:String?): ChatChannel {
      var channel:ChatChannel ?= datasource.ChatChannels.find {
          it.channelID == (currentChannelId?.toInt() ?: 0)
      }
        if(channel==null){
            return datasource.ChatChannels.get(0)
        }
        else
        {
            return channel
        }
    }

    fun retreiveGamerAccount(
        currentChannel:ChatChannel,
        userAccocuntList:List<UserAccount>): UserAccount {
        var userAccount = userAccocuntList.find {it.gamerTag==currentChannel.gamerTag }
        if (userAccount!=null){
            return userAccount
        }
        else
        {
            return UserAccount()
        }

    }

    fun onDmScreenMenuClicked(){
        isDmScreenMenuClicked = !isDmScreenMenuClicked
        _chatsScreenUiState.update {currentState -> currentState.copy(
            isDmTopbarMenuClicked = isDmScreenMenuClicked
        ) }
    }


}