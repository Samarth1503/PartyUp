package com.example.partyfinder.network

import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.UserAccount
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserAccountApi {
    @GET("users/data/{firebaseUID}.json")
    suspend fun getUserAccount(@Path("firebaseUID")userID:String):Response<UserAccount>

    @PUT("users/data/{firebaseUID}.json")
    suspend fun updateUserAccount(@Path("firebaseUID")userID: String, @Body userObject:UserAccount):Response<FirebaseResponse>

    @PUT("users/data/{firebaseUID}/liveGamerCallID.json")
    suspend fun updateLiveGamerCallID(@Path("firebaseUID")userID: String, @Body liveGamerCallID:String)

    @PUT("users/data/{firebaseUID}/userGamerCallsList.json")
    suspend fun uploadGamerCallID(@Path("firebaseUID")userID:String,@Body gamerCallID:String)
}