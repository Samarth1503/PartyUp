package com.example.partyfinder.model

import kotlinx.serialization.Serializable
import java.sql.Types.NULL
@Serializable
class UserAccount(
    var email: String = "",
    var uid: String = "",
    var gamerID: String = "",
    var gamerTag: String = "",
    var bio: String = "",
    var profilePic: String= "",
    var profileBanner: String = "",
    var rank1GameName :String = "",
    var rank1GameRank:String = "",
    var rank2GameName :String = "",
    var rank2GameRank:String = "",
    var rank3GameName :String = "",
    var rank3GameRank:String = "",
    var status: Pair<Int, Int> = Pair(NULL, NULL),
    var liveGamerCallID: String = "",
    var chatChannelList:List<String> = listOf("defaultObject"),
    var userGamerCallsList:List<String> = listOf("defaultObject"),
    var friendList:List<String> = listOf("defaultObject")
) {
    init {
        if (uid.isNotEmpty()) {
            this.gamerTag = uid.substring(0,4)
        }
    }
}

