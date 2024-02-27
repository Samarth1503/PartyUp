package com.example.partyfinder.data.repositories

import com.example.partyfinder.model.CommunitiesList
import com.example.partyfinder.model.CommunityPost
import com.example.partyfinder.model.FirebaseResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface CommunitiesRepository {
    suspend fun postCommunityUserPost(userPost:CommunityPost):Response<FirebaseResponse>

    suspend fun fetchCommunityData():Response<CommunitiesList>

    suspend fun updatePost(postID:String,communityPost:CommunityPost):Response<ResponseBody>
}

object networkCommunityRepository:CommunitiesRepository{

    override suspend fun postCommunityUserPost(userPost: CommunityPost): Response<FirebaseResponse> {
        val response =CommunityApiService.postCommunityUserPost(userPost = userPost)
        return response
    }


    override suspend fun fetchCommunityData(): Response<CommunitiesList> {
        val response = CommunityApiService.fetchCommunityData()
        return response
    }

    override suspend fun updatePost(
        postID: String,
        communityPost: CommunityPost
    ): Response<ResponseBody> {
        val response = CommunityApiService.updateCommunityPost(communityPostId = postID, communityPost = communityPost)
        return response
    }
}