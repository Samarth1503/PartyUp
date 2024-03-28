package com.example.partyfinder.ui.theme.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partyfinder.data.repositories.UserApiService
import com.example.partyfinder.data.repositories.networkChatChannelRepository
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.ChatItem
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.local.SortType
import com.example.partyfinder.model.uiState.ChatScreenUiState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.time.LocalDateTime
import kotlin.coroutines.resume


class chatScreenViewModel(val userUIDSharedViewModel : UserUIDSharedViewModel, val retrievedUserUID:String?) : ViewModel(){

    val _currentUserUID = MutableLiveData<String>()
    val currentUserUID :LiveData<String> get() = _currentUserUID

    private val _chatsScreenUiState = MutableStateFlow(ChatScreenUiState())
    val chatsScreenUiState:StateFlow<ChatScreenUiState> =_chatsScreenUiState.asStateFlow()

    var isMenuClicked by mutableStateOf(false)
    var isDmScreenMenuClicked by mutableStateOf(false)
    private val _sortType = MutableStateFlow(SortType.MOST_RECENT)
    val _chatChannelList = MutableLiveData<ChatChannelList>()
    val chatChannelList :LiveData<ChatChannelList> get() = _chatChannelList

    val _currentChannelObject = MutableLiveData<ChatChannel>()
    val currentChannelObject:LiveData<ChatChannel> get() = _currentChannelObject

    val dbref = FirebaseDatabase.getInstance("https://partyup-sam-default-rtdb.asia-southeast1.firebasedatabase.app").reference
    init {


       viewModelScope.launch {
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


           while (isActive){
               currentChannelObject.observeForever { response ->
                   _chatsScreenUiState.update { currentState -> currentState.copy(
                       currentChannelObject = response
                   ) }
               }

               if (chatsScreenUiState.value.currentChannel!=""){
               setLiveChangesListenerForNode(chatsScreenUiState.value.currentChannel)}

                   val currentUserAccountObject = retrieveUserAccount(currentUserUID.value.toString())
               if (currentUserAccountObject != null) {
                   var userChatChannels:ChatChannelList
                   var mutableMap = mutableMapOf<String,ChatChannel>()
                   userChatChannels = ChatChannelList(mutableMap)
                   currentUserAccountObject.chatChannelList.forEach {

                       val chatChannelObject = networkChatChannelRepository.retreiveCurrentChannel(it)
                       mutableMap.put(it, chatChannelObject.body()!!)
                   }
                   _chatsScreenUiState.update { currentState -> currentState.copy(
                       channelList = userChatChannels
                   ) }
               }
//               val response = retrieveChatChannelList()
//               if (response.isSuccessful){
//                   Log.d("retriveChatChannelList TestCase",response.body().toString())
//                   _chatsScreenUiState.update { currentState -> currentState.copy(
//                       channelList = response.body()
//                   ) }
//               }
               else{
                   Log.d(TAG,"Error fetching data")
               }
               delay(1000)
           }

       }

    }

    fun setLiveChangesListenerForNode(currentChannelID:String) {
        dbref.child("chatChannels")
            .child("data")
            .child(currentChannelID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Handle data changes
                    val currentChannel: ChatChannel? = snapshot.getValue(ChatChannel::class.java)

                    // Update your UI state or perform other actions
                    _currentChannelObject.value=currentChannel!!
                    Log.d(TAG,"Channel updated")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle errors
                    Log.e(TAG, "Error reading data: ${error.message}")
                }
            })
    }

