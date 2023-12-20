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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.JuraBold
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun Community(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .height(808.dp)
            .width(393.dp)
        ) {
            SpecificCommunityTopBar()
            SpecificCommunityContent()
        }
    }
}

@Composable
fun SpecificCommunityTopBar(modifier: Modifier = Modifier) {
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
            text = "Community",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
        Image(
            painter = painterResource(id = (R.drawable.close_white)),
            contentDescription = "BackIcon",
            modifier = modifier
                .padding(25.dp, 2.dp, 16.dp, 0.dp)
                .size(20.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun SpecificCommunityContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.black),
//                shape = RoundedCornerShape(15.dp)
            )
    ) {

        Image(
            painter = painterResource(id = (R.drawable.valorant_two)),
            contentDescription = "BackIcon",
            modifier = modifier
                .height(120.dp)
                .background(color = colorResource(id = R.color.black))
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Change this to adjust the border thickness
                .background(Color.Red)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Change this to adjust the border thickness
                .background(Color.Red)
                .align(Alignment.BottomCenter)
        )
    }
    LazyColumn(
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
        items(2) {
            CommunityComments()
        }
    }
}


@Composable
fun CommunityComments(modifier: Modifier = Modifier) {

//        Variable declaration for menu
    var isLiked by remember { mutableStateOf(false) }

    Box ( modifier = modifier) {
        Column(
            modifier = modifier
                .padding(0.dp, 12.dp, 0.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
//                .height(160.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp, 8.dp, 16.dp, 0.dp)
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .heightIn(min = 80.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = modifier
                        .padding(0.dp, 4.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = modifier
                            .padding(0.dp, 4.dp, 0.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image (
                            painter = painterResource(id = R.drawable.pp),
                            contentDescription = "58008",
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 10.dp, 0.dp)
                                .size(45.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "58008_Rock",
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(id = R.color.primary),
                            modifier = modifier
                                .padding(start = 8.dp)
                        )
                    }
                    Row(
                        modifier = modifier
                            .padding(0.dp, 4.dp, 6.dp, 0.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "8 hrs ago",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(id = R.color.SubliminalText),
                            modifier = modifier
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }
                }
                Row {
                    Text(
                        text = "Need a 4 stack of cracked Valorant gamers for comp grind, And I mean Cracked(CRAZY) ",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white),
                        modifier = modifier
                            .padding(0.dp, 8.dp, 0.dp, 12.dp)
                    )
                }
            }


            Divider(color = colorResource(id = R.color.CallWidgetBorder), thickness = 1.dp)

//        Description
            Row(
                modifier = modifier
                    .padding(bottom = 2.dp)
                    .height(36.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = modifier
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    if (!isLiked) {
                        Image(
                            painter = painterResource(id = R.drawable.empty_heart_blue),
                            contentDescription = "Like",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 6.dp, 4.dp)
                                .size(16.dp)
                                .clickable { isLiked = !isLiked }
                        )
                    }
                    if (isLiked) {
                        Image(
                            painter = painterResource(id = R.drawable.filled_heart_blue),
                            contentDescription = "Like",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 6.dp, 4.dp)
                                .size(16.dp)
                                .clickable { isLiked = !isLiked }
                        )
                    }
                    Text(
                        text = "Like",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(id = R.color.white)
                    )
                }
                Row(
                    modifier = modifier
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.share_blue_1),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 6.dp, 4.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = "Share",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(id = R.color.white)
                    )
                }
                Row(
                    modifier = modifier
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.report_blue),
                        contentDescription = "Report",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 6.dp, 4.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = "Report",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCommunity(){
    PartyFinderTheme {
        Community()
    }
}


