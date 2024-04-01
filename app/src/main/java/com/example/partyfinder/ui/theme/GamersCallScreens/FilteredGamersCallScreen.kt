package com.example.partyfinder.ui.theme.GamersCallScreens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.partyfinder.R
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun FilteredGamersCallScreen(
    filteredGamersCallsTopBar:@Composable ()->Unit,
    filteredGamersCallsContent:@Composable ()->Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.black))
    ) {
        filteredGamersCallsTopBar()
        filteredGamersCallsContent()
    }
}


@Composable
fun FilteredGamersCallTopBar(
    onBackClick:()->Unit
) {
    Box(
        modifier = Modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = (R.drawable.back_blue)),
            contentDescription = "BackIcon",
            modifier = Modifier
                .padding(25.dp, 5.dp, 0.dp, 0.dp)
                .size(26.dp)
                .align(Alignment.CenterStart)
                .clickable { onBackClick() }
        )
        Text(
            text = "Filtered Calls",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
    }
}



@Composable
fun FilteredGamersCallContent(
    modifier: Modifier = Modifier,
    filterOnClick: () -> Unit,
    clearFilterOnClick: () -> Unit,
    FilterGamerCallGameMenu: @Composable () -> Unit,
    FilterNoOfGamerMenu: @Composable () -> Unit,
    listOfGamersCall: GamerCallsList,
    chatScreenViewModel: chatScreenViewModel,
    navController: NavController,
    context: Context,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
//        .fillMaxHeight()
        .background(color = colorResource(id = R.color.black))
    ) {

        Text(text = "Select Filters",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.main_padding))
                .align(Alignment.CenterHorizontally)
        )

        Row(modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.main_padding),
                0.dp,
                dimensionResource(id = R.dimen.main_padding),
                dimensionResource(id = R.dimen.main_padding)
            )
            .fillMaxWidth()
            .zIndex(2f)
        ){
            Row(modifier = Modifier.weight(2f)) {
                FilterGamerCallGameMenu()
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Row(modifier = Modifier.weight(1f)) {
                FilterNoOfGamerMenu()
            }
        }

        Column {
            Row(modifier = Modifier
                .padding(dimensionResource(id = R.dimen.main_padding))
                .fillMaxWidth()
                .zIndex(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = modifier
                        .height(40.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { filterOnClick() },
                    border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
                ) {
                    Text(text = "Search",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary),
                        modifier = modifier
                            .padding(bottom = 4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.main_padding) + dimensionResource(id = R.dimen.main_padding),
                        dimensionResource(id = R.dimen.main_padding)
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Results",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "Clear",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier.clickable { clearFilterOnClick() }
                )
            }


            LazyColumn(
                modifier = modifier
//                        .padding(0.dp, 5.dp)
                    .background(
                        color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(dimensionResource(id = R.dimen.main_padding))
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(listOfGamersCall.gamerCalls.values.toList()) { gamerCall ->
                    G_Calls(
                        gameName = gamerCall.gameName,
                        callDes = gamerCall.callDes,
                        partySize = gamerCall.partySize,
                        profilePic = gamerCall.ProfilePic,
                        gamerID = gamerCall.gamerID,
                        context = context,
                        onPartyUpClicked = {
                            runBlocking {
                                chatScreenViewModel.onNewChatClicked(chatScreenViewModel.currentUserUID.value!!, gamerCall.userUID, false)
                            }

                            navController.navigate("ChatsScreen")
                        }
                    )

                }

            }
        }
    }
}



@Composable
fun CustomDropdownMenu(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .border(
            BorderStroke(
                1.dp,
                colorResource(id = R.color.SubliminalText)
            ),
            shape = RoundedCornerShape(12.dp)
        )
        .clip(RoundedCornerShape(12.dp))
        .background(colorResource(id = R.color.DarkBG))
        .fillMaxWidth(0.75f)
        .clickable { expanded = !expanded }
    ) {
        Row ( modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedItem,
                modifier = Modifier.padding(16.dp),
                color = colorResource(id = R.color.primary)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable { expanded = !expanded }
            )
        }

        if (expanded) {
            Divider()
            Column(modifier = Modifier
                .background(colorResource(id = R.color.DarkBG))
            ) {
                items.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemSelected(item)
                                expanded = false
                            }
                            .padding(16.dp),
                        color = colorResource(id = R.color.primary)
                    )
                }
            }
        }
    }
}

@Composable
fun GameFilterDropdown() {
    val games = listOf("None", "Valorant", "CS : GO", "Overwatch")
    var selectedGame by remember { mutableStateOf(games[0]) }

    CustomDropdownMenu(
        items = games,
        selectedItem = selectedGame,
        onItemSelected = { selectedGame = it }
    )
}

@Composable
fun PlayerFilterDropdown() {
    val numbers = listOf("1", "2", "3", "4", "5", "6+")
    var selectedNumber by remember { mutableStateOf(numbers[0]) }

    CustomDropdownMenu(
        items = numbers,
        selectedItem = selectedNumber,
        onItemSelected = { selectedNumber = it }
    )
}



//@Preview
//@Composable
//fun PreviewFilteredGamersCallScreen(){
//    var isGameNameExpanded by remember { mutableStateOf(false) }
//    var gameNameValue by remember { mutableStateOf("") }
//    var isNoOfPlayersExpanded by remember { mutableStateOf(false) }
//    var NoofGamersValue by remember { mutableStateOf("") }
//    PartyFinderTheme {
//        FilteredGamersCallScreen(
//            filteredGamersCallsTopBar = {
//                FilteredGamersCallTopBar(onBackClick = {})
//
//            },
//            filteredGamersCallsContent = {
//                FilteredGamersCallContent(
//                    FilterGamerCallGameMenu = { CustomExposedDropDownMenu(
//                        placeholder = "Select the Game ",
//                        isDropDownExpanded = isGameNameExpanded ,
//                        onExpandChange ={newValue -> isGameNameExpanded= newValue } ,
//                        onValueChange = {newValue ->gameNameValue = newValue  },
//                        DropDownSelectedValue =gameNameValue ,
//                        onDismissRequest = { isGameNameExpanded=false }) {
//                        datasource.FindPartyGamesMenuItems.forEach { item ->
//                            DropdownMenuItem(
//                                text = { Text(text = item, color = colorResource(id = R.color.primary)) },
//                                onClick = { gameNameValue =item })
//                        }
//                    }},
//                    FilterNoOfGamerMenu = {
//                        CustomExposedDropDownMenu(
//                            placeholder = "Count",
//                            isDropDownExpanded = isNoOfPlayersExpanded,
//                            onExpandChange = {newValue -> isNoOfPlayersExpanded = false },
//                            onValueChange = {newValue ->  NoofGamersValue = newValue},
//                            DropDownSelectedValue = NoofGamersValue,
//                            onDismissRequest = { isNoOfPlayersExpanded = false }) {
//                            datasource.FindPartyNoOfPlayerMenuItems.forEach { item ->
//                                DropdownMenuItem(
//                                    text = { Text(text = item, color = colorResource(id = R.color.primary)) },
//                                    onClick = { gameNameValue =item })
//                            }
//
//                        }
//                    }
//                )
//            }
//        )
//    }
//}