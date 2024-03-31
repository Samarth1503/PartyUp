package com.example.partyfinder.ui.theme.ViewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.UserApiService
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.uiState.CreateGamerCallsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CreateGamerCallsViewModel(
    val userUIDSharedViewModel : UserUIDSharedViewModel,
    val retrievedUserUID:String?,
    private val application: Application
) :ViewModel() {
    private val _CreateGamerCallsUiState = MutableStateFlow(CreateGamerCallsUiState())
    val CreateGamerCallUiState:StateFlow<CreateGamerCallsUiState> = _CreateGamerCallsUiState.asStateFlow()

    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID
    init {
        if (retrievedUserUID == null){
            viewModelScope.launch {
                while (isActive){
                    _currentUserUID.value = userUIDSharedViewModel.currentUserUID.value
                    delay(1000)
                }
            }
        }
        else{
            _currentUserUID.value = retrievedUserUID!!
        }
    }

    fun onGameNameValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            gameName = newValue
        ) }
    }
    fun onNoOfGamersValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            noOfGamers = newValue
        ) }
    }
    fun onCallDescriptionValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            DescriptionOfCall = newValue
        ) }
    }
    fun onCallDurationValueChange(newValue:String){
        _CreateGamerCallsUiState.update { currentState -> currentState.copy(
            CallDuration = newValue
        ) }
    }

    fun postGamerCall(
        navigateAfterPost: () -> Unit,
        GamerProfilePic: String,
        GamerID: String,
        GamerTag: String
    ) {
        var gamerCall = GamerCalls(
            gamerCallID = "",
            ProfilePic = GamerProfilePic,
            callDes = _CreateGamerCallsUiState.value.DescriptionOfCall,
            gameName = _CreateGamerCallsUiState.value.gameName,
            userUID = currentUserUID.value!!,
            gamerID = GamerID,
            gamerTag = GamerTag,
            partySize = _CreateGamerCallsUiState.value.noOfGamers.toInt(),
            callDuration = _CreateGamerCallsUiState.value.CallDuration
        )

        viewModelScope.launch {
            val response = networkGamerCallsRepository.postGamerCall(gamerCall)
            if (response.isSuccessful) {
                Log.d("Posting GamerCall", "GamerCall Posted Successfully")

                val gamerCallID = response.body()!!.name
                gamerCall.gamerCallID = gamerCallID

                val updateResponse = networkGamerCallsRepository.updateGamerCall(gamerCallID = gamerCallID, gamerCall = gamerCall)
                if (updateResponse.isSuccessful){
                    val userAccount = UserApiService.getUserAccount(currentUserUID.value!!).body()
                    if (userAccount != null) {
                        userAccount.userGamerCallsList = userAccount.userGamerCallsList.toMutableList().apply { add(gamerCallID) }
                        UserApiService.updateUserAccount(currentUserUID.value!!,userAccount)
                    }
                    else{
                        Log.d("Posting GamerCall","User Account retrieved was empty")
                    }

                    Log.d("Posting GamerCall","GamerCall Updated Successfully")
                if (updateResponse.isSuccessful) {
                    Log.d("Posting GamerCall", "GamerCall Updated Successfully \n${gamerCallID}")

                    launch(Dispatchers.Default) {
                        scheduleGamerCallDeletion(gamerCallID, _CreateGamerCallsUiState.value.CallDuration.toInt())
                    }

                    Log.d("Posting GamerCall", "navigateAfterPost() reached")
                    navigateAfterPost()
                }
            }
        }
    }


    private fun scheduleGamerCallDeletion(gamerCallID: String, gamerCallDuration: Int) {
        Log.d("Deleting GamerCall", "scheduleGamerCallDeletion() called")
        val delayMillis = (gamerCallDuration * 3600000).toLong()

        // Create a Data object with the gamerCallID
        val data = workDataOf("gamerCallID" to gamerCallID)

        // Create a OneTimeWorkRequest with the delay and the Data
        val workRequest = OneTimeWorkRequestBuilder<DeleteGamerCallWorker>()
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        // Enqueue the WorkRequest
        WorkManager.getInstance(application).enqueue(workRequest)
    }
}


class DeleteGamerCallWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        Log.d("Deleting GamerCall", "DeleteGamerCallWorker() - doWork() called")
        val gamerCallID = inputData.getString("gamerCallID")

        val deleteResponse = networkGamerCallsRepository.deleteGamerCall(gamerCallId = gamerCallID!!)
            if (deleteResponse.isSuccessful) {
                Log.d("Deleting GamerCall", "GamerCall Deleted Successfully")
            } else {
                Log.d("Deleting GamerCall", "GamerCall Delete Unsuccessful")
            }
        return Result.success()
    }
}