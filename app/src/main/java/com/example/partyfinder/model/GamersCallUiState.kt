package com.example.partyfinder.model

import com.example.partyfinder.data.GamerCalls
import com.example.partyfinder.datasource.datasource

data class GamersCallUiState(
    val listOfGamersCall: List<GamerCalls> = datasource.GamerCalls
)
