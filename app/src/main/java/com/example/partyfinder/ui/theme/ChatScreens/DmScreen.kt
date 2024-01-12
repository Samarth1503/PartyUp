package com.example.partyfinder.ui.theme.ChatScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R
import com.example.partyfinder.data.ChatChannel
import com.example.partyfinder.data.UserAccount
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun DmScreen(
    modifier:Modifier=Modifier,

    dmTopBar:@Composable ()->Unit,
    currentChatChannel: ChatChannel){
    Box(modifier=Modifier.fillMaxSize()){

        dmTopBar()

        Column(modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.top_bar_height))
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black))
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { ReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
            }
            DmChatInput()
        }
    }

}


@Composable
fun DmTopBar(
    isMenuClicked:Boolean,
    onMenuItemClicked:(item:Pair<String,Int>)->Unit,
    modifier: Modifier = Modifier,
    currentChatChannel: ChatChannel,
    navigateBack:() -> Unit,
    onMenuClicked:()->Unit,
    retreivedGamerAccount:UserAccount) {

    Box(modifier = modifier
        .fillMaxWidth()
    ){
        Card(modifier=Modifier
            .align(Alignment.CenterEnd)
            .shadow(10.dp)
            .padding(end = 8.dp)) {

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
                                onMenuItemClicked(item)
                                onMenuClicked()}
                        )
                    }

                }
            }
        }

        Row(
            modifier = modifier
                .height(dimensionResource(id = (R.dimen.top_bar_height)))
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.DarkBG)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_left_arrow_48),
                contentDescription = "BackIcon",
                modifier = modifier
                    .padding(16.dp, 2.dp, 0.dp, 0.dp)
                    .size(24.dp)
                    .clickable { navigateBack() }
            )
            Image (
                painter = if (currentChatChannel.isGroupChat){
                    painterResource(id = currentChatChannel.channelProfile)
                }
                else{
                    painterResource(id = retreivedGamerAccount.profilePic)
                },
                contentDescription = "58008",
                modifier = Modifier
                    .padding(20.dp, 0.dp, 10.dp, 0.dp)
                    .size(40.dp)
                    .border(
                        (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                        RoundedCornerShape(50.dp)
                    )
            )
            Text(
                text = if(currentChatChannel.isGroupChat){
                    currentChatChannel.channelName
                }else
                {
                    retreivedGamerAccount.gamerID
                },
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.primary)
            )
            Spacer(modifier = modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.threesquaremenu_blue),
                contentDescription = "menu",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .clickable { onMenuClicked() }
            )


        }

    }



}


//val ReceiverTriangle = object : Shape {
//    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
//        return Outline.Generic(Path().apply {
//            moveTo(size.width - size.width / 180f, 20f)
//            lineTo(0f, size.height)
//            lineTo(size.width, size.height)
//            close()
//        })
//    }
//}

val ReceiverTriangle = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(Path().apply {
            moveTo(size.width - size.width / 180f, 0f)
            lineTo(0f, size.height - 8f) // Change the minus height to bring the left end upwards
            lineTo(size.width, size.height - 20f)
            close()
        })
    }
}

val SenderTriangle = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(Path().apply {
            moveTo(size.width / 180f, 0f)
            lineTo(0f, size.height - 20f)
            lineTo(size.width, size.height -12f) // Change the minus height to bring the right end upwards
            close()
        })
    }
}


@Composable
fun ReceiverDm(modifier: Modifier = Modifier) {

    val bgcolor_of_dm = colorResource(id = R.color.DarkBG)

    Box(modifier = modifier
        .padding(0.dp, 8.dp, 0.dp, 0.dp)
        .fillMaxWidth()
//        .background(color = colorResource(id = R.color.SubliminalText))
    ) {
        Row(
            modifier = modifier
                .padding(start = 12.dp, end = 60.dp, bottom = 7.dp)
                .heightIn(min = 28.dp, max = 300.dp)
                .widthIn(min = 24.dp, max = 360.dp)
                .background(
                    bgcolor_of_dm,
                    shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp)
                )
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = "TF",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(12.dp, 8.dp, 12.dp, 8.dp)
            )
        }
        Box(modifier = modifier
            .align(Alignment.BottomStart)
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(20.dp)
                    .background(
                        color = bgcolor_of_dm,
                        ReceiverTriangle
                    )
            )

        }
    }
}



