package com.example.partyfinder.ui.theme.ViewModels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.data.repositories.networkGamerCallsRepository
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.Status
import com.example.partyfinder.model.local.LocalUser
import com.example.partyfinder.model.uiState.ProfileUiState
import com.example.partyfinder.model.uiState.Ranks
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Suppress("NAME_SHADOWING")
class ProfileViewModel(val userUIDSharedViewModel : UserUIDSharedViewModel, val retrievedUserUID:String?, private val userRepository: LocalUserRepository) : ViewModel() {

    val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    var selectedStatus by mutableStateOf(Status())

    private var _gamerCallList = MutableLiveData<GamerCallsList>()
    val gamerCallList: LiveData<GamerCallsList> get() = _gamerCallList

    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID

    init {
        viewModelScope.launch {

            if(retrievedUserUID != null){
                _currentUserUID.value = retrievedUserUID!!
                fetchData(_currentUserUID.value)
            }
            else{
                while (isActive){
                    _currentUserUID.value = userUIDSharedViewModel.currentUserUID.value
                    delay(1000)
                }
                fetchData(_currentUserUID.value)

            }

        }

        viewModelScope.launch {
            while(isActive){
                    backGroundGetGamerCall()
                    delay(1000)
            }
        }

        viewModelScope.launch {
            while (isActive){
                getHomeScreenGamerCalls()
                delay(14400000)
            }
        }
    }


