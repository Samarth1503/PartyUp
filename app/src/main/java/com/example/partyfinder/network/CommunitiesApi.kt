package com.example.partyfinder.network

import com.example.partyfinder.model.CommunitiesList
import com.example.partyfinder.model.CommunityPost
import com.example.partyfinder.model.FirebaseResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommunitiesApi {

    @GET("communities.json")
    suspend fun fetchCommunityData():Response<CommunitiesList>

    @POST("communities/data/{communityID}/communityPosts.json")
    suspend fun postCommunityUserPost(
        @Path("communityID") communityID:String,
        @Body userPost: CommunityPost): Response<FirebaseResponse>

    @PUT("communities/data/{communityID}/communityPosts/{CommunityPostID}.json")
    suspend fun updateCommunityPost(
        @Path("communityID") communityID:String,
        @Path("CommunityPostID") communityPostId:String,
        @Body communityPost:CommunityPost):Response<ResponseBody>
}
