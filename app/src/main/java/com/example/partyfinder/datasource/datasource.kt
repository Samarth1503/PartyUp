@file:Suppress("ClassName")

package com.example.partyfinder.datasource
import com.example.partyfinder.R
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.CommunitiesList
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.Status
import com.google.gson.Gson

object datasource {
    val userStatusOption= listOf(
        Status(R.string.ONLINE,R.drawable.user_online_logo),
        Status(R.string.AFK,R.drawable.user_afk_logo),
        Status(R.string.DND,R.drawable.user_dnd_logo)
    )

    val dmScreenDropDownOptions = listOf(
        Pair("Clear Chats",R.drawable.delete_blue),
        Pair("Report Chat",R.drawable.report_blue),
    )




//
//    val UserAccounts:List<UserAccount> = listOf(
//        UserAccount(gamerID = "Unknown", bio = "My Bio", profilePic = R.drawable.pp, status = userStatusOption[0]),
//        UserAccount(gamerID ="Sam", gamerTag = "5", bio = "My Bio", profilePic = R.drawable.pp, status = userStatusOption[0]),
//        UserAccount(gamerID = "Ichigo", gamerTag = "6", bio = "My Bio", profilePic = R.drawable.pp, status = userStatusOption[0]),
//        UserAccount(gamerID = "Kurama", bio = "My Bio", profilePic = R.drawable.pp, status = userStatusOption[0]),
//        UserAccount(gamerID = "GRoman", bio = "My Bio", profilePic = R.drawable.pp, status = userStatusOption[0]),
//        UserAccount(gamerID = "Kaizoku", gamerTag = "3", bio = "My Bio", profilePic = R.drawable.luffy, status = userStatusOption[0]),
//    )



    val FindPartyGamesMenuItems = listOf("Valorant", "CS : GO", "Overwatch","Team Fortress2")
    val FindPartyNoOfPlayerMenuItems = listOf("1","2","3","4","5","6+")

    val jsonString = """
{"data":
{"-NpnqL-rFzuHILEmxF9W":{"ProfilePic":"https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6","callDes":"Need players to play Overwatch","gameName":"OverWatch","gamerCallID":"1608b93a-8aa6-48f1-97ad-a1084a884d64","gamerID":"Sam","gamerTag":"#123","partySize":2},
"-NpnqLUIufiID9QYXQzN":{"ProfilePic":"https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6","callDes":"Need players to play Overwatch","gameName":"OverWatch","gamerCallID":"45f7888c-d3a9-43e6-b4e9-ae11c32f63d2","gamerID":"Sam","gamerTag":"#123","partySize":2},
"-NpotODPDalQpDVhe82L":{"ProfilePic":"https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6","callDes":"Need players to play Overwatch","gameName":"OverWatch","gamerCallID":"533adece-a205-4506-946b-ec9c0686c506","gamerID":"Sam","gamerTag":"#123","partySize":2},
"-NpotOL8leQ5jpBYQER2":{"ProfilePic":"https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6","callDes":"Need players to play Overwatch","gameName":"OverWatch","gamerCallID":"be681d2a-9d9c-47e9-be06-80df9bc539a9","gamerID":"Sam","gamerTag":"#123","partySize":2},
"-NqacO6-ehhDZgdoFZkb":{"ProfilePic":"https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6","callDes":"aga","callDuration":"2 hours","gameName":"Valorant","gamerCallID":"f80141b1-9fad-4e79-827d-afabd990cc68","gamerID":"Kaizoku","gamerTag":"#123","partySize":2}}}
"""

    // Create a Gson instance
    val gson = Gson()

    // Deserialize the JSON string to an object of GamerCallsList
    val gamerCallsList:GamerCallsList = gson.fromJson(jsonString, GamerCallsList::class.java)


    val ChatString = """ 
        {
          "data": {
            "-1": {
              "channelID": 1,
              "channelName": "Group",
              "isGroupChat": true,
              "gamerTag": "2",
              "memberTags": ["1"],
              "channelProfile": 2131165362,
              "content": [
                {
                  "author": "kaizoku",
                  "content": "Hello",
                  "timeStamp": "2024-02-23T12:34:56.789"
                }
              ]
            },
            "-2": {
              "channelID": 2,
              "channelName": "Pushkar",
              "isGroupChat": true,
              "gamerTag": "3",
              "memberTags": ["1"],
              "channelProfile": 2131165362,
              "content": [
                {
                  "author": "kaizoku",
                  "content": "Hello",
                  "timeStamp": "2024-02-23T12:34:56.789"
                },  
                {
                  "author": "Sam",
                  "content": "Sup",
                  "timeStamp": "2024-02-23T12:34:56.789"
                }
              ]
            }
           
          }
        }
    """.trimIndent()

    val chatChannelList: ChatChannelList = gson.fromJson(ChatString, ChatChannelList::class.java)

    val communityString = """
        {
          "Albion": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-Nrb9T1XLXqgYYkeasnj": {
                "postContent": "hii",
                "postId": "-Nrb9T1XLXqgYYkeasnj",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-Nrb9TagBGFJ4ehKAhUJ": {
                "postContent": "hii",
                "postId": "-Nrb9TagBGFJ4ehKAhUJ",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-Nrb9ToruscH29YOTWBd": {
                "postContent": "hii",
                "postId": "-Nrb9ToruscH29YOTWBd",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-NroSAjJHRrvpDjOUCRx": {
                "postContent": "Hello",
                "postId": "-NroSAjJHRrvpDjOUCRx",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "COC": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "CS:GO": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "LOL": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-NroadUpvGGxK9AArzyJ": {
                "postContent": "Are you excited for season 14?",
                "postId": "-NroadUpvGGxK9AArzyJ",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "Overwatch": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "Valorant": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              },
              "-Nro_KrpWtGYWB03zn6w": {
                "postContent": "hello",
                "postId": "-Nro_KrpWtGYWB03zn6w",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          },
          "WOW": {
            "communityPosts": {
              "-Nra0_t9cYsz6LdeuPmn": {
                "postContent": "Hello I am Excited to Join this Community",
                "postId": "-Nra0_t9cYsz6LdeuPmn",
                "userName": "Kaizoku",
                "userProfilepic": "https://firebasestorage.googleapis.com/v0/b/partyup-sam.appspot.com/o/download.jfif?alt=media&token=f38c422b-b4da-437a-97f3-a0774fd5c1a6"
              }
            }
          }
        }
    """.trimIndent()

    val tempCommunityList: CommunitiesList = gson.fromJson(communityString, CommunitiesList::class.java)
}

