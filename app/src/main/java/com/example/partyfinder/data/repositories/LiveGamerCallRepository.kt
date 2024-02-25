package com.example.partyfinder.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.LiveGamerCall
import com.example.partyfinder.model.LiveGamerCallList
import com.example.partyfinder.model.LiveGamerCallSearchResult
import okhttp3.ResponseBody
import retrofit2.Response

interface LiveGamerCallRepository{
    suspend fun postLiveGamerCall(liveGamerCall: LiveGamerCall):Response<FirebaseResponse>

    suspend fun getLiveGamerCallList():LiveData<LiveGamerCallList>
    suspend fun updateLiveGamerCall(liveGamerCallId:String,liveGamerCall:LiveGamerCall):Response<ResponseBody>

    suspend fun liveGamerCallSearchResult():List<LiveGamerCallSearchResult>
}

object networkLiveGamerCallRepository : LiveGamerCallRepository{
    val _liveGamerCallList =MutableLiveData<LiveGamerCallList>()
    val liveGamerCallList :LiveData<LiveGamerCallList> get() = _liveGamerCallList


    override suspend fun getLiveGamerCallList(): LiveData<LiveGamerCallList> {
        val response = LiveGamerCallApiService.getLiveGamerCallList()
        if (response.isSuccessful){
            _liveGamerCallList.value = response.body()
        }
        return liveGamerCallList
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

        liveGamerCallList.value!!.liveGamerCallList.values.forEach {resultLiveGamerCall ->
            val liveGamerCallResultObject = LiveGamerCallSearchResult(
                liveGamerCallObject = resultLiveGamerCall,
                userAccount = datasource.UserAccounts.find {it.gamerID == resultLiveGamerCall.gamerId}!!
            )
            resultList.add(liveGamerCallResultObject)
        }
        return resultList
    }

}