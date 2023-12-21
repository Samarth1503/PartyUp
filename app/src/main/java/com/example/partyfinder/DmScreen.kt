package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Size
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
    Box(modifier = Modifier
        .height(808.dp)
        .width(393.dp))
    {
        DmTopBar()
        Column(modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.top_bar_height), bottom = dimensionResource(
                    id = R.dimen.top_bar_height
                )
            )
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.black))
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SenderDM()
            ReceiverDm()
            SenderDM()
            SenderDM()
        }
        DmChatInput(modifier = Modifier.align(Alignment.BottomCenter))
    }
}


@Composable
fun DmTopBar(modifier: Modifier = Modifier) {
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
        Row(
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
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "TF",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.white),
        )
        Image (
            painter = painterResource(id = R.drawable.send_4),
            contentDescription = "Send",
            modifier = Modifier
                .padding(20.dp, 0.dp, 10.dp, 0.dp)
                .size(40.dp),
            alignment = Alignment.CenterEnd
        )
    }
}


@Preview
@Composable
fun PreviewDmScreen(){
    PartyFinderTheme {
        DmScreen()
    }
}


