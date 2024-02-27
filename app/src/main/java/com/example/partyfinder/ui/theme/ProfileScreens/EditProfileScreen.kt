package com.example.partyfinder.ui.theme.ProfileScreens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.partyfinder.R
import com.example.partyfinder.ui.theme.LoadingIndicator
import com.example.partyfinder.ui.theme.ViewModels.ProfileViewModel

//@Preview(showBackground = true)
//@Composable
//fun EditProfileScreenPreview(){
//    PartyFinderTheme {
//        EditProfileScreen(
//            navigateBack = {},
//            viewModel = ProfileViewModel()
//        )
//    }
//}

@Composable
fun EditProfileScreen(
    modifier: Modifier=Modifier.fillMaxSize(),
    viewModel: ProfileViewModel,
    navigateBack: () -> Unit,
    userUID: String
    ){
    val uiState by viewModel.profileState.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    try {
        viewModel.fetchUID(userUID)
        Log.d("EditProfile TestCase","UID: $userUID")
        viewModel.fetchData()
        isLoading = false
    } catch (_: Exception){}

    if (!isLoading) {
        Column(modifier = modifier
            .background(color = colorResource(id = R.color.black))
            .verticalScroll(
                rememberScrollState()
            )
        ) {
            EditProfileScreenTopBar(navigateBack = navigateBack)

            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.Profile_Banner_Box_Height))){
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
                    color = colorResource(id = R.color.primary)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                        contentDescription =null,
                        modifier=Modifier.padding(bottom = 4.dp, start = 1.dp))
                }
                Box(modifier=Modifier.align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.luffy),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.main_padding))
                            .height(dimensionResource(id = R.dimen.profile_picture_height))
                            .width(dimensionResource(id = R.dimen.profile_picture_height))
                            .clip(RoundedCornerShape(50))

                    )
                    Surface(
                        modifier= Modifier
                            .height(36.dp)
                            .width(36.dp)
                            .align(Alignment.TopEnd),
                        shape = RoundedCornerShape(50),
                        color = colorResource(id = R.color.primary)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.pngtreeblack_edit_icon_4422168),
                            contentDescription =null,
                            modifier= Modifier
                                .padding(bottom = 4.dp, start = 1.dp)
                                .clickable {},
                        )
                    }
                }
            }


            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.main_padding))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EditProfileDataWidget(
                    gamerID = uiState.gamerID,
                    onValueChanged = { viewModel.onGamerIDChanged(it) },
                    bio = uiState.bio,
                    onBioValueChanged = { viewModel.onBioChanged(it) },
                    onSaveChanges = { viewModel.onSaveChangesClicked() }
                )

                // Update Ranks Section
                Spacer(modifier = Modifier.height(36.dp))

                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Your Ranks",
                            color = colorResource(id = R.color.primary),
                            style = MaterialTheme.typography.titleLarge
                        )

                        // For the underline effect
                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .height(1.dp)
                                .width(120.dp)
                                .background(color = colorResource(id = R.color.primary))
                        ) {}
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(4.dp))

                UpdateRankWidget(
                    gameNo = "Game 1",
                    gameName = uiState.rank1GameName,
                    onGameNameChanged = { viewModel.updateRank1(1, it, "") },
                    rank = uiState.rank1GameRank,
                    onRankValueChanged = { viewModel.updateRank1(2, "", it) }
                )
                UpdateRankWidget(
                    gameNo = "Game 2",
                    gameName = uiState.rank2GameName,
                    onGameNameChanged = { viewModel.updateRank2(1, it, "") },
                    rank = uiState.rank2GameRank,
                    onRankValueChanged = { viewModel.updateRank2(2, "", it) }
                )
                UpdateRankWidget(
                    gameNo = "Game 3",
                    gameName = uiState.rank3GameName,
                    onGameNameChanged = { viewModel.updateRank3(1, it, "") },
                    rank = uiState.rank3GameRank,
                    onRankValueChanged = { viewModel.updateRank3(2, "", it) }
                )
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { viewModel.onSaveChangesClicked() },
                    border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.primary))
                ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.titleSmall,
                        color = colorResource(id = R.color.primary),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
//        SnackbarHost(hostState = scaffoldState)
        }
    }
    else{
        LoadingIndicator()
    }
}

