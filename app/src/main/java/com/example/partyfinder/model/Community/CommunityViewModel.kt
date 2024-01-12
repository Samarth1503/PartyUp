package com.example.partyfinder.model.Community

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.Login.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommunityViewModel: ViewModel() {
    private val TAG = CommunityViewModel::class.simpleName

    private val _communityUIState = MutableStateFlow(CommunityUIState())
    val communityViewModel: StateFlow<CommunityUIState> = _communityUIState.asStateFlow()

}