package com.example.partyfinder.ui.theme.PartyFinderScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.ui.theme.CustomExposedDropDownMenu
import com.example.partyfinder.ui.theme.PartyFinderTheme

@Composable
fun FindPartyScreen(
    modifier: Modifier =Modifier,
    findPartyScreenTopBar:@Composable () -> Unit,
    partyFinderContent:@Composable () ->Unit
    )
{
    Surface(modifier = modifier.fillMaxSize(), color = colorResource(id = R.color.black)) {
        Column {
            findPartyScreenTopBar()
            partyFinderContent()
        }
    }
}

@Preview
@Composable
fun PrevivewFindPartyScreen(){
    var gameExpanded by remember { mutableStateOf(false) }
    var gamevalue by remember { mutableStateOf("") }
    var partyExpanded by remember { mutableStateOf(false) }
    var partyValue by remember{ mutableStateOf("") }
    var requiredExpanded by remember { mutableStateOf(false) }
    var requiredvalue by remember{ mutableStateOf("") }
    var hideDetails by remember{ mutableStateOf(false) }
    var isCallLive by remember{ mutableStateOf(false) }
    PartyFinderTheme {
        FindPartyScreen(
            findPartyScreenTopBar = { FindPartyScreenTopBar() },
            partyFinderContent = { PartyFinderContent(
                gameNameExposedDD = { CustomExposedDropDownMenu(
                    placeholder = "Select the Game",
                    isDropDownExpanded =gameExpanded,
                    onExpandChange = { newValue -> gameExpanded= newValue},
                    DropDownSelectedValue =gamevalue ,
                    onValueChange = {newValue -> gamevalue = newValue },
                    onDismissRequest = { gameExpanded=false },
                    exposedMenuContent = {
                        datasource.FindPartyGamesMenuItems.forEach{item ->
                            DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { gamevalue = item})
                        }
                    })
                },
                noOfPlayerInParty = { CustomExposedDropDownMenu(
                    placeholder = "Count",
                    isDropDownExpanded = partyExpanded,
                    onExpandChange ={newValue ->   partyExpanded= newValue} ,
                    onValueChange = {newValue -> partyValue = newValue  },
                    DropDownSelectedValue =partyValue,
                    onDismissRequest = { partyExpanded =false }) {
                    datasource.FindPartyNoOfPlayerMenuItems.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { partyValue = item})
                    }

                }
                },
                noOfPlayersRequired = { CustomExposedDropDownMenu(
                    placeholder = "Count",
                    isDropDownExpanded = requiredExpanded,
                    onExpandChange = {newValue -> requiredExpanded= newValue  },
                    onValueChange = {newValue -> requiredvalue = newValue  },
                    DropDownSelectedValue = requiredvalue,
                    onDismissRequest = { requiredExpanded=false }) {
                    datasource.FindPartyNoOfPlayerMenuItems.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { requiredvalue=item})
                    }
                }
                },
                hideDetails = hideDetails,
                onClickHideDetails = {hideDetails = !hideDetails},
                onClickClearDetails = {
                    gamevalue =""
                    partyValue=""
                    requiredvalue=""},
                isGamerCallLive = isCallLive,
                onClickSearch = {isCallLive = true},
                onClickStopCall = {isCallLive =false}
            )
            }
        )
    }
}

