package com.example.partyfinder.data.repositories

 import androidx.lifecycle.LiveData
 import androidx.lifecycle.MutableLiveData
 import com.example.partyfinder.model.FirebaseResponse
 import com.example.partyfinder.model.GamerCalls
 import com.example.partyfinder.model.GamerCallsList
 import okhttp3.ResponseBody
 import retrofit2.Response
 import kotlin.random.Random


 interface GamerCallsRepository{
  suspend fun getUserGamerCalls(currentUserUID:String):LiveData<GamerCallsList>

  suspend fun postGamerCall(gamerCalls: GamerCalls):Response<FirebaseResponse>

 suspend fun getGamerCallsToDisplay(currentUserUID: String):LiveData<GamerCallsList>
 suspend fun updateGamerCall(gamerCallID:String,gamerCall:GamerCalls):Response<ResponseBody>
 suspend fun deleteGamerCall(gamerCallId: String): Response<ResponseBody>
 }






object networkGamerCallsRepository : GamerCallsRepository {



  val data =MutableLiveData<GamerCallsList>()
  val livedata:LiveData<GamerCallsList> get() = data

  val _gamerCallsToDisplay = MutableLiveData<GamerCallsList>()
  val gamerCallsToDisplay :LiveData<GamerCallsList> get() = _gamerCallsToDisplay
  override suspend fun getUserGamerCalls(currentUserUID:String):LiveData<GamerCallsList> {
    val userAccount = UserApiService.getUserAccount(currentUserUID).body()
    val userGamerCallsMap = mutableMapOf<String,GamerCalls>()
    val userGamerCallsList = GamerCallsList(gamerCalls = userGamerCallsMap)

   if (userAccount != null) {
    userAccount.userGamerCallsList.forEach { gamerCallID ->
        var gamerCallObject = GamerCallApiService.getGamerCallObject(gamerCallID).body()
        if (gamerCallObject != null) {
          userGamerCallsMap.put(gamerCallID,gamerCallObject)
         }
       }
   }

    data.value = userGamerCallsList
    return livedata

  }

 override suspend fun postGamerCall(gamerCall: GamerCalls): Response<FirebaseResponse> {
  val response = GamerCallApiService.postGamerCall(gamerCall)
  return response
 }

  override suspend fun updateGamerCall(
   gamerCallID: String,
   gamerCall: GamerCalls
  ): Response<ResponseBody> {
   val response = GamerCallApiService.updateLiveGamerCall(gamerCallId = gamerCallID, gamerCall = gamerCall)
   return response
  }

  override suspend fun getGamerCallsToDisplay(currentUserUID: String): LiveData<GamerCallsList> {
  //fetching all the GamerCalls in database
   val response = GamerCallApiService.getGamerCalls()
   if (response.isSuccessful){
    val unfilteredGamerCallList = response.body()
    var gamerCallList = mutableMapOf<String,GamerCalls>()

    //filtering gamerCalls for not containing users own gamerCalls
    if (unfilteredGamerCallList != null) {
     unfilteredGamerCallList.gamerCalls.values.forEach {
      if ( it.userUID != currentUserUID){
       gamerCallList.put(it.gamerCallID,it)
      }
     }
    }

    val filteredGamerCallList = GamerCallsList(gamerCalls = gamerCallList)

    _gamerCallsToDisplay.value = filteredGamerCallList
   }
   return gamerCallsToDisplay
  }


  suspend fun getRandom4GamerCalls(currentUserUID: String): GamerCallsList {
   val response = GamerCallApiService.getGamerCalls()
   if (response.body()?.gamerCalls?.isNotEmpty() == true) {
    val allGamerCalls = response.body()!!.gamerCalls.values.toList()

    // Filter out user's own gamer calls
    val filteredGamerCalls = allGamerCalls.filter { it.userUID != currentUserUID }

    if (filteredGamerCalls.isNotEmpty()) {
     val randomIndices = List(4) { Random.nextInt(filteredGamerCalls.size) }
     val randomGamerCalls = randomIndices.map { filteredGamerCalls[it] }
     val randomGamerCallMap = randomGamerCalls.associateBy { it.gamerCallID }
     return GamerCallsList(randomGamerCallMap)
    }
   }

   // Handle the case when no valid gamer calls are available
   return GamerCallsList(emptyMap())
  }
 override suspend fun deleteGamerCall(gamerCallId: String): Response<ResponseBody> {
  val response = GamerCallApiService.deleteGamerCall(gamerCallId)
  return response
 }
 }
