package com.example.partyfinder.model.Community

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PostViewModel: ViewModel(){
    private val TAG = PostViewModel::class.simpleName

    private val _postUIState = MutableStateFlow(PostUIState())
    val postUIState: StateFlow<PostUIState> = _postUIState.asStateFlow()

    fun onEvent(event: PostUIEvent){
        when (event) {
            is PostUIEvent.PostLiked -> {
                _postUIState.update { currentState -> currentState.copy(
                    likes = _postUIState.value.likes+1 ) }
                printState()
            }
            is PostUIEvent.PostUnLiked -> {
                _postUIState.update { currentState -> currentState.copy(
                    likes = _postUIState.value.likes-1 ) }
                printState()
            }
            is PostUIEvent.PostReported -> {
                _postUIState.update { currentState -> currentState.copy(
                    reports = _postUIState.value.reports+1 ) }
                printState()
            }
            is PostUIEvent.PostShared -> {
                _postUIState.update { currentState -> currentState.copy(
                    shareCount = _postUIState.value.shareCount+1 ) }
                printState()
            }
        }
    }

    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, _postUIState.value.toString())
    }
}