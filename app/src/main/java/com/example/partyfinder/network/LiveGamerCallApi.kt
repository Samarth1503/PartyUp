package com.example.partyfinder.network


import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.LiveGamerCall
import com.example.partyfinder.model.LiveGamerCallList
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LiveGamerCallApi {

    @GET("liveGamerCalls.json")
    suspend fun getLiveGamerCallList():Response<LiveGamerCallList>

    @POST("liveGamerCalls/data.json")
    suspend fun postLiveGamerCalls(@Body liveGamerCall:LiveGamerCall): Response<FirebaseResponse>

    @PUT("liveGamerCalls/data/{liveGamerCallId}/.json")
    suspend fun updateLiveGamerCall(@Path("liveGamerCallId") liveGamerCallId: String, @Body liveGamerCall: LiveGamerCall): Response<ResponseBody>
}