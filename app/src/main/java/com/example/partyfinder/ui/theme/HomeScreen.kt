package com.example.partyfinder.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R




@Composable
fun HomeScreen(
    modifier:Modifier=Modifier,
    navigateToProfileScreen:()->Unit,
    navigateToChats:()->Unit,
    navigateToPartyFinder:()->Unit,
    navigateToGamerCalls:()->Unit,
    navigateToCommunities:()->Unit,
    ){
    Box(modifier = modifier.fillMaxSize())
    {
        Surface(
            modifier
                .align(Alignment.BottomCenter)
                .padding(dimensionResource(id = R.dimen.main_padding))) {
            PartyFinderNavBar(
                modifier=Modifier,
                navigateToProfileScreen=navigateToProfileScreen,
                navigateToChats=navigateToChats,
                navigateToPartyFinder=navigateToPartyFinder,
                navigateToGamerCalls=navigateToGamerCalls,
                navigateToCommunities=navigateToCommunities

            )
        }
    }
}

@Composable
fun PartyFinderNavBar(
    modifier:Modifier=Modifier,
    navigateToProfileScreen:()->Unit,
    navigateToChats:()->Unit,
    navigateToPartyFinder:()->Unit,
    navigateToGamerCalls:()->Unit,
    navigateToCommunities:()->Unit,
){
    Box(modifier = modifier
        .height(100.dp)
        .fillMaxWidth()){
            Surface(
                modifier= Modifier
                    .height(64.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                shape = RoundedCornerShape(50),
                color = colorResource(id = R.color.neutral_10)
            ) {
                    Row(){
                            Surface(
                                modifier= Modifier
                                    .height(64.dp)
                                    .width(64.dp)
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                                    .clickable { navigateToCommunities() },
                                shape = RoundedCornerShape(topStartPercent = 100, bottomStartPercent = 100),
                                color= colorResource(id = R.color.neutral_20)

                            ) {
                                    Text(text = "A", style = MaterialTheme.typography.headlineMedium, color = colorResource(
                                        id = R.color.primary
                                    ))
                            }

                        Surface(
                            modifier= Modifier
                                .height(64.dp)
                                .width(64.dp)
                                .clickable { navigateToGamerCalls()}
                                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                            color= colorResource(id = R.color.neutral_20)


                        ) {
                            Text(text = "B", style = MaterialTheme.typography.headlineMedium, color = colorResource(
                                id = R.color.primary
                            ))
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        Surface(
                            modifier= Modifier
                                .height(64.dp)
                                .width(64.dp)
                                .clickable { navigateToChats() }
                                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp),
                            color= colorResource(id = R.color.neutral_20)


                            ) {
                            Text(text = "D", style = MaterialTheme.typography.headlineMedium, color = colorResource(
                                id = R.color.primary
                            ))
                        }

                        Surface(
                            modifier= Modifier
                                .height(64.dp)
                                .width(64.dp)
                                .clickable { navigateToProfileScreen() }
                                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp),
                            shape = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50),
                            color= colorResource(id = R.color.neutral_20)



                            ) {
                            Text(text = "E", style = MaterialTheme.typography.headlineMedium, color = colorResource(
                                id = R.color.primary
                            ))
                        }


                    }
            }
        Surface(
            modifier= Modifier
                .height(90.dp)
                .width(90.dp)
                .clickable { navigateToPartyFinder()}
                .align(Alignment.Center),
            shape = RoundedCornerShape(50),
            color= colorResource(id = R.color.neutral_20)
        ) {
            Text(
                text = "C",
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(id = R.color.primary),
                modifier=Modifier.padding(start = 35.dp, top = 25.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){

}