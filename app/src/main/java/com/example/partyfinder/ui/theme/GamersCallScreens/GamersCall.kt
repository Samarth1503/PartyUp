package com.example.partyfinder.ui.theme.GamersCallScreens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun GamersCall(
    modifier:Modifier=Modifier,
    onCreateClick:()->Unit,
    gamersCallsTopBar:@Composable () ->Unit,
    gamersCallContent:@Composable () ->Unit){
    Surface(color= colorResource(id = R.color.black)){
        Box(modifier=Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState(), true)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                gamersCallsTopBar()
                gamersCallContent()
            }
            Button(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .height(40.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(5.dp),
                onClick = onCreateClick,
                border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
            ) {
                Text(text = "Create",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
            }
        }
    }
}


@Composable
fun GamersCallTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () ->Unit,
    onFilteClick: () ->Unit
    ) {
    Box(
        modifier = modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = (R.drawable.back_blue)),
            contentDescription = "BackIcon",
            modifier = modifier
                .padding(25.dp, 5.dp, 0.dp, 0.dp)
                .size(26.dp)
                .align(Alignment.CenterStart)
                .clickable { onBackClick() }
        )
        Text(
            text = "Gamers Call",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
        Image(
            painter = painterResource(id = (R.drawable.filter1)),
            contentDescription = "FilterIcon",
            modifier = modifier
                .padding(0.dp, 5.dp, 24.dp, 0.dp)
                .size(26.dp)
                .align(Alignment.CenterEnd)
                .clickable { onFilteClick() }
        )
    }
}




@Composable
fun GamersCallContent(
    chatScreenViewModel: chatScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    listOfGamerCalls: GamerCallsList,
    ) {

    var isChatChannelLoading by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier){
        LazyColumn(
            modifier = modifier
                .padding(0.dp, 5.dp)
                .background(
                    color = colorResource(id = R.color.black),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(dimensionResource(id = R.dimen.main_padding), 0.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listOfGamerCalls.gamerCalls.values.toList()) { gamerCall ->
                G_Calls(
                    gameName = gamerCall.gameName,
                    callDes = gamerCall.callDes,
                    partySize = gamerCall.partySize,
                    profilePic = gamerCall.ProfilePic,
                    gamerID = gamerCall.gamerID,
                    context = context,
                    onPartyUpClicked = {
                        val channelID = runBlocking {
                            chatScreenViewModel.onNewChatClicked(chatScreenViewModel.currentUserUID.value!!, gamerCall.userUID, false)
                        }
                       navController.navigate("ChatsScreen")
                    }
                )

            }

        }

////        To toggle Hide Menu screen
//        if (!isMenuVisible){
//        Surface(color = colorResource(id = R.color.white),
//            modifier = modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .zIndex(1f)
//                    .clickable { isMenuVisible = !isMenuVisible }
//        ) {}
//        }

    }
}


@Composable
fun G_Calls(
    modifier: Modifier = Modifier,
    profilePic:String,
    context: Context,
    gamerID:String,
    gameName:String,
    partySize:Int,
    callDes:String,
    onPartyUpClicked :() ->Unit,
) {

//        Variable declaration for menu
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth()
        .zIndex(1f)
    ){
        Column(
            modifier = modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
                .height(160.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = modifier
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 0.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(profilePic)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = modifier
                        .padding(top = 12.dp, end = 8.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(50)),
                    error = painterResource(id = R.drawable.sadpaprx),
                    placeholder = painterResource(id = R.drawable.defaultpp)
                )

                Column {
//                Menu Icon
                    Row(
                        modifier = modifier
                            .padding(top = 6.dp)
                            .background(
                                color = colorResource(id = R.color.DarkBG),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .height(32.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = gameName,
                            style = MaterialTheme.typography.titleSmall,
                            color = colorResource(id = R.color.white),
                            modifier = modifier
                                .padding(start = 4.dp,top = 8.dp)
                        )

                        if (!isMenuVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.menu_icon_white),
                                contentDescription = "MenuIcon",
                                modifier = modifier
                                    .size(16.dp)
                                    .clickable { isMenuVisible = !isMenuVisible }
                            )
                        }
                        if (isMenuVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.close_white),
                                contentDescription = "MenuIcon",
                                modifier = modifier
                                    .size(18.dp)
                                    .clickable { isMenuVisible = !isMenuVisible }
                            )
                        }
                    }

//                Name + Other details
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(
                                color = colorResource(id = R.color.DarkBG),
                                shape = RoundedCornerShape(15.dp)
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = gamerID,
                            style = MaterialTheme.typography.labelSmall,
                            color = colorResource(id = R.color.SubliminalText),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .width(94.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.weight(1f))

//                    Icons
                        Row( modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.playericon_white),
                                contentDescription = "NoOfPlayersIcon",
                                modifier = Modifier
                                    .padding(bottom = 1.dp)
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = partySize.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(bottom = 2.dp)
                            )

                        Spacer(modifier = Modifier.width(12.dp))

                            Image(
                                painter = painterResource(id = R.drawable.dmicon_white),
                                contentDescription = "Chat",
                                modifier = Modifier
                                    .padding(bottom = 1.dp)
                                    .size(16.dp)
                            )
                            Spacer(modifier = modifier.width(4.dp))
                            Text(
                                text = "PartyUp!",
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(end = 12.dp, bottom = 2.dp)
                                    .clickable { onPartyUpClicked() }
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = colorResource(id = R.color.CallWidgetBorder)
            )

//        Description
            Row(
                modifier = modifier
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(60.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = callDes,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.white),
                    modifier = modifier
                        .padding(12.dp, 0.dp)
                )
            }
        }


