package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme


//creating other screens
@Composable
fun ChatsScreen(){

//        Variable declaration for menu
    var isMenuVisible by remember { mutableStateOf(false) }

    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .height(808.dp)
            .width(393.dp)
        )
        {
            Box(modifier = Modifier){
                Column{
                    isMenuVisible = ChatTopBar()
                    Chats()
                }
                if (isMenuVisible){
                    ChatMenu()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatsScreen(){
    PartyFinderTheme {
        ChatsScreen()
    }
}

@Composable
fun ChatTopBar(modifier: Modifier = Modifier): Boolean {
    var isMenuClicked by remember { mutableStateOf(false) }

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
                    .clickable { isMenuClicked = !isMenuClicked }
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
                    .clickable { isMenuClicked = !isMenuClicked }
            )
        }
    }
    return isMenuClicked
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
fun ChatMenu(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
    ) {
        Surface(color = colorResource(id = R.color.black),
            modifier = modifier
                .padding(
                    0.dp,
                    (dimensionResource(id = R.dimen.top_bar_height) - 24.dp),
                    36.dp,
                    0.dp
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(4.dp)
                )
                .align(Alignment.TopEnd)
        ) {
            Column ( modifier = modifier
                .padding(20.dp, 8.dp, 20.dp, 8.dp)
            ){
                Row ( verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
//                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.delete_blue),
                        contentDescription = "DeleteChats",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 15.dp, 4.dp)
                            .size(18.dp)
                    )
                    Text(
                        text = "Clear Chats",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.primary)
                    )
                }
                Row ( verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
//                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.report_blue),
                        contentDescription = "DeleteChats",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 15.dp, 4.dp)
                            .size(20.dp)
                    )
                    Text(
                        text = "Report",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.primary)
                    )
                }
            }
        }
    }
}


@Composable
fun Chats(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxHeight()
    ) {
        LazyColumn(
            modifier = modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            items(13) { index ->
                Chat()
            }
        }
        NewChat()
    }
}


@Composable
fun Chat(modifier: Modifier = Modifier){
    Row ( verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(10.dp, 5.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
            .height(75.dp)
    ) {
        Image (
            painter = painterResource(id = R.drawable.pp),
            contentDescription = "58008",
            modifier = Modifier
                .padding(20.dp, 0.dp, 10.dp, 0.dp)
                .size(45.dp)
                .border(
                    (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                    RoundedCornerShape(50.dp)
                )
        )
        Column ( modifier = modifier
            .padding(5.dp,0.dp,0.dp,0.dp)
        ){
            Text(
                text = "58008",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = modifier.height(2.dp))
            Text(
                text = "58008",
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(id = R.color.chatPreview)
            )
        }
    }
}

@Composable
fun NewChat(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
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
