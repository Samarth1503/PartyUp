package com.example.partyfinder.ui.theme.ProfileScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.model.GamerCallsList
import com.example.partyfinder.model.Status
import com.example.partyfinder.model.uiState.Ranks
import com.example.partyfinder.ui.theme.GamersCallScreens.User_G_Calls
import com.example.partyfinder.ui.theme.ViewModels.CreateGamerCallsViewModel

//@Preview
//@Composable
//fun PreviewProfileScreen(){
//    PartyFinderTheme{
//        ProfileScreen(
//            viewModel = ProfileViewModel(retrievedUserUID = "", userUIDSharedViewModel = Unit),
//            profileBannerWidget = { ProfileBannerWidget(onEditProfileClick = {}) },
//            profileScreenContent = { ProfileScreenContent(
//                profileDataWidget = { ProfileDataWidget(
//                    gamerID ="Kaizoku",
//                    gamerTag ="#2342" ,
//                    isChangeStatusExapanded = false,
//                    onChangeStatusClick ={},
//                    profileUpdateStatus = {
//                        ProfileUpdateStatus(
//                            selectedStatusOption = datasource.userStatusOption.get(0),
//                            onSelectionChanged = { /*TODO*/ },
//                            options = datasource.userStatusOption
//                        )
//                    },
//                    userStatus =datasource.userStatusOption.get(0) )
//                },
//                profileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = "") },
//                profileRanksWidget = { ProfileRanksWidget(onUpdateRanksClick = { /*TODO*/ }) },
//                profileMyGamerCallsWidget = { ProfileMyGamerCallsWidget(userGamerCalls = datasource.gamerCallsList) }) }
//        )
//
//    }
//}


@Composable
fun ProfileScreen(
    profileBannerWidget: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = colorResource(id = R.color.black), modifier = modifier) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState(), true)) {
            profileBannerWidget()
            profileScreenContent()
        }
    }
}





@Composable
fun ProfileBannerWidget(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit,
    profilePic: String,
    coverImage: String
){
    Box(modifier =modifier.height(dimensionResource(id = R.dimen.Profile_Banner_Box_Height))){
        Surface(modifier= Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.Profile_banner_height)),
            color = colorResource(id = R.color.on_tertiary)
        ) {
            if (coverImage != null || coverImage != ""){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(coverImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Banner Image",
                    modifier = Modifier,
                    contentScale =  ContentScale.Crop
                )
            }
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                contentDescription = null,
                modifier=Modifier.padding(bottom = 4.dp, start = 1.dp))
        }

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(profilePic)
                .crossfade(true)
                .build(),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.main_padding))
                .height(dimensionResource(id = R.dimen.profile_picture_height))
                .width(dimensionResource(id = R.dimen.profile_picture_height))
                .clip(RoundedCornerShape(50))
                .align(Alignment.BottomStart),
            error = painterResource(id = R.drawable.sadpaprx),
            placeholder = painterResource(id = R.drawable.defaultpp),
            contentScale =  ContentScale.Crop
        )
    }
}

