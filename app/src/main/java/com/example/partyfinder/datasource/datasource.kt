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

    val dmScreenDropDownOptions = listOf(
        Pair("Clear Chats",R.drawable.delete_blue),
        Pair("Report Chat",R.drawable.report_blue),
    )

    val MyGamerCalls:List<GamerCalls> = listOf(
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),
        GamerCalls("Kaizoku","#123",4,"Need 2 Gold Rank Valorant Players","Valorant",R.drawable.pp),

    )

    val UserAccounts:List<UserAccount> = listOf(
        UserAccount(gamerID = "Unknown", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerID ="Sam", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerID = "Ichigo", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerID = "Kurama", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerID = "GRoman", bio = "My Bio", profilePic = R.drawable.pp, status = datasource.userStatusOption.get(0)),
        UserAccount(gamerID = "Kaizoku", bio = "My Bio", profilePic = R.drawable.luffy, status = datasource.userStatusOption.get(0)),
    )

    val ChatChannels:List<ChatChannel> = listOf(
        ChatChannel(channelID = 1,isGroupChat = true, gamerTag = "2",  memberTags = listOf("1"), channelName = "Group", channelProfile = R.drawable.pp),
        ChatChannel(channelID = 2,isGroupChat = false, gamerTag = "3", memberTags = listOf("1"), channelName = "Pushkar", channelProfile = R.drawable.pp),
        ChatChannel(channelID = 3,isGroupChat = true, gamerTag = "4", memberTags = listOf("1"), channelName = "Group2", channelProfile = R.drawable.pp),
        ChatChannel(channelID = 4,isGroupChat = false, gamerTag = "5", memberTags = listOf("1"), channelName = "Ganesh", channelProfile = R.drawable.pp),
        ChatChannel(channelID = 5,isGroupChat = false, gamerTag = "6", memberTags = listOf("6"), channelName = "Kaizoku", channelProfile = R.drawable.pp),
    )

    val FindPartyGamesMenuItems = listOf("Valorant", "CS : GO", "Overwatch","Team Fortress2")
    val FindPartyNoOfPlayerMenuItems = listOf("1","2","3","4","5","6+")
}