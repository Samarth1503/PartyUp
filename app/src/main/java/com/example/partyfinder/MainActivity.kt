package com.example.partyfinder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.partyfinder.data.repositories.LocalUserRepository
import com.example.partyfinder.datasource.AppDatabase
import com.example.partyfinder.ui.theme.PartyFinderTheme
import com.example.partyfinder.ui.theme.ViewModels.LoginViewModel
import com.example.partyfinder.ui.theme.ViewModels.RegistrationViewModel
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            FirebaseApp.initializeApp(this@MainActivity)
        }

        setContent {
            PartyFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // Local Database Query
//                    LaunchedEffect(Unit) {
//                        launch(Dispatchers.IO) {
//
//                            val localUserDao = AppDatabase.getDatabase(this@MainActivity).localUserDao()
//                            localUserDao.upsert(LocalUser("123example@email.com"))
//
//                            val usersDao = AppDatabase.getDatabase(this@MainActivity).usersDao()
//                            usersDao.upsert(Users(gamerID = "gamer1", bio = "bio1", email = "email1", gamerTag = "tag1", profilePic = 1L, statusFirst = 1L, statusSecond = 1L, liveGamerCallID = "call1", userCommunityPosts = emptyMap(), communitiesJoined = emptyMap(), friends = emptyMap(), chatroom = emptyMap(), userGamerCalls = emptyMap()))
//                            usersDao.upsert(Users(gamerID = "gamer2", bio = "bio2", email = "email2", gamerTag = "tag2", profilePic = 2L, statusFirst = 2L, statusSecond = 2L, liveGamerCallID = "call2", userCommunityPosts = emptyMap(), communitiesJoined = emptyMap(), friends = emptyMap(), chatroom = emptyMap(), userGamerCalls = emptyMap()))
//
//                            val gamerCallsDao = AppDatabase.getDatabase(this@MainActivity).gamerCallsDao()
//                            gamerCallsDao.insertAll(GamerCalls(gamerID = "gamer1", profilePic = "pic1", callDes = "des1", gameName = "game1", gamerTag = "tag1", partySize = 1L))
//                            gamerCallsDao.insertAll(GamerCalls(gamerID = "gamer2", profilePic = "pic2", callDes = "des2", gameName = "game2", gamerTag = "tag2", partySize = 2L))
//
//                            val chatChannelsDao = AppDatabase.getDatabase(this@MainActivity).chatChannelsDao()
//                            chatChannelsDao.insertAll(ChatChannels(channelID = "channel1", channelName = 1L, channelProfile = "profile1", isGroupChat = true, memberTags = emptyMap(), content = emptyMap()))
//                            chatChannelsDao.insertAll(ChatChannels(channelID = "channel2", channelName = 2L, channelProfile = "profile2", isGroupChat = false, memberTags = emptyMap(), content = emptyMap()))
//
//                            val chatDao = AppDatabase.getDatabase(this@MainActivity).chatDao()
//                            chatDao.insertAll(Chat(chatID = "chat1", author = "author1", content = "content1", timestamp = "timestamp1"))
//                            chatDao.insertAll(Chat(chatID = "chat2", author = "author2", content = "content2", timestamp = "timestamp2"))
//
//                            val userPostDao = AppDatabase.getDatabase(this@MainActivity).userPostDao()
//                            userPostDao.insertAll(UserPost(postID = "post1", author = "author1", content = "content1", likes = "likes1", reports = "reports1", timeline = "timeline1", gamerID = "gamer1"))
//                            userPostDao.insertAll(UserPost(postID = "post2", author = "author2", content = "content2", likes = "likes2", reports = "reports2", timeline = "timeline2", gamerID = "gamer2"))
//
//                            val liveGamerCallDao = AppDatabase.getDatabase(this@MainActivity).liveGamerCallDao()
//                            liveGamerCallDao.insertAll(LiveGamerCall(id = "call1", gameName = "game1", isGamerCallLive = true, noPlayerRequired = "player1", noPlayersInParty = "party1"))
//                            liveGamerCallDao.insertAll(LiveGamerCall(id = "call2", gameName = "game2", isGamerCallLive = false, noPlayerRequired = "player2", noPlayersInParty = "party2"))
//
//                            val communitiesDao = AppDatabase.getDatabase(this@MainActivity).communitiesDao()
//                            communitiesDao.insertAll(Communities(id = 1, communityIDs = emptyMap()))
//                            communitiesDao.insertAll(Communities(id = 2, communityIDs = emptyMap()))
//
//                            val communityDao = AppDatabase.getDatabase(this@MainActivity).communityDao()
//                            communityDao.insertAll(Community(communityID = "community1", posts = emptyMap()))
//                            communityDao.insertAll(Community(communityID = "community2", posts = emptyMap()))
//                        }
//                    }

                    LaunchedEffect(Unit) {
                        launch(Dispatchers.IO) {
                            val localUserDao = AppDatabase.getDatabase(this@MainActivity).localUserDao()
                            val retrievedUserEmail = localUserDao.getUser()
                            if (retrievedUserEmail != null) {
                                Log.d("Local UserData", retrievedUserEmail)
                            } else {
                                Log.d("Local UserData", "No user email found")
                            }

                            val usersDao = AppDatabase.getDatabase(this@MainActivity).usersDao()
                            val retrievedUsersEntities = usersDao.getAll().first()
                            retrievedUsersEntities.forEach { user ->
                                Log.d("Users Data", user.toString())
                            }

                            val gamerCallsDao = AppDatabase.getDatabase(this@MainActivity).gamerCallsDao()
                            val retrievedGamerCalls = gamerCallsDao.getAll().first()
                            retrievedGamerCalls.forEach { gamerCall ->
                                Log.d("GamerCalls Data", gamerCall.toString())
                            }

                            val chatChannelsDao = AppDatabase.getDatabase(this@MainActivity).chatChannelsDao()
                            val retrievedChatChannels = chatChannelsDao.getAll().first()
                            retrievedChatChannels.forEach { chatChannel ->
                                Log.d("ChatChannels Data", chatChannel.toString())
                            }

                            val chatDao = AppDatabase.getDatabase(this@MainActivity).chatDao()
                            val retrievedChats = chatDao.getAll().first()
                            retrievedChats.forEach { chat ->
                                Log.d("Chat Data", chat.toString())
                            }

                            val userPostDao = AppDatabase.getDatabase(this@MainActivity).userPostDao()
                            val retrievedUserPosts = userPostDao.getAll().first()
                            retrievedUserPosts.forEach { userPost ->
                                Log.d("UserPost Data", userPost.toString())
                            }

                            val liveGamerCallDao = AppDatabase.getDatabase(this@MainActivity).liveGamerCallDao()
                            val retrievedLiveGamerCalls = liveGamerCallDao.getAll().first()
                            retrievedLiveGamerCalls.forEach { liveGamerCall ->
                                Log.d("LiveGamerCall Data", liveGamerCall.toString())
                            }

                            val communitiesDao = AppDatabase.getDatabase(this@MainActivity).communitiesDao()
                            val retrievedCommunities = communitiesDao.getAll().first()
                            retrievedCommunities.forEach { community ->
                                Log.d("Communities Data", community.toString())
                            }

                            val communityDao = AppDatabase.getDatabase(this@MainActivity).communityDao()
                            val retrievedCommunity = communityDao.getAll().first()
                            retrievedCommunity.forEach { community ->
                                Log.d("Community Data", community.toString())
                            }
                        }
                    }
                    val userDao = AppDatabase.getDatabase(this).localUserDao()
                    val userRepository = LocalUserRepository(userDao)

                    val registrationViewModel = RegistrationViewModel(userRepository)

                    PartyFinderApp(registrationViewModel = registrationViewModel,
                        loginViewModel = LoginViewModel(userRepository, localLoginEmail = registrationViewModel.updateLoginEmailField())
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PartyFinderTheme {

    }
}

