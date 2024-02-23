package com.example.partyfinder.network

import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatChannelApi {
    @POST("chatChannels/data.json")
    suspend fun postChatChannel(@Body chatChannel: ChatChannel)

    @GET("chatChannels/data.json")
    suspend fun getAllChatChannels():ChatChannelList

}