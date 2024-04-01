package com.example.partyfinder.ui.theme.ChatScreens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.ChatChannel
import com.example.partyfinder.model.ChatChannelList
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel


//creating other screens
@Composable
fun ChatsScreen(
    isMenuClicked:Boolean,
    chatTopBar:@Composable () -> Unit,
    chatMenu:@Composable () ->Unit,
    chats:@Composable () -> Unit){

//        Variable declaration for menu


    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
        )
        {
            Box(modifier = Modifier){
                Column{
                    chatTopBar()
                    chats()
                }
                if (isMenuClicked){
                    chatMenu()
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewChatsScreen(){
//    PartyFinderTheme {
//        ChatsScreen(
//            chatTopBar ={ ChatTopBar(isMenuClicked = false, onMenuClick = {}, navigateBack = {}) },
//            chatMenu = { ChatMenu(isMenuClicked = true, onMenuClicked = {}, onMenuItemClicked = {}) },
//            isMenuClicked = false,
//            chats = { Chat(
//                chatChannel = datasource.chatChannelList.chatChannels.get("-1")!!,
//                userAccount = datasource.UserAccounts.get(0),
//                onClick = {})
//            }
//
//        )
//    }
//}

@Composable
fun ChatTopBar(modifier: Modifier = Modifier,
               isMenuClicked:Boolean,
               navigateBack:()->Unit,
               onMenuClick:()->Unit){

    Box(
        modifier = modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG))
    ) {
        Image(
            painter = painterResource(id = (R.drawable.back_blue)),
            contentDescription = "BackIcon",
            modifier = modifier
                .padding(25.dp, 5.dp, 0.dp, 0.dp)
                .size(25.dp)
                .align(Alignment.CenterStart)
                .clickable { navigateBack() }
        )
        Text(
            text = "Chat",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary),
            modifier = Modifier.align(Alignment.Center)
        )
        if (!isMenuClicked) {
            Image(
                painter = painterResource(id = R.drawable.menu_icon_blue),
                contentDescription = "MenuIcon",
                modifier = modifier
                    .padding(0.dp, 5.dp, 15.dp, 0.dp)
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onMenuClick() }
            )
        }
        if (isMenuClicked) {
            Image(
                painter = painterResource(id = R.drawable.close_blue),
                contentDescription = "MenuIcon",
                modifier = modifier
                    .padding(0.dp, 5.dp, 15.dp, 0.dp)
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onMenuClick() }
            )
        }
    }

}



//
//@Composable
//fun ChatTopBar(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .height(dimensionResource(id = (R.dimen.top_bar_height)))
//            .fillMaxWidth()
//            .background(color = colorResource(id = R.color.DarkBG))
//    ) {
//        Image(
//            painter = painterResource(id = (R.drawable.back_blue)),
//            contentDescription = "BackIcon",
//            modifier = modifier
//                .padding(25.dp, 5.dp, 0.dp, 0.dp)
//                .size(25.dp)
//                .align(Alignment.CenterStart)
//        )
//        Text(
//            text = "Chat",
//            style = MaterialTheme.typography.titleMedium,
//            color = colorResource(id = R.color.primary),
//            modifier = Modifier.align(Alignment.Center)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.menu_icon_blue),
//            contentDescription = "MenuIcon",
//            modifier = modifier
//                .padding(0.dp, 5.dp, 15.dp, 0.dp)
//                .size(20.dp)
//                .align(Alignment.CenterEnd)
//        )
//    }
//}


@Composable
fun ChatMenu(
    modifier: Modifier = Modifier,
    isMenuClicked:Boolean,
    onClearAllChat:()->Unit,
    onMenuItemClicked:(item:Pair<String,Int>)->Unit,
    onMenuClicked:()->Unit,) {
    Box(modifier = modifier
        .fillMaxWidth()
    ) {
        Card(modifier= Modifier
            .padding(end = 8.dp,)
            .align(Alignment.CenterEnd)
            .shadow(10.dp)
            ) {

            if (isMenuClicked) {
                DropdownMenu(
                    modifier= Modifier
                        .background(color = colorResource(id = R.color.neutral_10)),

                    expanded = isMenuClicked,
                    onDismissRequest = { onMenuClicked() }) {
                    datasource.dmScreenDropDownOptions.forEach{
                            item ->
                        DropdownMenuItem(text = { Row(modifier=Modifier.width(110.dp)) {
                            Image(modifier=Modifier.size(18.dp),painter = painterResource(id = item.second), contentDescription =item.first )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = item.first, color = colorResource(id = R.color.primary))
                        }},
                            onClick = {
//                                onMenuItemClicked(item)
                                if (item == datasource.dmScreenDropDownOptions.get(0)){
                                    onClearAllChat()
                                }
                                onMenuClicked()}
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun Chats(
    modifier: Modifier = Modifier,
    chatChannelList:ChatChannelList?,
    chatScreenViewModel: chatScreenViewModel,
    navController:NavHostController,
    onNewChatClicked: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxHeight()
        ) {
        LazyColumn(
            modifier = modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            items(chatChannelList!!.chatChannels.toList()){ chatChannel ->
                var userAccount by remember { mutableStateOf<UserAccount?>(null) }
                LaunchedEffect(chatChannel) {
                    userAccount = if(chatChannel.second.memberTags.get(1) == chatScreenViewModel.currentUserUID.value){
                        chatScreenViewModel.retrieveUserAccount(chatChannel.second.memberTags.get(0))
                } else {
                        chatScreenViewModel.retrieveUserAccount(chatChannel.second.memberTags.get(1))
                    }
                }

                if (userAccount != null) {
                    if(chatChannel.second.isGroupChat){
                        Chat(onClick = { navController.navigate("DMScreen/${chatChannel.first}") }, chatChannel = chatChannel.second, userAccount = userAccount!!)
                        Log.d("ChatsOnClick TestCase",chatChannel.second.channelID.toString())
                    }
                    else{
                        Chat(onClick = { navController.navigate("DMScreen/${chatChannel.first}") }, chatChannel = chatChannel.second, userAccount = userAccount!!)
                    }
                }
            }
        }
        NewChat(modifier=
        modifier
            .size(80.dp)
            .align(alignment = Alignment.BottomEnd),onNewChatClicked = onNewChatClicked)
    }
}



@Composable
fun Chat(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    chatChannel: ChatChannel,
    userAccount: UserAccount,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClick() }
            .padding(10.dp, 5.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .height(75.dp)
    ) {


        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(if (chatChannel.isGroupChat){ chatChannel.channelProfile}else{userAccount.profilePic})
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp, 0.dp, 10.dp, 0.dp)
                .size(45.dp)
                .border(
                    (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                    RoundedCornerShape(50.dp)
                ).clip(RoundedCornerShape(50.dp)),
            error = painterResource(id = R.drawable.sadpaprx),
            placeholder = painterResource(id = R.drawable.defaultpp)
        )


        // Separate modifier for the Column
        Column(modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)) {
            Text(
                text = if (chatChannel.isGroupChat) {
                    chatChannel.channelName
                } else {
                    userAccount.gamerID
                },
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = userAccount.gamerTag,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.chatPreview)
            )
        }
    }
}


@Composable
fun NewChat(modifier: Modifier = Modifier , onNewChatClicked:()->Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { onNewChatClicked() },
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_chat_img),
            contentDescription = "58008",
            modifier = Modifier
                .padding(0.dp, 0.dp, 20.dp, 20.dp)
                .size(45.dp)
                .background(color = colorResource(id = R.color.transparent))
        )
    }
}


