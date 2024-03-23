package com.example.partyfinder.network

import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.GamerCallsList
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GamerCallApi {


    @GET("gamerCalls.json")
     suspend fun getGamerCalls( ): GamerCallsList

    @POST("gamerCalls/data.json")
    suspend fun postGamerCall( @Body gamerCall: GamerCalls):Response<FirebaseResponse>

    @PUT("gamerCalls/data/{gamerCallid}/.json")
    suspend fun updateLiveGamerCall(@Path("gamerCallid") gamerCallId: String, @Body gamerCall: GamerCalls): Response<ResponseBody>


}








