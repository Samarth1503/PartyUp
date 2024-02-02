package com.example.partyfinder.network

import com.example.partyfinder.data.GamerCalls
import retrofit2.http.GET

interface GamerCallApi {
    @GET
    suspend fun getGamerCalls():GamerCalls
}



