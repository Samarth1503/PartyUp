package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.CommunityPost
import com.example.partyfinder.model.uiEvent.PostUIEvent
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PostViewModel: ViewModel() {
    private val TAG = PostViewModel::class.simpleName

    private val _communityPostUIState = MutableStateFlow(CommunityPost())
    val communityPostUIState: StateFlow<CommunityPost> = _communityPostUIState.asStateFlow()

    private var isUpdated = false

    fun onEvent(event: PostUIEvent) {
        when (event) {
            is PostUIEvent.PostLiked -> {
                _communityPostUIState.update { currentState -> currentState.copy(
                    likes = _communityPostUIState.value.likes + 1 ) }
                printState()
                if (!isUpdated) {
                    updatePostDetails()
                    isUpdated = true
                }
            }
            is PostUIEvent.PostUnLiked -> {
                _communityPostUIState.update { currentState -> currentState.copy(
                    likes = _communityPostUIState.value.likes - 1 ) }
                printState()
                if (!isUpdated) {
                    updatePostDetails()
                    isUpdated = true
                }
            }
            is PostUIEvent.PostReported -> {
                _communityPostUIState.update { currentState -> currentState.copy(
                    reports = _communityPostUIState.value.reports + 1 ) }
                printState()
                if (!isUpdated) {
                    updatePostDetails()
                    isUpdated = true
                }
            }
            is PostUIEvent.PostShared -> {
                _communityPostUIState.update { currentState -> currentState.copy(
                    shareCount = _communityPostUIState.value.shareCount + 1 ) }
                printState()
                if (!isUpdated) {
                    updatePostDetails()
                    isUpdated = true
                }
            }
        }
    }


    fun postDataLoading(
        id: String,
        postLikes: Int,
        postReports: Int,
        community: String
    ){
        _communityPostUIState.update { currentState -> currentState.copy(
            postId = id,
            likes = postLikes,
            reports = postReports,
            communityName = community
        ) }
    }

    private fun updatePostDetails(){
        val db = FirebaseDatabase.getInstance().getReference("communities").child("data").child(_communityPostUIState.value.communityName).child("communityPosts").child(_communityPostUIState.value.postId)

        db.child("likes").setValue(_communityPostUIState.value.likes)
        db.child("reports").setValue(_communityPostUIState.value.likes)
        db.child("communityName").setValue(_communityPostUIState.value.communityName)
        db.child("shares").setValue(_communityPostUIState.value.shareCount)
            .addOnSuccessListener {
                Log.d("PostVM updatePostDetails TestCase", "Realtime Database update successful!")
                isUpdated = false // Reset the flag after successful update
            }
            .addOnFailureListener { e ->
                Log.w("PostVM updatePostDetails TestCase", "Error updating Realtime Database", e)
                isUpdated = false // Reset the flag after failed update
            }
    }

    private fun printState(){
        Log.d("PostVM $TAG", "InsideStack")
        Log.d("PostVM $TAG", _communityPostUIState.value.toString())
    }
}