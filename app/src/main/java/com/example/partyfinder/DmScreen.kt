package com.example.partyfinder

import android.media.ImageWriter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun DmScreen(){
    Surface(modifier = Modifier) {
        DmTopBar()
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
fun DmTopBar(modifier: Modifier = Modifier) {

//        Variable declaration for menu
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth()
        .zIndex(1f)
    ){
        Row(
            modifier = modifier
                .height(dimensionResource(id = (R.dimen.top_bar_height)))
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.DarkBG)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = (R.drawable.back_blue)),
                contentDescription = "BackIcon",
                modifier = modifier
                    .padding(16.dp, 2.dp, 0.dp, 0.dp)
                    .size(24.dp)
            )
            Image (
                painter = painterResource(id = R.drawable.pp),
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
                text = "58008",
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
                    .clickable { isMenuVisible = !isMenuVisible }
            )
        }
    }

//        Menu
    if (isMenuVisible) {
        Surface(color = colorResource(id = R.color.black),
            modifier = modifier
                .padding(
                    220.dp,
                    (dimensionResource(id = R.dimen.top_bar_height) - 24.dp),
                    0.dp,
                    0.dp
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(4.dp)
                )
                .zIndex(2f)
        ) {
            Column ( modifier = modifier
                .padding(20.dp, 8.dp, 20.dp, 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row ( verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
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
                Row ( verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .height(36.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.delete_blue),
                        contentDescription = "DeleteIcon",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 12.dp, 4.dp)
                            .size(18.dp)
                    )
                    Text(
                        text = "Delete Chats",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.primary)
                    )
                }
            }
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
        DmScreen()
    }
}


