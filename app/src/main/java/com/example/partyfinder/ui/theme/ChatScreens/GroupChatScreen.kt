package com.example.partyfinder.ui.theme.ChatScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.sp
import com.example.partyfinder.R
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun GroupChatScreen(){
    Surface(modifier = Modifier) {
        GroupChatTopBar()
        Column(modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.top_bar_height))
            .height(808.dp)
            .width(393.dp)
            .background(color = colorResource(id = R.color.black))
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { GroupReceiverDm() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
                item { GroupReceiverDm() }
                item { SenderDM() }
                item { SenderDM() }
            }
            DmChatInput()
        }
    }

}


@Composable
fun GroupChatTopBar(modifier: Modifier = Modifier) {
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
            painter = painterResource(id = R.drawable.settings_blue),
            contentDescription = "MenuIcon",
            modifier = modifier
                .padding(0.dp, 0.dp, 20.dp, 0.dp)
                .size(20.dp)
        )
    }
}



val GroupReceiverTriangle = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(Path().apply {
            moveTo(0f, size.height - 44f)
            lineTo(size.width - 8f, 19f) // Change the minus width to bring the top end to the right
            lineTo(size.width - 8f, 40f)
            close()
        })
    }
}




@Composable
fun GroupReceiverDm(modifier: Modifier = Modifier) {

    val bgcolor_of_dm = colorResource(id = R.color.DarkBG)

    Row(modifier = modifier
        .padding(0.dp, 8.dp, 0.dp, 0.dp)
        .fillMaxWidth()
//        .background(color = colorResource(id = R.color.SubliminalText))
    ){
        Image (
            painter = painterResource(id = R.drawable.pp),
            contentDescription = "58008",
            modifier = Modifier
                .padding(20.dp, 4.dp, 0.dp, 0.dp)
                .size(40.dp)
                .border(
                    (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                    RoundedCornerShape(50.dp)
                )
        )

        Box(modifier = modifier
            .padding(0.dp, 0.dp, 0.dp, 4.dp)
            .fillMaxWidth()
//        .background(color = colorResource(id = R.color.SubliminalText))
        ) {
            Column(
                modifier = modifier
                    .padding(start = 12.dp, end = 60.dp, top = 7.dp)
                    .heightIn(min = 28.dp, max = 300.dp)
                    .widthIn(min = 24.dp, max = 360.dp)
                    .background(
                        bgcolor_of_dm,
                        shape = RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
                    )
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "GroupChatScreen",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(12.dp, 8.dp, 12.dp, 0.dp)
                )
                Text(
                    text = "TF",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.white),
                    modifier = modifier
                        .padding(12.dp, 0.dp, 12.dp, 8.dp)
                )
            }
            Box(modifier = modifier
                .align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .width(16.dp)
                        .height(20.dp)
//                    .padding(top = 2.dp)
                        .background(
                            color = bgcolor_of_dm,
                            GroupReceiverTriangle
                        )
                )

            }
        }
    }
}



@Preview
@Composable
fun PreviewGroupChatScreen(){
    PartyFinderTheme {
        GroupChatScreen()
    }
}


