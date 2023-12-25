package com.example.partyfinder.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.partyfinder.R

@Composable
fun FindPartyScreen(modifier: Modifier =Modifier)
{
    Surface(modifier = modifier.fillMaxSize(), color = colorResource(id = R.color.black)) {
        Column {
            FindPartyScreenTopBar()
        }
    }
}

@Preview
@Composable
fun PrevivewFindPartyScreen(){
    PartyFinderTheme {
        FindPartyScreen()
    }
}

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