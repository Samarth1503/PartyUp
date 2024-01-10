package com.example.partyfinder

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
fun CreatePartyScreen(){
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
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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
            .background(
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(15.dp)
            )
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
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.white)
        )

        var partyName by remember { mutableStateOf("") }
        Box ( modifier = modifier
            .padding(10.dp, 25.dp, 10.dp, 0.dp)
            .background(
                colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(4.dp)
            )
        ) {
            TextField(
                value = partyName,
                onValueChange = { partyName = it },
                label = {
                    Text(
                        text = "Party Name",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    backgroundColor = Color.Black,
                    cursorColor = colorResource(id = R.color.primary),
                    focusedBorderColor = colorResource(id = R.color.primary),
                    unfocusedBorderColor = colorResource(id = R.color.primary)
                ),
                modifier = Modifier
                    .background(colorResource(id = R.color.DarkBG))
                    .fillMaxWidth()
            )
        }

        Column ( modifier = modifier
            .padding(10.dp, 24.dp, 10.dp, 10.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(5.dp)
            )
//            .fillMaxWidth()
        ) {
            Row ( modifier = modifier
                .padding(0.dp, 15.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "Party Members",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(10.dp,0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = modifier.weight(1f))
            }
            val scrollState = rememberLazyListState()

            LazyRow(
                modifier = modifier
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                state = scrollState
            ) {
                items(13) { index ->
                    CreatePartyMemberIcon()
                }
            }



//            Add new member
            Column ( modifier = modifier
                .padding(10.dp, 4.dp, 10.dp, 20.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(5.dp)
                )
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = modifier
                        .height(40.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { },
                    border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
                ) {
                    Text(text = "Add",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary),
                        modifier = modifier
                            .padding(bottom = 4.dp)
                    )
                }
            }

        }


        //        CreatePartyButton
        Column ( modifier = modifier
            .padding(10.dp, 36.dp, 10.dp, 10.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(5.dp)
            )
        ) {
            Button(
                modifier = modifier
                    .height(40.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = { },
                border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
            ) {
                Text(text = "Create",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(bottom = 4.dp)
                )
            }
        }
    }
}


@Composable
fun CreatePartyMemberIcon(modifier: Modifier = Modifier){
    Column ( modifier = modifier
        .padding(10.dp, 15.dp, 10.dp, 10.dp)
        .background(
            color = colorResource(id = R.color.DarkBG),
            shape = RoundedCornerShape(5.dp)
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image (
            painter = painterResource(id = R.drawable.pp),
            contentDescription = "58008",
            modifier = Modifier
                .size(45.dp)
                .border(
                    (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                    RoundedCornerShape(50.dp)
                )
        )
        Text(text = "Kawakbanga",
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = R.color.white),
            modifier = modifier
                .padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCreatePartyScreen(){
    PartyFinderTheme {
        CreatePartyScreen()
    }
}


