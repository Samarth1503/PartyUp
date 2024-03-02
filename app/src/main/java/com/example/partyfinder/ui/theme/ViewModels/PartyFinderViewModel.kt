package com.example.partyfinder.ui.theme.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.networkLiveGamerCallRepository
import com.example.partyfinder.model.LiveGamerCall
import com.example.partyfinder.model.LiveGamerCallRequest
import com.example.partyfinder.model.LiveGamerCallSearchResult
import com.example.partyfinder.model.uiState.PartyFinderUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class PartyFinderViewModel (val userUID:String?):ViewModel(){
    private val _partyFinderScreenUiState = MutableStateFlow(PartyFinderUiState())
    val partyFinderUiState:StateFlow<PartyFinderUiState> = _partyFinderScreenUiState.asStateFlow()

    var _liveGamerCallSearchResultList = MutableLiveData<List<LiveGamerCallSearchResult>>()
    val liveGamerCallSearchResultList : LiveData<List<LiveGamerCallSearchResult>> get() = _liveGamerCallSearchResultList

    init {
        viewModelScope.launch {
            while (isActive) {
                if (userUID != null) {
                    Log.d("Debugging User ID", userUID)
                } else {
                    Log.d("Debugging User ID", "User ID is null")
                }
                delay(2000)
            }
        }


        Timer().scheduleAtFixedRate(0, 1000) {
            viewModelScope.launch {
                partyFinderUiState.collect { currentState ->
                    if (currentState.isGamerCallLive == true) {
                        getLiveGamerCallResults(
                            gameName = currentState.gameNameSelectedValue,
                            NoOfPlayersRequired = currentState.noOfPlayerRequired,
                            NoOfPlayersinParty = currentState.noOfPlayersInParty
                        )
                        Log.d("resultsetCoRoutine", currentState.liveGamerCallResultLits.toString())
                    } else {
                        Log.d("resultsetCoRoutine", "GamerCallNot Live")
                        _partyFinderScreenUiState.update { currentState ->
                            currentState.copy(
                                liveGamerCallResultLits = null
                            )
                        }
                    }
                }
            }
        }
    }


    suspend fun getLiveGamerCallResults(gameName:String,NoOfPlayersinParty:String,NoOfPlayersRequired:String){
       var resultList:List<LiveGamerCallSearchResult>
                resultList = networkLiveGamerCallRepository.liveGamerCallSearchResult(gameName = gameName,NoOfPlayersinParty=NoOfPlayersinParty,NoOfPlayersRequired=NoOfPlayersRequired)
                _liveGamerCallSearchResultList.value= resultList
                Log.d("UpdatingViewModelData" , resultList.toString())
                _partyFinderScreenUiState.update { currentState -> currentState.copy(
                    liveGamerCallResultLits = liveGamerCallSearchResultList.value
                ) }


    }
    fun postLiveGamerCall(){
        var liveGamerCall = LiveGamerCall(
            gameName = partyFinderUiState.value.gameNameSelectedValue,
            uid = "PHOhuIFLXdMvfFRK6hOfoNKm0Yu2" ,
            gamerCallAccepted = false,
            isGamerCallLive = true,
            noOfPlayersRequired = partyFinderUiState.value.noOfPlayerRequired.toInt(),
            noOfPlayersinParty = partyFinderUiState.value.noOfPlayersInParty.toInt(),
            requestsReceived = listOf(LiveGamerCallRequest(senderGamerID = "defaultRequestItem", receiverGamerID = "defaultRequestItem", senderLiveGamerCallID = "defaultLiveGamerCallID", isAccepted = false)),
            requestsSent = listOf(LiveGamerCallRequest(senderGamerID = "defaultRequestItem", receiverGamerID = "defaultRequestItem", senderLiveGamerCallID = "defaultLiveGamerCallID", isAccepted = false)),
            liveGamerCallID="liveGamerCallID"
        )



        viewModelScope.launch {
            val response = networkLiveGamerCallRepository.postLiveGamerCall(liveGamerCall)
            liveGamerCall.liveGamerCallID = response.body()!!.name
            val updatedResponse = networkLiveGamerCallRepository.updateLiveGamerCall(response.body()!!.name,liveGamerCall)

            if (updatedResponse.isSuccessful){
                Log.d(TAG,"LiveGamerCall Posted and Updated Successfully")
            }
            else
            {
                Log.d(TAG,"Failed to Post or update livegamerCall")
            }
        }
    }
    fun onHideDetailsClicked(currentValue:Boolean){
        var updateTo =  !currentValue
        _partyFinderScreenUiState.update {currentState -> currentState.copy(
            hideDetails = updateTo
        )
        }
    }

    fun onClickClearDetails () {
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue = "",
            noOfPlayerRequired =  "",
            noOfPlayersInParty = "",
        ) }
        onStopCallClick()
    }
    fun onGameNameExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGameNameDDExtended = newValue
        ) }
    }

    fun onGameNameValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue = newValue
        ) }
    }

    fun onGameNameDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGameNameDDExtended = false
        )}
    }

    fun onGameNameItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            gameNameSelectedValue=item,
            isGameNameDDExtended = false
        ) }
    }

    fun onNoOfPartyExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerDDExtended = newValue
        ) }
    }

    fun onNoOfPartyValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayersInParty = newValue
        ) }
    }

    fun onNoOfPartyDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerDDExtended = false
        )}
    }
    fun onNoOfPartyItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayersInParty = item,
            isNoOfPlayerDDExtended = false
        ) }
    }

    fun onNoOfRequiredExpandedChanged(newValue:Boolean){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerRequiredDDExtended = newValue
        ) }
    }
    fun onNoOfRequiredValueChange(newValue:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayerRequired = newValue
        ) }
    }

    fun onNoOfRequiredDismissRequest(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isNoOfPlayerRequiredDDExtended = false
        )}
    }
    fun onNoOfRequiredItemClicked(item:String){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            noOfPlayerRequired = item,
            isNoOfPlayerRequiredDDExtended = false
        ) }
    }

    fun onSearchClick(){
        _partyFinderScreenUiState.update { currentState -> currentState.copy(
            isGamerCallLive = true
        ) }
        viewModelScope.launch {
            postLiveGamerCall()
        }
    }



    fun onStopCallClick(){
        _partyFinderScreenUiState.update { currentState ->currentState.copy(
            isGamerCallLive = false,
            liveGamerCallResultLits = null
        ) }
    }


}