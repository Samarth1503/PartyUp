package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.GamerCalls

data class GamersCallUiState(
    val listOfGamersCall: List<GamerCalls> = datasource.GamerCalls
)
