package com.example.partyfinder.network

import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.GamerCallsList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GamerCallApi {


    @GET("gamerCalls.json")
     suspend fun getGamerCalls( ): GamerCallsList

    @POST("gamerCalls/data.json")
    suspend fun postGamerCall( @Body gamerCall: GamerCalls)



}