@Composable
fun FindPartyScreenTopBar(
    modifier: Modifier = Modifier
        .height(dimensionResource(id = R.dimen.top_bar_height))
        .background(colorResource(id = R.color.DarkBG)),
){
    Box(modifier = modifier.fillMaxSize())
    {

        Text(
            text = "Party Finder",
            color= colorResource(id = R.color.primary),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = dimensionResource(id = R.dimen.main_padding)),
            style=MaterialTheme.typography.titleMedium
            )

        Box(
            modifier=Modifier
                .align(Alignment.CenterEnd)
                .wrapContentSize()
                .padding(end = 20.dp)
                .clickable {  },
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                tint = colorResource(id = R.color.primary),
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notification",
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyFinderContent(
    modifier:Modifier=Modifier,
    hideDetails:Boolean,
    onClickHideDetails:()->Unit,
    onClickClearDetails:()->Unit,
    isGamerCallLive:Boolean,
    onClickSearch:()->Unit,
    onClickStopCall:()->Unit,
    gameNameExposedDD:@Composable ()-> Unit,
    noOfPlayerInParty:@Composable ()->Unit,
    noOfPlayersRequired:@Composable ()->Unit,
){


    Card(modifier= modifier
        .padding(dimensionResource(id = R.dimen.main_padding))
        .wrapContentHeight()
        .fillMaxWidth(),
        colors =CardDefaults.cardColors(containerColor = colorResource(id = R.color.on_tertiary))

    ) {

        Row(modifier= Modifier
            .padding(10.dp)
            .clickable { onClickHideDetails()}){
            Text(
                modifier=Modifier.padding(start=10.dp),
                text = "Party Up!" ,
                color= colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier =Modifier.weight(1f) )
            Text(
                text = if(hideDetails)"Show Details" else "Hide Details",
                color =  colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleSmall)
            Icon(
                tint = colorResource(id = R.color.primary),
                imageVector = if (hideDetails) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                contentDescription = "show Ranks",
            )
        }
        if(!hideDetails) {
            Row(
                modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = "Enter Details:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.neutral_10)),
                    onClick =onClickClearDetails) {
                    Text(
                        text = "Clear Details",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary)
                    )
                }
            }
            Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                gameNameExposedDD()
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "Size of Your Party:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.primary)
                    )
                    noOfPlayerInParty()
                }
                Spacer(modifier = modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp, start = 10.dp),
                        text = "Players Required:",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.primary)
                    )
                    noOfPlayersRequired()
                }
            }
        }

        Row(modifier=Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)){
            if (!isGamerCallLive){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.edit_blue),
                        contentDescription = "edit call",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Edit Your Call",
                        color = colorResource(id = R.color.primary),
                        style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.neutral_10)),
                        onClick = onClickSearch) {
                            Text(
                                text = "Search",
                                color = colorResource(id = R.color.primary))
                    }
                }
            }
            else{
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(
                        modifier=Modifier.size(20.dp),
                        color = colorResource(id = R.color.primary)
                    )
                    Spacer(modifier=Modifier.width(10.dp))

                    Text(
                        text = "Your Call is Live",
                        color = colorResource(id = R.color.primary),
                        style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.neutral_10)),
                        onClick = onClickStopCall) {
                        Text(
                            text = "Stop Call",
                            color = colorResource(id = R.color.primary))
                    }
                }
            }


        }

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
    {
        items(7){
            item -> PartyFinderLiveCallsResult()
        }
    }

}

@Composable
fun PartyFinderLiveCallsResult(
    gamerID:String= "Sarang",
    modifier:Modifier=Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
){
    var requestSent by remember { mutableStateOf(false) }
    var showRanks by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DarkBG)))
    {
        Row(
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier= Modifier
                    .padding(end = 4.dp)
                    .size(dimensionResource(id = R.dimen.small_profile_pic_dim))
                    .border(
                        (BorderStroke(1.5.dp, colorResource(id = R.color.primary))),
                        RoundedCornerShape(50.dp)
                    ),
                painter = painterResource(id = R.drawable.pp),
                contentDescription ="pp" )
            Text(
                text = gamerID,
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.padding(),
                text ="#3425",
                color= colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier= Modifier
                    .width(120.dp)
                    .padding(end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.neutral_20)),
                onClick = { requestSent = !requestSent }) {
                if (!requestSent){
                    Text(text = "Request")}
                else
                {
                    Text(text = "Sent",
                        color= colorResource(id = R.color.primary)
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
        ){
            Text(
                text = "Party of : ",
                color= colorResource(id = R.color.primary),
                style = MaterialTheme.typography.bodyLarge)
            Image(
                modifier=Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.playericon_white),
                contentDescription ="partyImg" )
            Text(
                text = "4",
                color= colorResource(id = R.color.primary))

            Spacer(modifier = modifier.width(40.dp))
            Text(
                text = "Requires : ",
                color= colorResource(id = R.color.primary),
                style = MaterialTheme.typography.bodyLarge)
            Image(
                modifier=Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.playericon_white),
                contentDescription ="partyImg" )
            Text(
                text = "4",
                color= colorResource(id = R.color.primary))
        }

        Column(modifier =Modifier.padding(start = 20.dp, top = 10.dp, bottom = 20.dp)) {
            Row(
                modifier=Modifier.clickable { showRanks =!showRanks },
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Show Ranks",
                    color = colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    tint = colorResource(id = R.color.primary),
                    imageVector = if (!showRanks) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "show Ranks",
                )
            }
            if (showRanks){
                Row(modifier=Modifier.padding(start = 10.dp, top = 4.dp)) {
                    Text(
                        text = "Valorant",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Gold",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                }
                Row(modifier=Modifier.padding(start = 10.dp, top = 4.dp)) {
                    Text(
                        text = "Valorant",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Gold",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                }
                Row(modifier=Modifier.padding(start = 10.dp, top = 4.dp)) {
                    Text(
                        text = "Valorant",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "Gold",
                        color = colorResource(id = R.color.primary),
                        style =MaterialTheme.typography.bodyMedium)
                }

            }

        }

    }
}