@Composable
fun SenderDM(modifier: Modifier = Modifier) {

    val bgcolor_of_dm = colorResource(id = R.color.receiverDmBackground)

    Box(modifier = modifier
        .padding(0.dp, 8.dp, 0.dp, 0.dp)
        .fillMaxWidth()
//        .background(color = colorResource(id = R.color.SubliminalText))
    ) {
        Column(
            modifier = modifier
                .padding(start = 60.dp, end = 12.dp, bottom = 7.dp)
                .heightIn(min = 28.dp, max = 300.dp)
                .widthIn(min = 24.dp, max = 360.dp)
                .background(
                    bgcolor_of_dm,
                    shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp)
                )
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "Need a 4 stack of cracked Valorant gamers for comp grind, And I mean Cracked(CRAZY) ",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(12.dp, 8.dp, 12.dp, 8.dp)
            )
//            Text(
//                text = "5:00pm",
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(id = R.color.white),
//                modifier = modifier
//                    .padding(0.dp, 2.dp, 12.dp, 8.dp)
//                    .align(Alignment.End)
//            )
        }
        Box(modifier = modifier
            .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(20.dp)
                    .background(
                        color = bgcolor_of_dm,
                        SenderTriangle
                    )
            )

        }
    }
}



@Composable
fun DmChatInput(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
//            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.black)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var message by remember { mutableStateOf("") }

        TextField(
            value = message,
            onValueChange = { message = it },
            placeholder = {
                Text("Enter Message",
                    style = MaterialTheme.typography.bodyMedium
                ) },
            modifier = Modifier
                .padding(8.dp, 4.dp, 8.dp, 4.dp)
                .weight(1f)
                .background(
                    colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(40.dp)
                )
                .clip(CircleShape),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.DarkBG),
                focusedContainerColor = colorResource(id = R.color.black),
                focusedPlaceholderColor = colorResource(id = R.color.primary),
                unfocusedPlaceholderColor = colorResource(id = R.color.primary),
                focusedTextColor = colorResource(id = R.color.SubliminalText),
                unfocusedTextColor = colorResource(id = R.color.SubliminalText),
                cursorColor = colorResource(id = R.color.primary))
        )

        Image (
            painter = painterResource(id = R.drawable.send_4),
            contentDescription = "Send",
            modifier = Modifier
                .padding(4.dp, 2.dp, 12.dp, 0.dp)
                .size(32.dp),
            alignment = Alignment.CenterEnd
        )
    }
}





//
//@Composable
//fun DmChatInput(modifier: Modifier = Modifier) {
//    Row(
//        modifier = modifier
//            .height(dimensionResource(id = (R.dimen.top_bar_height)))
//            .fillMaxWidth()
//            .background(color = colorResource(id = R.color.DarkBG)),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "TF",
//            style = MaterialTheme.typography.bodyMedium,
//            color = colorResource(id = R.color.white),
//        )
//        Image (
//            painter = painterResource(id = R.drawable.send_4),
//            contentDescription = "Send",
//            modifier = Modifier
//                .padding(20.dp, 0.dp, 10.dp, 0.dp)
//                .size(40.dp),
//            alignment = Alignment.CenterEnd
//        )
//    }
//}


@Preview
@Composable
fun PreviewDmScreen(){
    PartyFinderTheme {
        DmScreen(
            currentChatChannel = datasource.ChatChannels.get(0),
            dmTopBar = { DmTopBar(
                navigateBack = {},
                onMenuItemClicked = {},
                isMenuClicked = true,
            currentChatChannel = datasource.ChatChannels.get(0),
            onMenuClicked = { /*TODO*/ },
            retreivedGamerAccount = datasource.UserAccounts.get(0),

        )
            })
    }
}