@Composable
fun EditProfileScreenTopBar(modifier:Modifier=Modifier,navigateBack:()->Unit){
        Box(modifier= modifier
            .height(dimensionResource(id = R.dimen.top_bar_height))
            .background(color = colorResource(id = R.color.DarkBG))
            .padding(start = dimensionResource(id = R.dimen.main_padding))
            .fillMaxWidth()
        ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_left_arrow_48),
                    contentDescription ="Back arrow",
                    modifier= Modifier
                        .height(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                        .width(dimensionResource(id = R.dimen.top_bar_back_icon_size))
                        .align(Alignment.CenterStart)
                        .clickable { navigateBack() }
                )
                Text(
                    text = "Edit Profile",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = colorResource(id = R.color.primary),
                    style = MaterialTheme.typography.titleMedium,
                )
        }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileDataWidget(
    gamerID:String,
    onValueChanged:(String)-> Unit,
    bio:String,
    onBioValueChanged:(String)->Unit,
    onSaveChanges: () -> Unit,
    modifier : Modifier = Modifier.padding(dimensionResource(id = R.dimen.main_padding))){

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = gamerID,
        onValueChange =onValueChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        modifier= Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.main_padding))
            .fillMaxWidth(),
        label = { Text(text = "Gamer_ID")},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = colorResource(id = R.color.primary),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary),
            unfocusedLabelColor = colorResource(id = R.color.primary),
            containerColor = colorResource(id = R.color.DarkBG),
            focusedBorderColor = colorResource(id = R.color.DarkBG),
            unfocusedBorderColor = colorResource(id = R.color.DarkBG))
    )

    Spacer(modifier = Modifier.height(30.dp))

    //        Textfield for Bio
    TextField(
        value = bio,
        onValueChange =onBioValueChanged,
        label = { Text(text = "Bio")},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        modifier= Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.main_padding))
            .fillMaxWidth()
            .height(180.dp)
            .drawBehind {
                val borderSize = 4.dp.toPx()
                drawLine(
                    color = Color.Cyan,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(id = R.color.DarkBG),
            focusedTextColor = colorResource(id = R.color.primary),
            unfocusedTextColor = colorResource(id = R.color.primary),
            unfocusedLabelColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary) )
    )
}


@Composable
fun UpdateRankWidget(
    gameNo:String,
    gameName:String,
    onGameNameChanged:(String)->Unit,
    rank:String,
    onRankValueChanged:(String)->Unit,
    modifier: Modifier=Modifier){
    Card (
        modifier= modifier
            .padding(
                start = dimensionResource(id = R.dimen.main_padding),
                top = dimensionResource(id = R.dimen.main_padding),
                bottom = 10.dp,
                end = dimensionResource(id = R.dimen.main_padding)
            )
            .border(
                2.dp,
                color = colorResource(id = R.color.neutral5),
                shape = RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DarkBG))
    ){
        Column(modifier= Modifier
            .padding(dimensionResource(id = R.dimen.main_padding))
            .fillMaxWidth()
        ) {
            Text(
                text = gameNo,
                color = colorResource(id = R.color.primary),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 8.dp, bottom = 12.dp))

            OutlinedTextField(
                value = gameName,
                onValueChange = onGameNameChanged,
                label = { Text(text = "Game Name", style = MaterialTheme.typography.bodyMedium)},
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                colors=OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = colorResource(id = R.color.primary),
                    focusedTextColor = colorResource(id = R.color.primary),
                    unfocusedTextColor = colorResource(id = R.color.primary),
                    focusedLabelColor = colorResource(id = R.color.primary),
                    focusedBorderColor = colorResource(id = R.color.primary),
                    cursorColor = colorResource(id = R.color.primary)

                )
            )
            OutlinedTextField(
                value = rank,
                onValueChange =onRankValueChanged,
                label = { Text(text = "Rank", style = MaterialTheme.typography.bodyMedium)},
                modifier=Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors=OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = colorResource(id = R.color.primary),
                    focusedTextColor = colorResource(id = R.color.primary),
                    unfocusedTextColor = colorResource(id = R.color.primary),
                    focusedLabelColor = colorResource(id = R.color.primary),
                    focusedBorderColor = colorResource(id = R.color.primary),
                    cursorColor = colorResource(id = R.color.primary)
                )
            )
        }
    }
}