    fun uploadImage(picUri : Uri, type : String){
        Log.d("UploadImage", "Starting image upload process for $type picture")
        val storage = FirebaseStorage.getInstance()
        Log.d("UploadImage", "FirebaseStorage instance obtained")

        val storageRef = storage.reference.child("UserProfileImages/").child(_currentUserUID.value!!).child(type)
        Log.d("UploadImage", "Storage reference created")

        val uploadTask = storageRef.putFile(picUri)
        Log.d("UploadImage", "Upload task created")

        uploadTask.addOnSuccessListener {
            Log.d("UploadImage", "Upload task succeeded")
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val downloadUrl = uri.toString()
                Log.d("UploadImage", "Download URL obtained: $downloadUrl")

                if(type == "profile"){
                    _profileUiState.update { currentState -> currentState.copy(
                        profilePic = downloadUrl
                    ) }
                } else {
                    _profileUiState.update { currentState -> currentState.copy(
                        coverImageLink = downloadUrl
                    ) }
                }
                Log.d("UploadImage", "Profile UI state updated with \n1: ${_profileUiState.value.profilePic}\n2: ${_profileUiState.value.coverImageLink}")
            }
        }.addOnFailureListener {
            Log.d("UploadImage", "Upload task failed", it)
        }
    }

    suspend fun backGroundGetGamerCall() {
       if(currentUserUID.value != null){
           _gamerCallList.value = networkGamerCallsRepository.getUserGamerCalls(currentUserUID.value!!).value
           val response = _gamerCallList.value
           if (response != null) {
               gamerCallList.observeForever { response ->
                   _profileUiState.update { currentState -> currentState.copy(
                       UserGamerCalls = response
                   ) }
               }
           }
       }
    }

    fun onGamerIDChanged(gamerID: String) {
        _profileUiState.update { currentState -> currentState.copy(
            gamerID = gamerID
        )}
    }

    fun onGamerTagChanged(gamerTAG: String) {
        _profileUiState.update { currentState -> currentState.copy(
            gamerTag = gamerTAG
        )}
    }

    fun onBioChanged(gamerBio: String) {
        _profileUiState.update { currentState -> currentState.copy(
            bio = gamerBio
        )}
    }

    fun updateRank1(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank1GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank1GameRank = updatedGameRank
            )}
        }
    }
    fun updateRank2(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank2GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank2GameRank = updatedGameRank
            )}
        }
    }
    fun updateRank3(fieldNo: Int, updatedGameName: String, updatedGameRank: String) {
        when (fieldNo) {
            1 -> _profileUiState.update { currentState -> currentState.copy(
                rank3GameName = updatedGameName
            )}
            2 -> _profileUiState.update { currentState -> currentState.copy(
                rank3GameRank = updatedGameRank
            )}
        }
    }

    fun onSaveChangesClicked(navigateToHomeScreen: () -> Unit) {
        Log.d("onSaveChangesClicked UploadImage 1", "Profile UI state updated with \n1: ${_profileUiState.value.profilePic}\n2: ${_profileUiState.value.coverImageLink}")
        if (currentUserUID.value != "") {
            val db = FirebaseDatabase.getInstance().getReference("users").child("data").child(currentUserUID.value!! )

            db.child("gamerID").setValue(_profileUiState.value.gamerID)
            db.child("gamerTag").setValue(_profileUiState.value.gamerTag)
            db.child("bio").setValue(_profileUiState.value.bio)
            db.child("rank1GameName").setValue(_profileUiState.value.rank1GameName)
            db.child("rank1GameRank").setValue(_profileUiState.value.rank1GameRank)
            db.child("rank2GameName").setValue(_profileUiState.value.rank2GameName)
            db.child("rank2GameRank").setValue(_profileUiState.value.rank2GameRank)
            db.child("rank3GameName").setValue(_profileUiState.value.rank3GameName)
            db.child("rank3GameRank").setValue(_profileUiState.value.rank3GameRank)
            db.child("coverImageLink").setValue(_profileUiState.value.coverImageLink)
            db.child("profilePic").setValue(_profileUiState.value.profilePic)
                .addOnSuccessListener { Log.d("onSaveChangesClicked TestCase", "Realtime Database update successful!")
                    val editedProfileData = db.get()
                    editedProfileData.addOnSuccessListener { dataSnapshot ->
                        val profileData = dataSnapshot.getValue(ProfileUiState::class.java)
                        Log.d("onSaveChangesClicked TestCase 1", "Profile data: $profileData")

                        Log.d("onSaveChangesClicked UploadImage 2", "Profile UI state updated with \n1: ${_profileUiState.value.profilePic}\n2: ${_profileUiState.value.coverImageLink}")
                    }
                }
                .addOnFailureListener { e -> Log.w("onSaveChangesClicked TestCase", "Error updating Realtime Database", e) }

        } else {
            Log.d("onSaveChangesClicked TestCase", "No local UID found")
        }

        navigateToHomeScreen()
    }

    fun updateStatus(changedStatus: Status) {
        Log.d("PVM TestCase", "updateStatus $changedStatus")
        Log.d("PVM TestCase", "updateStatus 2 ${_profileUiState.value.status}")
        _profileUiState.update { currentState -> currentState.copy(
            status = changedStatus
        )}
        selectedStatus = changedStatus
        Log.d("PVM TestCase", "updateStatus 3 ${changedStatus}")
    }

    fun onChangeStatusClicked(isExpanded: Boolean, selectedStatus1: Status) {
        Log.d("PVM TestCase", "onChangeStatusClicked $selectedStatus")
        if (isExpanded) {
            Log.d("PVM TestCase", "onChangeStatusClicked 1 ${_profileUiState.value.status}")
            _profileUiState.update { currentState -> currentState.copy(
                status = selectedStatus1,
                isChangeStatusExpanded = !isExpanded
            )}
            selectedStatus = selectedStatus1
            Log.d("PVM TestCase", "onChangeStatusClicked 2 ${_profileUiState.value.status}")
        } else {
            Log.d("PVM TestCase", "onChangeStatusClicked 3 ${_profileUiState.value.status}")
            _profileUiState.update { currentState -> currentState.copy(
                isChangeStatusExpanded = !isExpanded
            )}
            Log.d("PVM TestCase", "onChangeStatusClicked 4 ${_profileUiState.value.status}")
        }
        if (currentUserUID.value != "") {
            val db = FirebaseDatabase.getInstance().getReference("users")

            db.child("data").child(currentUserUID.value!! ).child("status").setValue(_profileUiState.value.status)
                .addOnSuccessListener { Log.d("onSaveChangesClicked TestCase", "Realtime Database update successful!")
                }
                .addOnFailureListener { e -> Log.w("onSaveChangesClicked TestCase", "Error updating Realtime Database", e) }
        } else {
            Log.d("onSaveChangesClicked TestCase", "No local UID found")
        }
    }

    fun getProfileRanks(): List<Ranks> {
        val ranks = mutableListOf<Ranks>()

        if (_profileUiState.value.rank1GameName != "") {
            ranks.add(Ranks(1, _profileUiState.value.rank1GameName, _profileUiState.value.rank1GameRank))
        }

        if (_profileUiState.value.rank2GameName != "") {
            ranks.add(Ranks(2, _profileUiState.value.rank2GameName, _profileUiState.value.rank2GameRank))
        }

        if (_profileUiState.value.rank3GameName != "") {
            ranks.add(Ranks(3, _profileUiState.value.rank3GameName, _profileUiState.value.rank3GameRank))
        }

        return ranks
    }



    suspend fun fetchData(currentUserUID: String?) = withContext(Dispatchers.IO) {
        Log.d("ProfileViewModel TestCase", "FetchData() called")

        val db = FirebaseDatabase.getInstance().getReference("users")
        if (currentUserUID.isNullOrEmpty()) {
            Log.e("ProfileVM FetchDataTrial TestCase", "currentUserUID is null or empty")
            return@withContext
        }

        val dataFetchTask = db.child("data").child(currentUserUID).get()

        try {
            val dataSnapshot = Tasks.await(dataFetchTask)
            val firebaseProfileData = dataSnapshot.getValue(ProfileUiState::class.java)
            if (firebaseProfileData != null) {
                Log.d("ProfileVM FetchDataTrial TestCase", firebaseProfileData.toString())
                // Convert FirebaseProfileUiState to ProfileUiState
                withContext(Dispatchers.Main) {
                    _profileUiState.update { currentState ->
                        currentState.copy(
                            gamerID = firebaseProfileData.gamerID,
                            gamerTag = firebaseProfileData.gamerTag,
                            status = firebaseProfileData.status,
                            bio = firebaseProfileData.bio,
                            rank1GameName = firebaseProfileData.rank1GameName,
                            rank1GameRank = firebaseProfileData.rank1GameRank,
                            rank2GameName = firebaseProfileData.rank2GameName,
                            rank2GameRank = firebaseProfileData.rank2GameRank,
                            rank3GameName = firebaseProfileData.rank3GameName,
                            rank3GameRank = firebaseProfileData.rank3GameRank,
                            coverImageLink = firebaseProfileData.coverImageLink,
                            profilePic = firebaseProfileData.profilePic,
                            UserGamerCalls = firebaseProfileData.UserGamerCalls
                        )
                    }
                }
                Log.d("ProfileVM FetchDataTrial TestCase", "Data fetched successfully")
            } else {
                Log.e("FetchDataTrial TestCase", "No data available")
            }
        } catch (e: Exception) {
            Log.e("FetchDataTrial TestCase", "Error fetching data", e)
        }
    }

    suspend fun getHomeScreenGamerCalls(){
           if (currentUserUID.value != null){
               viewModelScope.launch {
                   val list = networkGamerCallsRepository.getRandom4GamerCalls(currentUserUID.value!!)
                   _profileUiState.update { currentState -> currentState.copy(
                       random4GamerCallsOnHomeScreen = list
                   ) }
               }
           }
    }

    fun logoutUser(redirectToLogin:()->Unit) {
        FirebaseAuth.getInstance().signOut()
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("LoginProcessTestCase", "upsert() started")
            userRepository.upsert(LocalUser(id = 0, userEmail = "", userUID = ""))
            Log.d("LoginProcessTestCase", "upsert() ended")
        }
        redirectToLogin()
    }
}