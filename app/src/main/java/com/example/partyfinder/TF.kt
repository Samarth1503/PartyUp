package com.example.partyfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun TF(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .height(808.dp)
            .width(393.dp)
        ) {
            GamersCallTopBar()
            GamersCallContent()
        }
    }
}


@Composable
fun GamersCallTopBar(modifier: Modifier = Modifier) {
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
            text = "Gamers Call",
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(id = R.color.primary)
        )
    }
}

@Composable
fun GamersCallContent(modifier: Modifier = Modifier) {
    Column(
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
        G_Calls()
        G_Calls()
    }
}


@Composable
fun G_Calls(modifier: Modifier = Modifier) {
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
    ){
        Row(
            modifier = modifier
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
                .height(100.dp)
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 0.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "GamerIcon",
                modifier = modifier
                    .size(76.dp)
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
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Image(
                        painter = painterResource(id = R.drawable.menu_icon_blue),
                        contentDescription = "MenuIcon",
                        modifier = modifier
                            .size(16.dp)
                    )
                }

//                Name + Other details
                Row(
                    modifier = modifier
                        .padding(top = 6.dp)
                        .background(
                            color = colorResource(id = R.color.DarkBG),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "58008",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white)
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


