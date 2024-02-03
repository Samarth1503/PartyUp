package com.example.partyfinder.ui.theme.GamersCallScreens

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.model.GamerCalls
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.ui.theme.PartyFinderTheme


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
                    .height(808.dp)
                    .width(393.dp)
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
    }
}

@Composable
fun GamersCallContent(
    modifier: Modifier = Modifier,
    listOfGamerCalls:List<GamerCalls>) {
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
            items(listOfGamerCalls) {
                G_Calls(
                    gameName =it.gameName,
                    callDes = it.callDes,
                    partySize = it.partySize,
                    profilePic = it.ProfilePic,
                    gamerID = it.gamerID,
                    gamerTag = it.gamerTag
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
    gamerID:String,
    gamerTag:String,
    gameName:String,
    partySize:Int,
    callDes:String
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
                    model =ImageRequest.Builder(context = LocalContext.current)
                        .data(profilePic)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = modifier
                        .padding(top = 12.dp, end = 8.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(50)),
                    error= painterResource(id = R.drawable.close_blue),
                    placeholder = painterResource(id = R.drawable.usericon_white)


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
                                .padding(top = 12.dp)
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
                        modifier = modifier
                            .padding(top = 4.dp)
                            .background(
                                color = colorResource(id = R.color.DarkBG),
                                shape = RoundedCornerShape(15.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
//                    horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = gamerID,
                            style = MaterialTheme.typography.labelSmall,
                            color = colorResource(id = R.color.SubliminalText),
                            modifier = modifier
                                .padding(start = 4.dp)
                        )

                        Text(
                            text = gamerTag,
                            style = MaterialTheme.typography.labelSmall,
                            color = colorResource(id = R.color.SubliminalText),
                            modifier = modifier
                                .padding(start = 2.dp)
                        )

                        Spacer(modifier = modifier.weight(1f))

//                    Icons
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.playericon_white),
                                contentDescription = "NoOfPlayersIcon",
                                modifier = modifier
                                    .padding(bottom = 1.dp)
                                    .size(16.dp)
                            )
                            Spacer(modifier = modifier.width(4.dp))
                            Text(
                                text =partySize.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.white),
                                modifier = modifier
                                    .padding(bottom = 2.dp)
                            )
                        }

                        Spacer(modifier = modifier.width(16.dp))

                        Image(
                            painter = painterResource(id = R.drawable.dmicon_white),
                            contentDescription = "NoOfPlayersIcon",
                            modifier = modifier
                                .padding(bottom = 1.dp)
                                .size(16.dp)
                        )
                        Spacer(modifier = modifier.width(4.dp))
                        Text(
                            text = "PartyUp!",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(id = R.color.white),
                            modifier = modifier
                                .padding(end = 16.dp, bottom = 2.dp)
                        )
                    }
                }
            }

            Divider(color = colorResource(id = R.color.CallWidgetBorder), thickness = 1.dp)

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



@Preview
@Composable
fun PreviewGamersCall(){
    PartyFinderTheme {
        GamersCall(
            gamersCallsTopBar = { GamersCallTopBar(onBackClick = {})},
            onCreateClick = {},
            gamersCallContent = { GamersCallContent(listOfGamerCalls = datasource.GamerCalls)}
        )
    }
}


