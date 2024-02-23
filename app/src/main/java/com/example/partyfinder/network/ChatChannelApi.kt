package com.example.partyfinder.network

import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.ChatItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChatChannelApi {
    @POST("chatChannels/data.json")
    suspend fun postChatChannel(@Body chatChannel: ChatChannel)

    @GET("chatChannels.json")
    suspend fun getAllChatChannels():Response<ChatChannelList>

    @GET("chatChannels/data/{uniqueID}.json")
    suspend fun retreiveCurrentChannel(@Path("uniqueID")firebaseID: String):Response<ChatChannel>

    @PUT("chatChannels/data/{uniqueID}/content.json")
    suspend fun addChatItem(@Path("uniqueID")firebaseID:String , @Body content:Array<ChatItem>)
}