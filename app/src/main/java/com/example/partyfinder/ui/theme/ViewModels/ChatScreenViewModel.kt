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
import com.example.partyfinder.R
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch


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

               val response = retrieveChatChannelList()
               if (response.isSuccessful){
                   Log.d(TAG,response.body().toString())
                   _chatsScreenUiState.update { currentState -> currentState.copy(
                       channelList = response.body()
                   ) }
               }
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

    fun onNewChatClicked(){
            val ChatChannel = ChatChannel(
                channelID = 2,
                channelName = "Trial Chat 3",
                isGroupChat = true,
                channelProfile = R.drawable.pp,
                gamerTag = "#1342",
                content = listOf(
                    ChatItem("kaizoku",content="Hello",timeStamp = LocalDateTime.now().toString()),
                    ChatItem("Sam",content="Sup",timeStamp = LocalDateTime.now().toString())),

                memberTags = listOf(currentUserUID.value.toString(),"xEw4m8meVfWSbfAALUIP8T7zOfz2")

            )

        viewModelScope.launch {

            networkChatChannelRepository.postChatChannel(ChatChannel)
        }
        Log.d(TAG,"Chat channel posted")
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


    fun retrieveUserAccount(UserUid: String): UserAccount {

        Log.d("ChatsScreen TestCase", UserUid)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users").child("data")
        var userAccount = UserAccount()

        val latch = CountDownLatch(1)

        myRef.child(UserUid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(UserAccount::class.java)
                if (value != null) {
                    userAccount = value
                }
                latch.countDown()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Fetching User Account", "Request Cancelled")
                latch.countDown()
            }
        })

        latch.await()

        return userAccount
    }



    fun onMessageValueChanged(changedMsg:String){
        _chatsScreenUiState.update {currentState -> currentState.copy(
            message = changedMsg
        )} }

    fun sendChatButtonClick(fireBaseUniqueID: String, message: String) {
        val chatItem = ChatItem(author = "kaizoku", content = message, timeStamp = LocalDateTime.now().toString())
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


