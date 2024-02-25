package com.example.partyfinder.data.repositories

import com.example.partyfinder.network.ChatChannelApi
import com.example.partyfinder.network.GamerCallApi
import com.example.partyfinder.network.LiveGamerCallApi
import com.example.partyfinder.network.RequestInterceptor
import com.example.partyfinder.network.UserAccountApi
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val BASE_URL = "https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app/"

val gson = GsonBuilder().setLenient().create()


val okHttpClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(RequestInterceptor)
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
    .baseUrl(BASE_URL)
    .build()

val GamerCallApiService : GamerCallApi by lazy{
    retrofit.create(GamerCallApi :: class.java)
}

val ChatChannelApiService :ChatChannelApi by lazy{
    retrofit.create(ChatChannelApi :: class.java)
}

val LiveGamerCallApiService:LiveGamerCallApi by lazy {
    retrofit.create(LiveGamerCallApi :: class.java)
}

val UserApiService:UserAccountApi by lazy {
    retrofit.create(UserAccountApi :: class.java)
}
