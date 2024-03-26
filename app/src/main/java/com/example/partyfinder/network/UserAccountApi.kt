package com.example.partyfinder.network

import com.example.partyfinder.model.UserAccount
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserAccountApi {
    @GET("users/{firebaseUID}.json")
    suspend fun getUserAccount(@Path("firebaseUID")userID:String):Response<UserAccount>

//    @PUT("users/data/{firebaseUID}.json")
//    suspend fun
}