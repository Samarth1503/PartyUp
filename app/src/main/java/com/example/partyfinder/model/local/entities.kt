package com.example.partyfinder.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LocalUser(
    @PrimaryKey val id: Int = 0,
    val userEmail: String = "",
    val userUID: String = ""
)

@Entity
data class Users(
    @PrimaryKey val gamerID: String,
    val bio: String,
    val email: String,
    val gamerTag: String,
    val profilePic: Long,
    val statusFirst: Long,
    val statusSecond: Long,
    val liveGamerCallID: String,
    val userCommunityPosts: Map<String, Any>,
    val communitiesJoined: Map<String, Any>,
    val friends: Map<String, Any>,
    val chatroom: Map<String, Any>,
    val userGamerCalls: Map<String, Any>
)

//@Entity
//data class GamerCalls(
//    @PrimaryKey val gamerID: String = "null",
//    val profilePic: String,
//    val callDes: String,
//    val gameName: String,
//    val gamerTag: String,
//    val partySize: Long
//)

@Entity
data class ChatChannels(
    @PrimaryKey val channelID: String = "null",
    val channelName: Long,
    val channelProfile: String,
    val isGroupChat: Boolean,
    val memberTags: Map<String, Any>,
    val content: Map<String, Any>
)

@Entity
data class Chat(
    @PrimaryKey val chatID: String = "null",
    val author: String,
    val content: String,
    val timestamp: String
)

//   need to be handled with TypeConverters or a different structure

@Entity
data class UserPost(
    @PrimaryKey val postID: String = "null",
    val author: String,
    val content: String,
    val likes: String,
    val reports: String,
    val timeline: String,
    val gamerID: String
)


@Entity
data class LiveGamerCall(
    @PrimaryKey val id: String = "null",
    val gameName: String,
    val isGamerCallLive: Boolean,
    val noPlayerRequired: String,
    val noPlayersInParty: String
)

@Entity
data class Communities(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val communityIDs: Map<String, Any>
)

@Entity
data class Community(
    @PrimaryKey val communityID: String = "null",
    val posts: Map<String, Any>
)
