package com.example.partyfinder.model

import kotlinx.serialization.Serializable
@Serializable
class UserAccount(
    var email: String = "",
    var uid: String = "",
    var gamerID: String = "",
    var gamerTag: String = "",
    var bio: String = "",
    var profilePic: String= "",
    val coverImageLink: String = "",
    var rank1GameName :String = "",
    var rank1GameRank:String = "",
    var rank2GameName :String = "",
    var rank2GameRank:String = "",
    var rank3GameName :String = "",
    var rank3GameRank:String = "",
    var status: Status = Status(),
    var liveGamerCallID: String = "",
    var chatChannelList:List<String> = emptyList(),
    var userGamerCallsList:List<String> = emptyList(),
    var friendList:List<String> = emptyList()
) {
    init {
        if (uid.isNotEmpty()) {
            this.gamerTag = uid.substring(0,4)
        }
    }
}

