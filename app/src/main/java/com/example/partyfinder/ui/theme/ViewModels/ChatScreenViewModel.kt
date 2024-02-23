package com.example.partyfinder.ui.theme.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.uiState.ChatScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class chatScreenViewModel:ViewModel(){
    private val _chatsScreenUiState = MutableStateFlow(ChatScreenUiState())
    val chatsScreenUiState:StateFlow<ChatScreenUiState> =_chatsScreenUiState.asStateFlow()

    var isMenuClicked by mutableStateOf(false)
    var isDmScreenMenuClicked by mutableStateOf(false)

//    val _chatChannelList =MutableLiveData<ChatChannelList>()
//    val chatChannelList : LiveData<ChatChannelList> get () = _chatChannelList

    init {
//       viewModelScope.launch {
//           while (isActive){
//               retrieveChatChannelList()
//           }
//       }
    }
//    fun onNewChatClicked(){
//            val ChatChannel = ChatChannel(
//                channelID = 2,
//                channelName = "Trial Chat 3",
//                isGroupChat = false,
//                channelProfile = R.drawable.pp,
//                gamerTag = "#1342",
//                content = listOf(
//                    ChatItem("kaizoku",content="Hello",timeStamp = LocalDateTime.now().toString()),
//                    ChatItem("Sam",content="Sup",timeStamp = LocalDateTime.now().toString())),
//
//                memberTags = listOf("Sam")
//
//            )
//
//        viewModelScope.launch {
//            networkChatChannelRepository.postChatChannel(ChatChannel)
//        }
//        Log.d(TAG,"Chat channel posted")
//    }
    fun onChatsScreenMenuClick(){
        isMenuClicked=!isMenuClicked
        _chatsScreenUiState.update { currentState -> currentState.copy(
            isMenuClicked = isMenuClicked
        ) }
    }

    fun retreiveCurrentChannel(currentChannelId:String?): ChatChannel {
      var channel: ChatChannel?= datasource.ChatChannels.find {
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
        currentChannel: ChatChannel,
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

//    fun retrieveCurrentChatChannel(channelID:String?):ChatChannel{
//        val channelIDInt = channelID?:"0"
//        viewModelScope.launch {
//            while(isActive){
//                 val response = networkChatChannelRepository.getCurrentChatChannel(channelIDInt.toInt())
//                if (response.isSuccessful){
//                    currentChannel.value = response.body()
//                    _chatsScreenUiState.update { currentState -> currentState.copy(
//                        currentChannel = currentChannel.value
//                    ) }
//                        Log.d(TAG,"Retriving currentChatChannel")
//
//                }
//            }
//        }
//        return currentChannel.value?:datasource.ChatChannels.get(0)
//    }


//    suspend fun retrieveChatChannelList(){
//        _chatChannelList.value = networkChatChannelRepository.getAllChatChannels().value
//        chatChannelList.observeForever {
//            _chatsScreenUiState.update { currentState -> currentState.copy(
//                channelList = it
//            ) }
//        }
//    }

}