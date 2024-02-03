package com.example.partyfinder.network

import com.example.partyfinder.data.GamerCalls
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GamerCallApi {
    @GET("Gamercalls.json")
    suspend fun getGamerCalls():List<GamerCalls>?

    @POST("gamerCalls/.json")
    suspend fun postGamerCall(@Body gamerCall: GamerCalls)


}








