package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.JuraBold
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun TF(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .height(808.dp)
            .width(393.dp)
        ) {
            DmTopBar()
            SenderDM()
            ReceiverDm()
        }
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
            lineTo(0f, size.height - 8f)
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
            lineTo(size.width, size.height - 8f)
//            lineTo(size.width, size.height)
//            lineTo(0f, size.height)
            close()
        })
    }
}


@Composable
fun ReceiverDm(modifier: Modifier = Modifier) {

    var bgcolor_of_dm = colorResource(id = R.color.DarkBG)

    Box(modifier = modifier
        .padding(dimensionResource(id = R.dimen.main_padding), 16.dp, 0.dp, 16.dp)
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.SubliminalText))
    ) {
        Row(
            modifier = modifier
                .padding(start = 12.dp, bottom = 7.dp)
                .heightIn(min = 28.dp, max = 300.dp)
                .width(280.dp)
                .background(bgcolor_of_dm)
                .align(Alignment.BottomStart)
        ) {


            Text(
                text = "Need a 4 stack of cracked Valorant gamers for comp grind, And I mean Cracked(CRAZY) ",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(16.dp, 8.dp, 8.dp, 8.dp)
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

    var bgcolor_of_dm = colorResource(id = R.color.DarkBG)

    Box(modifier = modifier
        .padding(dimensionResource(id = R.dimen.main_padding), 16.dp, 0.dp, 16.dp)
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.SubliminalText))
    ) {
        Row(
            modifier = modifier
                .padding(end = 12.dp, bottom = 7.dp)
                .heightIn(min = 28.dp, max = 300.dp)
                .width(280.dp)
                .background(bgcolor_of_dm)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "Need a 4 stack of cracked Valorant gamers for comp grind, And I mean Cracked(CRAZY) ",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(16.dp, 8.dp, 8.dp, 8.dp)
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


@Preview
@Composable
fun PreviewTF(){
    PartyFinderTheme {
        TF()
    }
}


