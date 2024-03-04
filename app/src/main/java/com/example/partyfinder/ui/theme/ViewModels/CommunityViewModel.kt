package com.example.partyfinder.ui.theme.ViewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.data.repositories.networkCommunityRepository
import com.example.partyfinder.model.CommunitiesList
import com.example.partyfinder.model.Community
import com.example.partyfinder.model.CommunityPost
import com.example.partyfinder.model.uiEvent.CommunityUIEvent
import com.example.partyfinder.model.uiState.CommunityUIState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CommunityViewModel(val userRepository: LocalUserRepository): ViewModel() {
    private val TAG = CommunityViewModel::class.simpleName

    private val _communityUIState = MutableStateFlow(CommunityUIState())
    val communityUiState: StateFlow<CommunityUIState> = _communityUIState.asStateFlow()

    val dbref = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app").reference

    private val _communityList = MutableLiveData<CommunitiesList> ()
    val communityList : LiveData<CommunitiesList> get() = _communityList
    init {
        viewModelScope.launch {
            while (isActive) {
                retreiveCurrentCommunityData(communityUiState.value.communityName)
                fetchCommunitiesData()
                communityList.observeForever { response ->
                    _communityUIState.update { currentState ->
                        currentState.copy(
                            communityList = communityList.value
                        )
                    }
                }
                Log.d("CommunityData",communityUiState.value.communityObject.toString())

            }
        }
    }

    fun retreiveCurrentCommunityData(communityName:String){
        dbref.child("communities")
            .child("data")
            .child(communityName)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Handle data changes
                    val currentCommunity: Community? = snapshot.getValue(Community::class.java)

                    // Update your UI state or perform other actions
                    _communityUIState.update { currentState -> currentState.copy(
                        communityObject = currentCommunity!!
                    ) }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle errors
                    Log.e(ContentValues.TAG, "Error reading data: ${error.message}")
                }
            })
    }
    suspend fun fetchCommunitiesData(){
        val response = networkCommunityRepository.fetchCommunityData()
        if (response.isSuccessful){
            _communityList.value = response.body()
            Log.d("Fetching Community data","Fetched Community Data Successfully")
            Log.d("Fetching Community data", communityList.value.toString())
        }
        else
        {
            Log.d("Fetching Community data","Failed To fetch Community Data")
        }
    }
    fun onEvent(event: CommunityUIEvent){
        when (event){
            is CommunityUIEvent.ContentChanged -> {
                _communityUIState.update { currentState -> currentState.copy(
                    newPostContent = event.content ) }
                printState()
            }
            is CommunityUIEvent.NewPostAdded -> {
////                Add this as value for the database of new post
//                _communityUIState.value.newPostContent
               postAndUpdateUserCommunityPost()
            }
        }
    }

    fun postAndUpdateUserCommunityPost(){
        var tempUserPost = CommunityPost(
            postContent = communityUiState.value.newPostContent,
            postId = "default",
            userName = "Kaizoku",
            userProfilepic ="https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6",
        )
        viewModelScope.launch {
            val response = networkCommunityRepository.postCommunityUserPost(communityID = communityUiState.value.communityName,userPost = tempUserPost)
            val postID = response.body()!!.name
            tempUserPost.postId = postID
            val updateResponse = networkCommunityRepository.updatePost(communityID = communityUiState.value.communityName,postID = postID, communityPost = tempUserPost)

            if (updateResponse.isSuccessful){
                Log.d("Community Posts","CommunityPost Posted and Updated Successfully")
            }
            else{
                Log.d("Community Posts","Failed to post or update Community Post")
            }
        }
    }

    fun updateCurrentCommunityName(name:String){
        _communityUIState.update { currentState -> currentState.copy(
            communityName = name
        ) }

        Log.d("CommunityData",communityUiState.value.communityName)
    }
    private fun printState(){
        Log.d(TAG, "InsideStack")
        Log.d(TAG, _communityUIState.value.toString())
    }
}