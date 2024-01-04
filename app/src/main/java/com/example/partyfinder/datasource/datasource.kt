package com.example.partyfinder.datasource
import com.example.partyfinder.R
import com.example.partyfinder.data.ChatChannel
import com.example.partyfinder.data.GamerCalls
import com.example.partyfinder.data.UserAccount

object datasource {
    val userStatusOption= listOf(
        Pair(R.string.ONLINE,R.drawable.user_online_logo),
        Pair(R.string.AFK,R.drawable.user_afk_logo),
        Pair(R.string.DND,R.drawable.user_dnd_logo)
    )

    val MyGamerCalls:List<GamerCalls> = listOf(
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),

    )

    val UserAccounts:List<UserAccount> = listOf(
        UserAccount(gamerTag = "1", gamerID = "Kaizoku", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerTag = "2", gamerID ="Sam", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerTag="3", gamerID = "Ichigo", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerTag="4", gamerID = "Kurama", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
    )

    val ChatChannels:List<ChatChannel> = listOf(
        ChatChannel(channelID = 1,isGroupChat = false, gamerTag = "1", memberTags = listOf("1")),
        ChatChannel(channelID = 1,isGroupChat = false, gamerTag = "2", memberTags = listOf("1")),
        ChatChannel(channelID = 1,isGroupChat = false, gamerTag = "3", memberTags = listOf("1")),
        ChatChannel(channelID = 1,isGroupChat = false, gamerTag = "4", memberTags = listOf("1")),
    )
}