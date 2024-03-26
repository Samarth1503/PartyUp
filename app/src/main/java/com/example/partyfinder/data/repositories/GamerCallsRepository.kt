 package com.example.partyfinder.data.repositories

 import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.partyfinder.model.FirebaseResponse
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.model.GamerCallsList
import okhttp3.ResponseBody
import retrofit2.Response


 interface GamerCallsRepository{
  suspend fun getGamerCalls():LiveData<GamerCallsList>

  suspend fun postGamerCall(gamerCalls: GamerCalls):Response<FirebaseResponse>

  suspend fun updateGamerCall(gamerCallID:String,gamerCall:GamerCalls):Response<ResponseBody>
 }





 object networkGamerCallsRepository:GamerCallsRepository{



  val data =MutableLiveData<GamerCallsList>()
  val livedata:LiveData<GamerCallsList> get() = data
  override suspend fun getGamerCalls():LiveData<GamerCallsList> {

    data.value = GamerCallApiService.getGamerCalls()
    return livedata

  }

  override suspend fun postGamerCall(gamerCall: GamerCalls):Response<FirebaseResponse> {
   val response =GamerCallApiService.postGamerCall(gamerCall)
   return response
  }

  override suspend fun updateGamerCall(
   gamerCallID: String,
   gamerCall: GamerCalls
  ): Response<ResponseBody> {
   val response = GamerCallApiService.updateLiveGamerCall(gamerCallId = gamerCallID, gamerCall = gamerCall)
   return response
  }


 }