@Composable
fun ProfileScreenContent(
    profileDataWidget:@Composable () ->Unit,
    profileScreenBioWidget:@Composable ()->Unit,
    profileRanksWidget:@Composable ()->Unit,
    profileMyGamerCallsWidget:@Composable () ->Unit,
    logoutButtonClicked: ()-> Unit,
    modifier: Modifier = Modifier,
    ){
    Column {
        profileDataWidget()
        profileScreenBioWidget()
        profileRanksWidget()
        profileMyGamerCallsWidget()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .clip(RoundedCornerShape(1.dp)),
                onClick = { logoutButtonClicked() },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG),
                    disabledContainerColor = colorResource(id = R.color.CallWidgetBorder),
                    disabledContentColor = colorResource(id = R.color.SubliminalText)),
                border = BorderStroke(1.dp, colorResource(id = R.color.CallWidgetBorder)),
            ) {
                Text(
                    text = "Logout",
                    color = colorResource(id = R.color.primary)
                )
            }
        }
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
    userStatus: Status,
    ){

    if (userStatus.first == 0){
        userStatus.first = R.string.ONLINE
        userStatus.second = R.drawable.user_online_logo
    }


    Card(
        modifier=modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
    ) {
        Column(modifier= Modifier.padding(start = dimensionResource(id = R.dimen.main_padding),16.dp,16.dp,16.dp)) {
            Text(
                text = if (gamerID == "" || gamerID == null){ "GamerID" } else { gamerID },
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(text = gamerTag, color = colorResource(id = R.color.primary),
                modifier = Modifier.padding(start = 4.dp))

            Spacer(modifier = Modifier.height(4.dp))

            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 4.dp))
            {
               Image(painter = painterResource(userStatus.second) , contentDescription = null)

                Text(
                    text = stringResource(id = userStatus.first),
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier= Modifier.padding(start = 4.dp)
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
    Card(
        modifier= modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))) {
        Column(modifier= Modifier.padding(22.dp, 16.dp)
        ) {
            Text(
                text = "Bio",
                fontSize = 20.sp,
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.bodyLarge,
                text = if(gamerBio=="")"Edit Profile to enter your Bio" else gamerBio
            )
        }
    }
}

@Composable
fun ProfileRanksWidget(
    onUpdateRanksClick: () -> Unit,
    ranks: List<Ranks>
){
    Card(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
    ) {

        var buttonText : String
        if (ranks.isEmpty()){
            buttonText = "Add Ranks"
        } else {
            buttonText = "Update Ranks"
        }
        Column(modifier= Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ranks",
                    fontSize = 20.sp,
                    color= colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 6.dp,bottom = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tertiary_40)),
                    onClick = { onUpdateRanksClick()},
                    modifier = Modifier.shadow(elevation = 16.dp)
                ) {
                    Text(text = buttonText, style = MaterialTheme.typography.titleSmall)
                }
            }


            if (ranks.isEmpty()){
                Text(
                    text = "No Ranks to Display",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
            // Iterate over the ranks list and call ProfileRankDisplay for each Ranks object
            ranks.forEachIndexed { index, rank ->
                ProfileRankDisplay(gameNumber = index + 1, gameName = rank.gameName, rankName = rank.gameRank)
            }
        }
    }
}


@Composable
fun ProfileRankDisplay(
    gameNumber: Int,
    gameName: String,
    rankName: String,
){
    val rankIcon = when (gameNumber) {
        3 -> {
            painterResource(id = R.drawable.rank3_icon)
        }
        2 -> {
            painterResource(id = R.drawable.rank2_icon)
        }
        else -> {
            painterResource(id = R.drawable.rank1_icon)
        }
    }

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = rankIcon,
            contentDescription = null,
            modifier= Modifier
                .padding(top = 8.dp)
                .height(44.dp)
                .width(44.dp)

        )
        Column {
            Text(text = gameName, color = colorResource(id = R.color.primary), style = MaterialTheme.typography.titleSmall)
            Text(text = rankName, color = colorResource(id = R.color.white), style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Composable
fun ProfileMyGamerCallsWidget(
    modifier: Modifier = Modifier.padding(16.dp),
    userGamerCalls:GamerCallsList?,
    GamerCallsViewModel: CreateGamerCallsViewModel,
    context: Context
){
    Card(modifier= modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.neutral_10))
        ) {
        Column(modifier= Modifier.padding(16.dp)) {
            Text(
                text = "My Gamer Calls",
                fontSize = 20.sp,
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 6.dp, top = 4.dp)
            )
            if (userGamerCalls == null){
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
            } else {
                Column(modifier=Modifier.padding(top = 16.dp)) {
                    if (userGamerCalls != null) {
                        userGamerCalls.gamerCalls.forEach(){
                            Log.d("Profile-G-Calls TestCase ", "${it.value.ProfilePic} \n${it.value.gamerID}")
                            User_G_Calls(
                                profilePic = it.value.ProfilePic,
                                gamerID = it.value.gamerID,
                                gameName = it.value.gameName,
                                partySize = it.value.partySize,
                                callDes =it.value.callDes,
                                context = context,
                                onDeleteClicked = { GamerCallsViewModel.deleteGamerCall(it.value.gamerCallID,GamerCallsViewModel.currentUserUID.value!!)}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileUpdateStatus(
    selectedStatusOption: Status,
    onSelectionChanged: (Status) -> Unit,
    modifier: Modifier = Modifier,
    options: List<Status>
) {
    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.main_padding))) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = (item.first == selectedStatusOption.first),
                    onClick = { onSelectionChanged(item) }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.primary)),
                    selected = (item.first == selectedStatusOption.first),
                    onClick = { onSelectionChanged(item) }
                )
                Text(text = stringResource(id = item.first), color = colorResource(id = R.color.primary))
            }
        }
    }
}
