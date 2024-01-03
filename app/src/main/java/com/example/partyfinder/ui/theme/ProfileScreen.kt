package com.example.partyfinder.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.R


//creating other screens

@Composable
fun ProfileScreen(
    modifier:Modifier=Modifier,
    ProfileBannerWidget:@Composable () ->Unit,
    ProfileScreenContent:@Composable () -> Unit
    ){
    Surface(color= colorResource(id = R.color.black), modifier = modifier){
        Column(modifier = Modifier.verticalScroll(rememberScrollState(),true)) {
            ProfileBannerWidget()
            ProfileScreenContent()
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen(){
    PartyFinderTheme{
        ProfileScreen(
            ProfileBannerWidget = {ProfileBannerWidget(onEditProfileClick = {})},
            ProfileScreenContent = {

            }
           )
    }
}

@Composable
fun ProfileBannerWidget(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit
){
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
                .clickable { onEditProfileClick() }
                .align(Alignment.TopEnd),
            shape = RoundedCornerShape(50),
            color = colorResource(id = R.color.primary)
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
                .padding(start = dimensionResource(id = R.dimen.main_padding))
                .height(dimensionResource(id = R.dimen.profile_picture_height))
                .width(dimensionResource(id = R.dimen.profile_picture_height))
                .clip(RoundedCornerShape(50))
                .align(Alignment.BottomStart)


        )
    }
}

@Composable
fun ProfileScreenContent(
    ProfileDataWidget: @Composable () -> Unit,
    ProfileScreenBioWidget: @Composable () -> Unit,
    ProfileRanksWidget:@Composable () -> Unit,
    ProfileMyGamerCallsWidget:@Composable () -> Unit
    ){
    Column {
        ProfileDataWidget()
        ProfileScreenBioWidget()
        ProfileRanksWidget()
        ProfileMyGamerCallsWidget()
    }
}

@Composable
fun ProfileDataWidget(
    modifier: Modifier = Modifier.padding(16.dp),
    gamerID:String,
    gamerTag:String,
    userStatus:Pair<Int,Int>,
    ){
    Card(
        modifier=modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
    ) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(
                text = gamerID,
                color = colorResource(id = R.color.primary),
                style=MaterialTheme.typography.headlineSmall
            )
            Text(text = gamerTag, color = colorResource(id = R.color.primary))

            Row (verticalAlignment = Alignment.CenterVertically){
               Image(painter = painterResource(userStatus.second) , contentDescription = null)
                Text(
                    text = stringResource(id = userStatus.first),
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier= Modifier.padding(start=4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.shadow(elevation =16.dp)
                ) {
                    Text(
                        text = "Change Status",
                        style = MaterialTheme.typography.titleSmall)
                }
            }

        }
    }
}

@Composable
fun ProfileScreenBioWidget(
    modifier: Modifier = Modifier.padding(16.dp),
    gamerBio:String,
){
    Card(modifier=modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(text = "Bio", color = colorResource(id = R.color.primary), style = MaterialTheme.typography.titleSmall)
            Text(color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.bodyLarge,
                text = gamerBio)
        }
    }
}

@Composable
fun ProfileRanksWidget(onUpdateRanksClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)){
    Card(modifier=modifier.wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = colorResource(
        id = R.color.neutral_10
    )
    )) {
        Column(modifier= Modifier.padding(16.dp)) {
            Row(modifier= Modifier, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ranks",
                    fontSize = 20.sp,
                    color= colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
                    onClick = { onUpdateRanksClick()},
                    modifier = Modifier.shadow(elevation = 16.dp)
                ) {
                    Text(text = "Update Ranks", style = MaterialTheme.typography.titleSmall)
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
                .padding(top = 8.dp)
                .height(44.dp)
                .width(44.dp)

        )
        Column {
            Text(text = "OverWatch 2", color = colorResource(id = R.color.primary), style = MaterialTheme.typography.titleSmall)
            Text(text = "Gold", color = colorResource(id = R.color.white), style = MaterialTheme.typography.bodyMedium)
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