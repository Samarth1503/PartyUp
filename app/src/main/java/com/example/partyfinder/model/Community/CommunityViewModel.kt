package com.example.partyfinder.model.Community

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.Login.LoginViewModel
import com.google.firebase.events.Event
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
            is CommunityUIEvent.NewPostAdded -> {
                _communityUIState.update { currentState -> currentState.copy(
                    communityPostsNumber = _communityUIState.value.communityPostsNumber+1
                ) }
                printState()
            }
        }
    }


    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, _communityUIState.value.toString())
    }
}