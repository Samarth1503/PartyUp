package com.example.partyfinder.ui.theme.ChatScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R
import com.example.partyfinder.ui.theme.PartyFinderTheme


@Composable
fun EditPartyScreen(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            EditPartyTopBar()
            EditPartyContent()
        }
    }
}


@Composable
fun EditPartyTopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG)),
        contentAlignment = Alignment.Center
    ) {
//        Heading
        Text(
            text = "Edit Party",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
//        Close Icon
        Image(
            painter = painterResource(id = (R.drawable.close_blue)),
            contentDescription = "BackIcon",
            modifier = modifier
                .padding(0.dp, 2.dp, 20.dp, 0.dp)
                .size(25.dp)
                .align(Alignment.CenterEnd)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPartyContent(modifier: Modifier = Modifier) {
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
        Box ( modifier = modifier
            .padding(bottom = 28.dp)
            .background(
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(15.dp)
            )
            .height(120.dp)
        ) {
            Image (
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "58008",
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .align(Alignment.BottomCenter)
                    .border(
                        (BorderStroke(1.5.dp, colorResource(id = R.color.SubliminalText))),
                        RoundedCornerShape(50.dp)
                    )
            )
            Image (
                painter = painterResource(id = R.drawable.edit_blue),
                contentDescription = "58008",
                modifier = Modifier
                    .size(22.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(colorResource(id = R.color.black))
                    .align(Alignment.BottomEnd)
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
            OutlinedTextField(
                value = partyName,
                onValueChange = { partyName = it },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = colorResource(id = R.color.SubliminalText),
                    unfocusedLabelColor = colorResource(id = R.color.SubliminalText),
                    focusedTextColor = colorResource(id = R.color.primary),
                    unfocusedTextColor = colorResource(id = R.color.primary),
                    focusedBorderColor = colorResource(id = R.color.primary),
                    unfocusedBorderColor = colorResource(id = R.color.primary))
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
                    text = "Edit Members",
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

        //        SavePartyButton
        Column ( modifier = modifier
            .padding(10.dp, 20.dp, 10.dp, 10.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(5.dp)
            )
        ) {
            Button(
                modifier = modifier
                    .height(40.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = { /* Add functionality */ },
                border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
            ) {
                Text(text = "Save",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(bottom = 4.dp)
                )
            }
        }


//        Report n Delete Group
        Row ( modifier = modifier
            .padding(top = 28.dp)
        ) {

            Spacer(modifier = modifier.weight(1f))

            Row ( modifier = modifier
                .height(44.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(5.dp)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.report_blue),
                    contentDescription = "ReportIcon",
                    modifier = modifier
                        .padding(16.dp, 5.dp, 12.dp, 4.dp)
                        .size(24.dp)
                )
                Text(
                    text = "Report",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(0.dp, 0.dp, 16.dp, 4.dp)
                )
            }

            Spacer(modifier = modifier.weight(0.75f))

            Row ( modifier = modifier
                .height(44.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(5.dp)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.delete_blue),
                    contentDescription = "ReportIcon",
                    modifier = modifier
                        .padding(16.dp, 5.dp, 12.dp, 4.dp)
                        .size(24.dp)
                )
                Text(
                    text = "Delete",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(0.dp, 0.dp, 16.dp, 4.dp)
                )
            }

            Spacer(modifier = modifier.weight(1f))

        }

    }
}
//
//
//@Composable
//fun CreatePartyMemberIcon(modifier: Modifier = Modifier){
//    Column ( modifier = modifier
//        .padding(10.dp, 15.dp, 10.dp, 10.dp)
//        .background(
//            color = colorResource(id = R.color.DarkBG),
//            shape = RoundedCornerShape(5.dp)
//        ),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image (
//            painter = painterResource(id = R.drawable.pp),
//            contentDescription = "58008",
//            modifier = Modifier
//                .size(45.dp)
//                .border(
//                    (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
//                    RoundedCornerShape(50.dp)
//                )
//        )
//        Text(text = "Kawakbanga",
//            style = MaterialTheme.typography.labelSmall,
//            color = colorResource(id = R.color.white),
//            modifier = modifier
//                .padding(top = 8.dp)
//        )
//    }
//}

@Preview
@Composable
fun PreviewEditPartyScreen(){
    PartyFinderTheme {
        EditPartyScreen()
    }
}


