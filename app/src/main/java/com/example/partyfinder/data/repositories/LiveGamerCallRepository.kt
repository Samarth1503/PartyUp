package com.example.partyfinder.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.LiveGamerCall
import com.example.partyfinder.model.LiveGamerCallList
import com.example.partyfinder.model.LiveGamerCallSearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

interface LiveGamerCallRepository{
    suspend fun postLiveGamerCall(liveGamerCall: LiveGamerCall):Response<FirebaseResponse>

    suspend fun fetchLiveGamerCallList()
    suspend fun updateLiveGamerCall(liveGamerCallId:String,liveGamerCall:LiveGamerCall):Response<ResponseBody>

    suspend fun liveGamerCallSearchResult():List<LiveGamerCallSearchResult>
}

object networkLiveGamerCallRepository : LiveGamerCallRepository{
    val _liveGamerCallList =MutableLiveData<LiveGamerCallList>()
    val liveGamerCallList :LiveData<LiveGamerCallList> get() = _liveGamerCallList

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive){
                delay(5000)
                fetchLiveGamerCallList()
            }
        }
    }

    override suspend fun fetchLiveGamerCallList() {
        try {
            val response = LiveGamerCallApiService.getLiveGamerCallList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _liveGamerCallList.value = response.body()
                    Log.d("FetchingDatabase", "Successfully fetched live gamer call list")
                    Log.d("FetchingDatabase", "Live Gamer Call List: ${response.body()}")
                } else {
                    Log.e("FetchingDatabase", "Error Fetching live gamer call list. Response code: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Log.e("FetchingDatabase", "Error fetching data: ${e.message}")
        }
    }

    override suspend fun postLiveGamerCall(liveGamerCall: LiveGamerCall):Response<FirebaseResponse> {
           val response = LiveGamerCallApiService.postLiveGamerCalls(liveGamerCall = liveGamerCall)
        return response
    }

    override suspend fun updateLiveGamerCall(
        liveGamerCallId: String,
        liveGamerCall: LiveGamerCall
    ): Response<ResponseBody> {
        val response = LiveGamerCallApiService.updateLiveGamerCall(liveGamerCallId, liveGamerCall)
        return response
    }

    override suspend fun liveGamerCallSearchResult(): List<LiveGamerCallSearchResult> {
        val resultList = mutableListOf<LiveGamerCallSearchResult>()

        liveGamerCallList.value?.liveGamerCallList?.values?.forEach { resultLiveGamerCall ->
            try {
                val userAccountResponse = UserApiService.getUserAccount(resultLiveGamerCall.uid)

                if (userAccountResponse.isSuccessful) {
                    val userAccount = userAccountResponse.body()
                    if (userAccount != null) {
                        val liveGamerCallResultObject = LiveGamerCallSearchResult(
                            liveGamerCallObject = resultLiveGamerCall,
                            userAccount = userAccount
                        )
                        resultList.add(liveGamerCallResultObject)
                        Log.d("liveGamerCallResultObject", liveGamerCallResultObject.toString())
                    } else {
                        Log.e("FetchingDatabase", "User account data is null")
                    }
                } else {
                    Log.e("FetchingDatabase", "Error fetching user account data. Code: ${userAccountResponse.code()}")
                }
            } catch (e: Exception) {
                Log.e("FetchingDatabase", "Exception while fetching user account data", e)
            }
        }

        Log.d("resultList", resultList.toString())
        return resultList
    }




}