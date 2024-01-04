package com.example.partyfinder.ui.theme

import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.ChatScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class chatsScreenViewModel:ViewModel(){
    private val _chatsScreenUiState = MutableStateFlow(ChatScreenUiState())
    val chatsScreenUiState:StateFlow<ChatScreenUiState> =_chatsScreenUiState.asStateFlow()
}