//        Menu
        if (isMenuVisible) {
            Box( modifier = Modifier
                .padding(
                    0.dp,
                    (dimensionResource(id = R.dimen.top_bar_height) - 24.dp),
                    32.dp,
                    0.dp
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(4.dp)
                )
                .zIndex(2f)
                .background(colorResource(id = R.color.black))
                .align(Alignment.TopEnd)
            ) {
                Column ( modifier = modifier
                    .padding(20.dp, 8.dp, 20.dp, 8.dp)
                ){
                    Row ( verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
//                        .fillMaxWidth()
                            .height(36.dp)
                            . clickable {
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/plain"
                                intent.putExtra(Intent.EXTRA_TEXT, "Hey Join This GamerCall in PartyFinder App")
                                val shareIntent = Intent.createChooser(intent, null)
                                context.startActivity(shareIntent) // Use the context parameter here
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.share_icon_white),
                            contentDescription = "ShareIcon",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 12.dp, 4.dp)
                                .size(18.dp)

                        )
                        Text(
                            text = "Share",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(id = R.color.primary)
                        )
                    }
                    Row ( verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
//                        .fillMaxWidth()
                            .height(36.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.report_blue),
                            contentDescription = "DeleteChats",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 12.dp, 4.dp)
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
}



@Composable
fun User_G_Calls(
    modifier: Modifier = Modifier,
    profilePic:String,
    context: Context,
    gamerID:String,
    gameName:String,
    partySize:Int,
    callDes:String,
    onDeleteClicked:()->Unit
) {

//        Variable declaration for menu
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth()
        .zIndex(1f)
    ){
        Column(
            modifier = modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
                .height(160.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = modifier
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 0.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(profilePic)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = modifier
                        .padding(top = 12.dp, end = 8.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(50)),
                    error = painterResource(id = R.drawable.sadpaprx),
                    placeholder = painterResource(id = R.drawable.defaultpp)
                )

                Column {
//                Menu Icon
                    Row(
                        modifier = modifier
                            .padding(top = 6.dp)
                            .background(
                                color = colorResource(id = R.color.DarkBG),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .height(32.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = gameName,
                            style = MaterialTheme.typography.titleSmall,
                            color = colorResource(id = R.color.white),
                            modifier = modifier
                                .padding(start = 4.dp,top = 8.dp)
                        )

                        if (!isMenuVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.menu_icon_white),
                                contentDescription = "MenuIcon",
                                modifier = modifier
                                    .size(16.dp)
                                    .clickable { isMenuVisible = !isMenuVisible }
                            )
                        }
                        if (isMenuVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.close_white),
                                contentDescription = "MenuIcon",
                                modifier = modifier
                                    .size(18.dp)
                                    .clickable { isMenuVisible = !isMenuVisible }
                            )
                        }
                    }

//                Name + Other details
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(
                                color = colorResource(id = R.color.DarkBG),
                                shape = RoundedCornerShape(15.dp)
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = gamerID,
                            style = MaterialTheme.typography.labelSmall,
                            color = colorResource(id = R.color.SubliminalText),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .width(94.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.weight(1f))

//                    Icons
                        Row( modifier = Modifier.padding(top = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.playericon_white),
                                contentDescription = "NoOfPlayersIcon",
                                modifier = Modifier
                                    .padding(bottom = 1.dp)
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = partySize.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(bottom = 2.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))
                            Row(modifier =Modifier.clickable { onDeleteClicked() }) {
                                Image(
                                    painter = painterResource(id = R.drawable.delete_blue),
                                    contentDescription = "Chat",
                                    modifier = Modifier
                                        .padding(bottom = 1.dp)
                                        .size(16.dp)
                                )
                                Spacer(modifier = modifier.width(4.dp))
                                Text(
                                    text = "Delete",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = colorResource(id = R.color.white),
                                    modifier = Modifier
                                        .padding(end = 12.dp, bottom = 2.dp)

                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = colorResource(id = R.color.CallWidgetBorder)
            )

//        Description
            Row(
                modifier = modifier
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(60.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = callDes,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.white),
                    modifier = modifier
                        .padding(12.dp, 0.dp)
                )
            }
        }


//        Menu
        if (isMenuVisible) {
            Box( modifier = Modifier
                .padding(
                    0.dp,
                    (dimensionResource(id = R.dimen.top_bar_height) - 24.dp),
                    32.dp,
                    0.dp
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(4.dp)
                )
                .zIndex(2f)
                .background(colorResource(id = R.color.black))
                .align(Alignment.TopEnd)
            ) {
                Column ( modifier = modifier
                    .padding(20.dp, 8.dp, 20.dp, 8.dp)
                ){
                    Row ( verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
//                        .fillMaxWidth()
                            .height(36.dp)
                            . clickable {
                                val intent = Intent(Intent.ACTION_SEND)
                                intent.type = "text/plain"
                                intent.putExtra(Intent.EXTRA_TEXT, "Hey Join This GamerCall in PartyFinder App")
                                val shareIntent = Intent.createChooser(intent, null)
                                context.startActivity(shareIntent) // Use the context parameter here
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.share_icon_white),
                            contentDescription = "ShareIcon",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 12.dp, 4.dp)
                                .size(18.dp)
                        )
                        Text(
                            text = "Share",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(id = R.color.primary)
                        )
                    }
                    Row ( verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
//                        .fillMaxWidth()
                            .height(36.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.report_blue),
                            contentDescription = "DeleteChats",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 12.dp, 4.dp)
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
}

//@Preview
//@Composable
//fun PreviewGamersCall(){
//    PartyFinderTheme {
//        GamersCall(
//            gamersCallsTopBar = { GamersCallTopBar(onBackClick = {})},
//            onCreateClick = {},
//            gamersCallContent = { GamersCallContent(listOfGamerCalls = datasource.gamerCallsList)}
//        )
//    }
//}
//

