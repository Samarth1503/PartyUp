package com.example.partyfinder.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R

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

//@Preview
//@Composable
//fun PrevivewFindPartyScreen(){
//    PartyFinderTheme {
//        FindPartyScreen(
//            findPartyScreenTopBar = { FindPartyScreenTopBar()},
//            partyFinderContent ={
//                PartyFinderContent(
//                    gameNameDropDownMenu = { CustomExposedDropDownMenu(
//                        isDropDownExpanded = true,
//                        onExpandedChange = {},
//                        DropDownSelectedValue ="",
//                        onDropDownValueChanged ={} ,
//                        onDismissRequest = { /*TODO*/ },
//                        menuItemList = datasource.FindPartyGamesMenuItems,
//                        onMenuItemClick = {})
//                    },
//                    )
//
//            }
//        )
//    }
//}

@Composable
fun FindPartyScreenTopBar(
    modifer: Modifier = Modifier
        .height(dimensionResource(id = R.dimen.top_bar_height))
        .background(colorResource(id = R.color.DarkBG)),
){
    Box(modifier = modifer.fillMaxSize())
    {

        Text(
            text = "Party Finder",
            color= colorResource(id = R.color.primary),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = dimensionResource(id = R.dimen.main_padding)),
            style=MaterialTheme.typography.titleMedium
            )

        Image(
            painter = painterResource(id = R.drawable.remove_icon),
            contentDescription =null,
            modifier=
            Modifier
                .padding(end = dimensionResource(id = R.dimen.main_padding))
                .height(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .width(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                .align(Alignment.CenterEnd)

        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyFinderContent(
    modifier:Modifier=Modifier,
    gameNameExposedDD:@Composable ()-> Unit,
){
    Card(modifier= modifier
        .padding(dimensionResource(id = R.dimen.main_padding))
        .wrapContentHeight()
        .fillMaxWidth(),
        colors =CardDefaults.cardColors(containerColor = colorResource(id = R.color.on_tertiary))

    ) {
        Text(
            modifier = Modifier.padding(top = 20.dp, start = 20.dp),
            text = "Enter Details:",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(id = R.color.primary)
        )
        gameNameExposedDD()
    }
}