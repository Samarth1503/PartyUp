package com.example.partyfinder.model.uiState

import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.GamerCallsList

data class GamersCallUiState(
    val listOfGamersCall: GamerCallsList = datasource.gamerCallsList
)
