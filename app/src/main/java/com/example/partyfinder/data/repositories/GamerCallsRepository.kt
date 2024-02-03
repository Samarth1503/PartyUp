package com.example.partyfinder.data.repositories

import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.network.GamerCallApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app/"

val gson =GsonBuilder().setLenient().create()

private val retrofit: Retrofit = Retrofit.Builder()
 .addConverterFactory(GsonConverterFactory.create(gson))
 .baseUrl(BASE_URL)
 .build()

interface GamerCallsRepository{
 suspend fun getGamerCalls():List<GamerCalls>?

 suspend fun postGamerCall(gamerCalls: GamerCalls)
}


private val retrofitService :GamerCallApi by lazy{
retrofit.create(GamerCallApi :: class.java)
}
object networkGamerCallsRepository:GamerCallsRepository{

 override suspend fun getGamerCalls(): List<GamerCalls>? {
  return  retrofitService.getGamerCalls()
 }

 override suspend fun postGamerCall(gamerCall: GamerCalls) {
  retrofitService.postGamerCall(gamerCall)
 }
}
