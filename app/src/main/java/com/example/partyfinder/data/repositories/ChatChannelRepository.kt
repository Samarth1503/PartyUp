package com.example.partyfinder.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.ChatItem
import retrofit2.Response

interface ChatChannelRepository{
    suspend fun postChatChannel(chatChannel: ChatChannel)


    suspend fun getAllChatChannels():Response<ChatChannelList>

    suspend fun postDmContent(Id:String,content:List<ChatItem>)

    suspend fun retreiveCurrentChannel(Id: String):Response<ChatChannel>
}

object networkChatChannelRepository : ChatChannelRepository{
    val data = MutableLiveData<ChatChannelList>()
    val liveChatChannelList :LiveData<ChatChannelList>
        get() = data
    override suspend fun postChatChannel(chatChannel: ChatChannel) {
        ChatChannelApiService.postChatChannel(chatChannel)
    }



    override suspend fun getAllChatChannels():Response<ChatChannelList> {
        return ChatChannelApiService.getAllChatChannels()

    }

    override suspend fun postDmContent(Id: String, content: List<ChatItem>) {
       ChatChannelApiService.addChatItem(firebaseID = Id , content = content)
    }

    override suspend fun retreiveCurrentChannel(Id: String):Response<ChatChannel> {
        return ChatChannelApiService.retreiveCurrentChannel(firebaseID = Id)
    }
}