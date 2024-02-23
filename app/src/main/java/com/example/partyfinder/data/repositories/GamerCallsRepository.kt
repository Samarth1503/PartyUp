 package com.example.partyfinder.data.repositories

 import androidx.lifecycle.LiveData
 import androidx.lifecycle.MutableLiveData
 import com.example.partyfinder.model.GamerCalls
 import com.example.partyfinder.model.GamerCallsList


 interface GamerCallsRepository{
  suspend fun getGamerCalls():LiveData<GamerCallsList>

  suspend fun postGamerCall(gamerCalls: GamerCalls)
 }





 object networkGamerCallsRepository:GamerCallsRepository{
  val data =MutableLiveData<GamerCallsList>()
  val livedata:LiveData<GamerCallsList> get() = data
  override suspend fun getGamerCalls():LiveData<GamerCallsList> {

    data.value = GamerCallApiService.getGamerCalls()
    return livedata

  }

  override suspend fun postGamerCall(gamerCall: GamerCalls) {
   GamerCallApiService.postGamerCall(gamerCall)
  }


 }
