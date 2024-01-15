package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.ViewModel
import com.example.partyfinder.model.GamersCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamerCallsViewModel : ViewModel() {
    private val _GamerCallsUiState = MutableStateFlow(GamersCallUiState())
    val GamerCallsUiState:StateFlow<GamersCallUiState> =_GamerCallsUiState.asStateFlow()
}