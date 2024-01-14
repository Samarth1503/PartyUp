package com.example.partyfinder.data

import android.util.Log
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import java.sql.Types.NULL

class UserAccount() {
    private var uid: String = ""
    var email: String = ""
    var gamerID: String = ""
    var gamerTag: String = ""
    var profilePic: Int = NULL
    var status: Pair<Int, Int> = Pair(NULL, NULL)
    var bio: String = ""

    constructor(email: String, uid: String): this() {
        this.email = email
        this.uid = uid
        this.gamerTag = uid.substring(0,4)
        Log.d("UserAccount", "UID: $uid")
        Log.d("UserAccount", "Email: $email")
        Log.d("UserAccount", "GamerTag: $gamerTag")
    }

    constructor(gamerID: String, bio: String, profilePic: Int, status: Pair<Int, Int>) : this() {
        this.gamerID = gamerID
        this.bio = bio
        this.profilePic = profilePic
        this.status = status
        Log.d("UserAccount", "GamerID: $gamerID")
        Log.d("UserAccount", "ProfilePic: $profilePic")
        Log.d("UserAccount", "Status: ${status.first}, ${status.second}")
        Log.d("UserAccount", "Bio: $bio")
    }

    fun printData() {
        Log.d("UserAccount", "FULL DATA")
        Log.d("UserAccount", "UID: $uid")
        Log.d("UserAccount", "Email: $email")
        Log.d("UserAccount", "GamerTag: $gamerTag")
        Log.d("UserAccount", "GamerID: $gamerID")
        Log.d("UserAccount", "ProfilePic: $profilePic")
        Log.d("UserAccount", "Status: ${status.first}, ${status.second}")
        Log.d("UserAccount", "Bio: $bio")
    }
}