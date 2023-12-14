package com.example.partyfinder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity



//creating other screens
@Composable
fun ProfileScreen(){
    Surface(color= colorResource(id = R.color.black)){
        Column(modifier = Modifier.verticalScroll(rememberScrollState(),true)) {
            ProfileBannerWidget()
            ProfileScreenContent()
        }
    }
}
@Preview
@Composable
fun PreviewProfileScreen(){
    ProfileScreen()
}

@Composable
fun ProfileBannerWidget(modifier: Modifier = Modifier){
    Box(modifier =modifier.height(dimensionResource(id = R.dimen.Profile_Banner_Box_Height))){
        Surface(modifier= Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.Profile_banner_height)),
            color = colorResource(id = R.color.on_tertiary)
        ) {

        }
        Surface(
            modifier= Modifier
                .padding(12.dp)
                .height(36.dp)
                .width(36.dp)
                .align(Alignment.TopEnd),
            shape = RoundedCornerShape(50),
            color = colorResource(id = R.color.teal_200)
        )
            {
            Image(
                painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                contentDescription =null,
                modifier=Modifier.padding(bottom = 4.dp, start = 1.dp))
        }
        Image(
            painter = painterResource(id = R.drawable.luffy),
            contentDescription =null,
            modifier= Modifier
                .padding(start = 16.dp)
                .height(dimensionResource(id = R.dimen.profile_picture_height))
                .width(dimensionResource(id = R.dimen.profile_picture_height))
                .clip(RoundedCornerShape(50))
                .align(Alignment.BottomStart)


        )
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier){
    Column {
        ProfileDataWidget()
        ProfileScreenBioWidget()
        ProfileRanksWidget()
        ProfileMyGamerCallsWidget()
    }
}

@Composable
fun ProfileDataWidget(modifier: Modifier = Modifier.padding(16.dp)){
    Card(
        modifier=modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_20))
    ) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(
                text = "Kaizoku",
                fontSize = 32.sp,
                color = colorResource(id = R.color.teal_200)
            )
            Text(text = "#3347", color = colorResource(id = R.color.teal_200))

            Row (verticalAlignment = Alignment.CenterVertically){
                Text(text = "icon", color = colorResource(id = R.color.green))
                Text(
                    text = "Status",
                    color = colorResource(id = R.color.white),
                    modifier= Modifier.padding(start=4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.neutral_40)),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.shadow(elevation =16.dp)
                ) {
                    Text(text = "Change Status")
                }
            }

        }
    }
}

@Composable
fun ProfileScreenBioWidget(modifier: Modifier = Modifier.padding(16.dp)){
    Card(modifier=modifier, colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_20))) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(text = "Bio", color = colorResource(id = R.color.teal_200))
            Text(color = colorResource(id = R.color.white),
                text = " Hello, I play Games for fun mostly.I like to play MMORPG games in which i can explore open world and take Decisions which affect my Gameplay.")
        }
    }
}

@Composable
fun ProfileRanksWidget(modifier: Modifier = Modifier
    .fillMaxWidth()
    .padding(16.dp)){
    Card(modifier=modifier.height(245.dp), colors = CardDefaults.cardColors(containerColor = colorResource(
        id = R.color.neutral_20
    )
    )) {
        Column(modifier= Modifier.padding(16.dp)) {
            Row(modifier= Modifier, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ranks",
                    fontSize = 20.sp,
                    color= colorResource(id = R.color.teal_200)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.shadow(elevation = 16.dp)
                ) {
                    Text(text = "Update Ranks")
                }
            }
            ProfileRankDisplay()
            ProfileRankDisplay()
            ProfileRankDisplay()
        }
    }
}

@Composable
fun ProfileRankDisplay(modifier: Modifier = Modifier
    .fillMaxWidth()
    .padding(top = 8.dp)){
    Row (modifier=modifier){
        Image(
            painter = painterResource(id = R.drawable.rank1_icon),
            contentDescription = null,
            modifier= Modifier
                .height(36.dp)
                .width(36.dp)
        )
        Column {
            Text(text = "Game_Name", color = colorResource(id = R.color.white))
            Text(text = "Rank", color = colorResource(id = R.color.white))
        }
    }

}


@Composable
fun ProfileMyGamerCallsWidget(modifier: Modifier = Modifier.padding(16.dp)){
    Card(modifier=modifier.fillMaxWidth()) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(text = "My Gamer Calls")
            Surface(modifier= Modifier
                .height(30.dp)
                .padding(top = 8.dp)) {
                Row(modifier= Modifier) {
                    Text(text = "No Gamer Calls Present add a gamer Call Right Now",modifier= Modifier.width(300.dp))
                    Text(text = "Add",modifier= Modifier.wrapContentWidth())
                }
            }
        }
    }
}