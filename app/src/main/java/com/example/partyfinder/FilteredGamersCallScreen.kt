package com.example.partyfinder

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.ui.theme.PartyFinderTheme
import androidx.compose.ui.zIndex


@Composable
fun FilteredGamersCallScreen() {
    Surface(modifier = Modifier) {
        FilteredGamersCallTopBar()
        FilteredGamersCallContent()
    }
}


@Composable
fun FilteredGamersCallTopBar(modifier: Modifier = Modifier) {
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
                .size(26.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Filtered Calls",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
    }
}



@Composable
fun FilteredGamersCallContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .padding(top = dimensionResource(id = R.dimen.top_bar_height))
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = colorResource(id = R.color.black))
    ) {

        Text(text = "Select Filters",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary),
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.main_padding))
                .align(Alignment.CenterHorizontally)
        )

        Box(modifier = modifier){
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
                GameFilterDropdown()
                Spacer(modifier = Modifier.weight(0.5f))
                PlayerFilterDropdown()
                Spacer(modifier = Modifier.weight(0.1f))
            }

            Column {
                Row(modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.main_padding),
                        80.dp,
                        dimensionResource(id = R.dimen.main_padding),
                        dimensionResource(id = R.dimen.main_padding)
                    )
                    .fillMaxWidth()
                    .zIndex(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = modifier
                            .height(40.dp),
                        shape = RoundedCornerShape(5.dp),
                        onClick = { },
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

                Row(modifier = Modifier
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

                    Text(text = "Sort",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary),
                        modifier = modifier
                    )
                }


                LazyColumn(modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.main_padding))
                ) {
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



@Preview
@Composable
fun PreviewFilteredGamersCallScreen(){
    PartyFinderTheme {
        FilteredGamersCallScreen()
    }
}