//    fun createChatChannelFromLiveGamerCall(user1:UserAccount,user2:UserAccount){
//        onNewChatClicked()
//    }





        fun onNewChatClicked(currentUserGamerID:String ,user2UUID:String,isGroupChatpara:Boolean):String{
            val chatChannel = ChatChannel(
                channelID = "",
                channelName = if (isGroupChatpara){"Group Chat"} else{currentUserGamerID},
                isGroupChat = isGroupChatpara,
                channelProfile ="https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/icons8-people-skin-type-7-24.png?alt=media&token=f7f50143-01b0-436a-91a7-fbeab5d92f43",
                content = emptyList(),

                memberTags = listOf(currentUserUID.value.toString(),user2UUID)

            )
            var isChatChannelUpdate = false
        viewModelScope.launch {

            val response = networkChatChannelRepository.postChatChannel(chatChannel)
            if (response.isSuccessful){
                val chatChannelFBID =response.body()!!.name
                chatChannel.channelID = chatChannelFBID
                isChatChannelUpdate = true
                val updateResponse = networkChatChannelRepository.updateChatChannel(chatChannelFBID,chatChannel)

                if (updateResponse.isSuccessful){
                    isChatChannelUpdate = true
                    Log.d("NewChatChannel TestCase" , "ChatChannel Updated Successfully")
                    val currentUserAccount = retrieveUserAccount(currentUserUID.value.toString())
                    if (currentUserAccount != null) {
                        currentUserAccount.chatChannelList =currentUserAccount.chatChannelList.toMutableList().apply { add(chatChannelFBID) }
                        UserApiService.updateUserAccount(currentUserUID.value.toString(),currentUserAccount)
                        Log.d("NewChatChannel TestCase","ChatChannelID updated in currentUser account")
                    }

                    val secondUserAccount =retrieveUserAccount(user2UUID)
                    if (secondUserAccount != null){
                        secondUserAccount.chatChannelList =secondUserAccount.chatChannelList.toMutableList().apply { add(chatChannelFBID) }
                        UserApiService.updateUserAccount(user2UUID,secondUserAccount)
                        Log.d("NewChatChannel TestCase" , "ChatChannelID updated in secondUserAccount")
                    }

                }
                else{
                    Log.d("NewChatChannel TestCase" , "ChatChannel Update failed")
                }
            }

        }
        Log.d(TAG,"Chat channel posted")
        if (isChatChannelUpdate){
            return chatChannel.channelID
        }
            else{
                return ""
            }
    }
    fun onChatsScreenMenuClick(){
        isMenuClicked=!isMenuClicked
        _chatsScreenUiState.update { currentState -> currentState.copy(
            isMenuClicked = isMenuClicked
        ) }
    }




    fun onDmScreenMenuClicked(){
        isDmScreenMenuClicked = !isDmScreenMenuClicked
        _chatsScreenUiState.update {currentState -> currentState.copy(
            isDmTopbarMenuClicked = isDmScreenMenuClicked
        ) }
    }

    fun setCurrentChatChannel(channelId:String){
        _chatsScreenUiState.update { currentState -> currentState.copy(
            currentChannel = channelId
        ) }


//        viewModelScope.launch {
//
//                val response = networkChatChannelRepository.retreiveCurrentChannel(channelId)
//                if (response.isSuccessful){
//                    _chatsScreenUiState.update { currentState -> currentState.copy(
//                        currentChannelObject = response.body()
//                    ) }
//                    Log.d(TAG,"Current ChatChannelFetched")
//                }
//                else{
//                    Log.d(TAG,"Error Fetching chat Channel")
//                }
//
//        }


    }


//    fun retrieveCurrentChatChannel(channelID:String?):ChatChannel{
//        val channelIDInt = channelID?:"0"
//        viewModelScope.launch {
//            while(isActive){
//                 val response = networkChatChannelRepository.getCurrentChatChannel(channelIDInt.toInt())
//                if (response.isSuccessful){
//                    currentChannel.value = response.body()
//                    _chatsScreenUiState.update { currentState -> currentState.copy(
//                        currentChannel = currentChannel.value
//                    ) }
//                        Log.d(TAG,"Retriving currentChatChannel")
//
//                }
//            }
//        }
//        return currentChannel.value?:datasource.ChatChannels.get(0)
//    }


    suspend fun retrieveChatChannelList(): Response<ChatChannelList> {
        val response = networkChatChannelRepository.getAllChatChannels()
        _chatChannelList.value = response.body()
        return response
    }



    suspend fun retrieveUserAccount(UserUid: String): UserAccount? {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("/users/data/")

        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<UserAccount?> { continuation ->
                myRef.child(UserUid).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.getValue(UserAccount::class.java)
                        continuation.resume(value)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d("Fetching User Account", "Request Cancelled")
                        continuation.resume(null)
                    }
                })
            }
        }
    }


    fun onMessageValueChanged(changedMsg:String){
        _chatsScreenUiState.update {currentState -> currentState.copy(
            message = changedMsg
        )} }

    fun sendChatButtonClick(author:String,fireBaseUniqueID: String, message: String) {
        val chatItem = ChatItem(author = author, content = message, timeStamp = LocalDateTime.now().toString())
        viewModelScope.launch {
            var localCurrentChannel = chatsScreenUiState.value.currentChannelObject

            localCurrentChannel!!.content = localCurrentChannel.content.toMutableList().apply { add(chatItem) }
            _currentChannelObject.value=localCurrentChannel!!

            val response = networkChatChannelRepository.retreiveCurrentChannel(Id = fireBaseUniqueID)
            var channel = response.body()
            channel!!.content = channel.content.toMutableList().apply { add(chatItem) }

            networkChatChannelRepository.postDmContent(Id = fireBaseUniqueID, content = channel.content)
            _chatsScreenUiState.update { currentState ->
                currentState.copy(
                    message = ""
                )
            }

        }
    }



    fun updateChatChannel(id:String){
        viewModelScope.launch {
            val response = networkChatChannelRepository.retreiveCurrentChannel(Id = id)
            if (response.isSuccessful){
                _currentChannelObject.value = response.body()
                Log.d(TAG,"CHAT CHANNEL UPDATED")

            }
        }
    }


    suspend fun searchUserDataInFirebase(): List<UserAccount> {
        val snapshot = FirebaseDatabase.getInstance().getReference("users").child("data").get().await()
        val userList = mutableListOf<UserAccount>()

        for (userSnapshot in snapshot.children) {
            val user = userSnapshot.getValue(UserAccount::class.java)
            user?.let {
                userList.add(UserAccount(it.gamerID, it.gamerTag, it.profilePic))
            }
        }
        return userList
    }

}


