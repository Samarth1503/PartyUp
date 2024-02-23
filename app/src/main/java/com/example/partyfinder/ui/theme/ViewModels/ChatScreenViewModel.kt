package com.example.partyfinder.ui.theme.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.R
import com.example.partyfinder.data.repositories.networkChatChannelRepository
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.ChatItem
import com.example.partyfinder.model.local.SortType
import com.example.partyfinder.model.uiState.ChatScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime


class chatScreenViewModel : ViewModel(){



    private val _chatsScreenUiState = MutableStateFlow(ChatScreenUiState())
    val chatsScreenUiState:StateFlow<ChatScreenUiState> =_chatsScreenUiState.asStateFlow()
    var currentChannel by mutableStateOf(datasource.ChatChannels.get(0))
    var isMenuClicked by mutableStateOf(false)
    var isDmScreenMenuClicked by mutableStateOf(false)
    private val _sortType = MutableStateFlow(SortType.MOST_RECENT)
    val _chatChannelList = MutableLiveData<ChatChannelList>()
    val chatChannelList :LiveData<ChatChannelList> get() = _chatChannelList

    val _currentChannelObject = MutableLiveData<ChatChannel>()
    val currentChannelObject:LiveData<ChatChannel> get() = _currentChannelObject
    init {
       viewModelScope.launch {
           while (isActive){
                currentChannelObject.observeForever {
                    it ->
                    _chatsScreenUiState.update { currentState -> currentState.copy(
                        currentChannelObject = it
                    ) }
                }
               if (chatsScreenUiState.value.currentChannel!=""){
                       updateChatChannel(chatsScreenUiState.value.currentChannel)
               }

               val response = retrieveChatChannelList()
               if (response.isSuccessful){
                   Log.d(TAG,response.body().toString())
                   _chatsScreenUiState.update { currentState -> currentState.copy(
                       channelList = response.body()
                   ) }
               }
               else{
                   Log.d(TAG,"Error fetching data")
               }
               delay(1000)
           }

       }

    }
        fun onNewChatClicked(){
            val ChatChannel = ChatChannel(
                channelID = 2,
                channelName = "Trial Chat 3",
                isGroupChat = true,
                channelProfile = R.drawable.pp,
                gamerTag = "#1342",
                content = arrayOf(
                    ChatItem("kaizoku",content="Hello",timeStamp = LocalDateTime.now().toString()),
                    ChatItem("Sam",content="Sup",timeStamp = LocalDateTime.now().toString())),

                memberTags = arrayOf("Sam","Kurama","Ichigo")

            )

        viewModelScope.launch {
            if (chatsScreenUiState.value.currentChannel !=""){
            networkChatChannelRepository.postChatChannel(ChatChannel)}
        }
        Log.d(TAG,"Chat channel posted")
    }
    fun onChatsScreenMenuClick(){
        isMenuClicked=!isMenuClicked
        _chatsScreenUiState.update { currentState -> currentState.copy(
            isMenuClicked = isMenuClicked
        ) }
    }




    fun onDmScreenMenuClicked(){
        isDmScreenMenuClicked = !isDmScreenMenuClicked
        _chatsScreenUiState.update {currentState -> currentState.copy(
            isDmTopbarMenuClicked = isDmScreenMenuClicked
        ) }
    }

    fun setAndRetreiveCurrentChatChannel(channelId:String):ChatChannel?{
        _chatsScreenUiState.update { currentState -> currentState.copy(
            currentChannel = channelId
        ) }

        viewModelScope.launch {

                val response = networkChatChannelRepository.retreiveCurrentChannel(channelId)
                if (response.isSuccessful){
                    _chatsScreenUiState.update { currentState -> currentState.copy(
                        currentChannelObject = response.body()
                    ) }
                    Log.d(TAG,"Current ChatChannelFetched")
                }
                else{
                    Log.d(TAG,"Error Fetching chat Channel")
                }

        }

        return chatsScreenUiState.value.currentChannelObject
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


    suspend fun retrieveChatChannelList():Response<ChatChannelList>{
        val response = networkChatChannelRepository.getAllChatChannels()
        _chatChannelList.value = response.body()
        return response
    }


    fun onMessageValueChanged(changedMsg:String){
        _chatsScreenUiState.update {currentState -> currentState.copy(
            message = changedMsg
        )} }

    fun sendChatButtonClick(fireBaseUniqueID:String,message:String){
        val chatItem = ChatItem(author ="kaizoku",content=message, timeStamp = LocalDateTime.now().toString())
        viewModelScope.launch{
           val response = networkChatChannelRepository.retreiveCurrentChannel(Id =  fireBaseUniqueID)
            var Channel = response.body()
            Channel!!.content = arrayOf(*Channel.content,chatItem)

            networkChatChannelRepository.postDmContent(Id = fireBaseUniqueID , content = Channel.content)
            _chatsScreenUiState.update { currentState -> currentState.copy(
                message=""
            ) }
        }
    }


    fun updateChatChannel(id:String){
        viewModelScope.launch {
            val response = networkChatChannelRepository.retreiveCurrentChannel(Id = id)
            if (response.isSuccessful){
                _currentChannelObject.value = response.body()
                Log.d(TAG,"CHAT CHANNEL UPDATED")

            }
        }
    }

}


