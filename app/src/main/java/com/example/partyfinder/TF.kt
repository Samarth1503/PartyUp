package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.NotificationCompat.TvExtender
import com.example.partyfinder.ui.theme.Jura
import com.example.partyfinder.ui.theme.PartyFinderTheme
import kotlin.text.Typography


@Composable
fun TF(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .height(808.dp)
            .width(393.dp)
        ) {
                CreatePartyTopBar()
                CreatePartyContent()
        }
    }
}


//@Composable
//fun CreatePartyTopBar(modifier: Modifier = Modifier) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
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
//        )
//        Spacer(modifier = Modifier.weight(0.75f))
//        Text(
//            text = "Create Party",
//            style = MaterialTheme.typography.headlineLarge,
//            color = colorResource(id = R.color.primary)
//        )
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}

@Composable
fun CreatePartyTopBar(modifier: Modifier = Modifier) {
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
                .size(25.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Create Party",
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(id = R.color.primary)
        )
    }
}


@Composable
fun CreatePartyContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(dimensionResource(id = R.dimen.main_padding), 0.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//      Party Icon
        Row ( modifier = modifier
            .height(140.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image (
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "58008",
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }
        Text(
            text = "Party Icon",
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(id = R.color.white)
        )

        var partyName by remember { mutableStateOf("") }
        Surface ( modifier = modifier
            .padding(10.dp, 25.dp,10.dp, 0.dp)
            .fillMaxWidth()
            ) {
            TextField(
                value = partyName,
                onValueChange = { partyName = it },
                label = { Text("Party Name") }
            )
        }
        Row ( modifier = modifier
            .padding(10.dp, 25.dp,10.dp, 0.dp)
            .fillMaxWidth()
        ) {
            Text(
                text = "Party Members",
                style = MaterialTheme.typography.headlineLarge,
                color = colorResource(id = R.color.primary),
                modifier = modifier
                    .padding(10.dp,0.dp, 0.dp, 0.dp)
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


