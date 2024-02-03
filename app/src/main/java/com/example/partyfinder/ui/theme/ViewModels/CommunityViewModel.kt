package com.example.partyfinder.ui.theme.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.uiEvent.CommunityUIEvent
import com.example.partyfinder.model.uiState.CommunityUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CommunityViewModel: ViewModel() {
    private val TAG = CommunityViewModel::class.simpleName

    private val _communityUIState = MutableStateFlow(CommunityUIState())
    val communityViewModel: StateFlow<CommunityUIState> = _communityUIState.asStateFlow()

    fun onEvent(event: CommunityUIEvent){
        when (event){
            is CommunityUIEvent.ContentChanged -> {
                _communityUIState.update { currentState -> currentState.copy(
                    newPostContent = event.content ) }
                printState()
            }
            is CommunityUIEvent.NewPostAdded -> {
////                Add this as value for the database of new post
//                _communityUIState.value.newPostContent
                Log.d(TAG, "New Post with Content as" + _communityUIState.value.toString())
            }
        }
    }


    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, _communityUIState.value.toString())
    }
}