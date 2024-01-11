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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.partyfinder.data.GamerCalls
import com.example.partyfinder.datasource.datasource





//creating other screens


@Composable
fun ProfileScreen(
    profileBannerWidget:@Composable () -> Unit,
    profileScreenContent:@Composable () ->Unit,
    modifier:Modifier=Modifier
    ){
    Surface(color= colorResource(id = R.color.black), modifier = modifier){
        Column(modifier = Modifier.verticalScroll(rememberScrollState(),true)) {
            profileBannerWidget()
            profileScreenContent()
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen(){
    PartyFinderTheme{
        ProfileScreen(
            profileBannerWidget = { ProfileBannerWidget(onEditProfileClick = {})},
            profileScreenContent = { ProfileScreenContent(
                profileDataWidget = { ProfileDataWidget(
                    gamerID ="Kaizoku",
                    gamerTag ="#2342" ,
                    isChangeStatusExapanded = false,
                    onChangeStatusClick ={},
                    profileUpdateStatus = {
                        ProfileUpdateStatus(
                            selectedStatusOption = datasource.userStatusOption.get(0),
                            onSelectionChanged = { /*TODO*/ },
                            options = datasource.userStatusOption
                        )
                    },
                    userStatus =datasource.userStatusOption.get(0) )},
                profileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = "Hello I like to play games")},
                profileRanksWidget = { ProfileRanksWidget(onUpdateRanksClick = { /*TODO*/ }) },
                profileMyGamerCallsWidget = { ProfileMyGamerCallsWidget(userGamerCalls = datasource.MyGamerCalls) }) }
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
    profileDataWidget:@Composable () ->Unit,
    profileScreenBioWidget:@Composable ()->Unit,
    profileRanksWidget:@Composable ()->Unit,
    profileMyGamerCallsWidget:@Composable () ->Unit,
    modifier: Modifier = Modifier,
    ){
    Column {
        profileDataWidget()
        profileScreenBioWidget()
        profileRanksWidget()
        profileMyGamerCallsWidget()
    }
}

@Composable
fun ProfileDataWidget(
    modifier: Modifier = Modifier.padding(16.dp),
    profileUpdateStatus: @Composable () -> Unit,
    gamerID:String,
    gamerTag:String,
    isChangeStatusExapanded:Boolean,
    onChangeStatusClick:()->Unit,
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
                    onClick = {
                            onChangeStatusClick()
                    },
                    modifier = Modifier.shadow(elevation =16.dp)
                ) {
                    if(isChangeStatusExapanded){
                        Text(
                            text = "Save Status",
                            style = MaterialTheme.typography.titleSmall)
                    }
                    else{
                        Text(
                            text = "Change Status",
                            style = MaterialTheme.typography.titleSmall)
                    }

                }
            }
            if(isChangeStatusExapanded){

                profileUpdateStatus()
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
fun ProfileMyGamerCallsWidget(modifier: Modifier = Modifier.padding(16.dp),userGamerCalls:List<GamerCalls>){
    Card(modifier= modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
        ) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(text = "My Gamer Calls", color = colorResource(id = R.color.primary), style = MaterialTheme.typography.titleSmall)
                if (userGamerCalls.isEmpty()){
                    Row(modifier= Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.main_padding),
                            end = dimensionResource(
                                id = R.dimen.main_padding
                            )
                        )
                        .wrapContentHeight()
                        .fillMaxWidth()) {
                        Text(
                            text = "No Gamer Calls Present add a gamer Call Right Now",
                            color = colorResource(id = R.color.white),
                            style = MaterialTheme.typography.titleSmall,
                            modifier= Modifier
                                .width(200.dp)
                                .weight(1f))

                        Image(
                            modifier= Modifier
                                .width(dimensionResource(id = R.dimen.profile_add_gamercall_icon_size))
                                .height(
                                    dimensionResource(id = R.dimen.profile_add_gamercall_icon_size)
                                ),
                            painter = painterResource(id = R.drawable.new_chat_img),
                            contentDescription = null)
                    }
                }
            else
                {
                    Column(modifier=Modifier.padding(top = 16.dp)) {
                        userGamerCalls.forEach(){
                            G_Calls(
                                profilePic = it.ProfilePic,
                                gamerID = it.gamerID,
                                gamerTag = it.gamerTag,
                                gameName = it.gameName,
                                partySize = it.partySize,
                                callDes =it.callDes
                            )
                        }
                    }
                }


        }
    }
}

@Composable
fun ProfileUpdateStatus(
    selectedStatusOption:Pair<Int,Int>,
    onSelectionChanged:(Pair<Int,Int>)->Unit,
    modifier:Modifier=Modifier,
    options:List<Pair<Int,Int>>
){
    Column(modifier=Modifier.padding(dimensionResource(id = R.dimen.main_padding))){
        options.forEach{ item ->
            Row(modifier=Modifier.selectable(selected = selectedStatusOption.first == item.first , onClick ={onSelectionChanged(item)}), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.primary)),selected = selectedStatusOption.first == item.first , onClick ={onSelectionChanged(item)})
                    Text(text = stringResource(id = item.first), color = colorResource(id = R.color.primary))
            }
        }
    }
}