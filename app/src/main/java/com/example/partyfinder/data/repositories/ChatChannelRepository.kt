package com.example.partyfinder.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList

interface ChatChannelRepository{
    suspend fun postChatChannel(chatChannel: ChatChannel)


    suspend fun getAllChatChannels(): LiveData<ChatChannelList>
}

object networkChatChannelRepository : ChatChannelRepository{
    val data = MutableLiveData<ChatChannelList>()
    val liveChatChannelList :LiveData<ChatChannelList>
        get() = data
    override suspend fun postChatChannel(chatChannel: ChatChannel) {
        ChatChannelApiService.postChatChannel(chatChannel)
    }



    override suspend fun getAllChatChannels(): LiveData<ChatChannelList> {
        data.value = ChatChannelApiService.getAllChatChannels()
        return liveChatChannelList
    }

}