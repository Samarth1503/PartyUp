package com.example.partyfinder.ui.theme.ViewModels

import androidx.lifecycle.ViewModel
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.model.uiState.GamersCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamerCallsViewModel (userRepository: LocalUserRepository): ViewModel() {
    private val _GamerCallsUiState = MutableStateFlow(GamersCallUiState())
    val GamerCallsUiState:StateFlow<GamersCallUiState> =_GamerCallsUiState.asStateFlow()

//    init{
//         viewModelScope.launch {
//             try{
//                 var list: GamerCallsList
//                 list= networkGamerCallsRepository.getGamerCalls().value!!
//                 _GamerCallsUiState.update { currentState -> currentState.copy(
//                     listOfGamersCall = list
//                 ) }
//             }catch (e : HttpException){
//                 Log.d(ContentValues.TAG,"error in fetching data")
//             }
//         }
//    }
}