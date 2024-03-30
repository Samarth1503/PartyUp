package com.example.partyfinder.ui.theme.ChatScreens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel
import java.util.Locale


@Composable
fun SearchUserChatScreen(
    searchUserChatTopBar: @Composable () -> Unit,
    searchUserChatContent: @Composable () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.black))
    ){
        searchUserChatTopBar()

        Spacer(modifier = Modifier.height(16.dp))

        searchUserChatContent()
    }
}


@Composable
fun SearchUserChatTopBar(
    navigateUp:()->Unit,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(dimensionResource(id = (R.dimen.top_bar_height)))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.black)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = (R.drawable.back_blue)),
            contentDescription = "BackIcon",
            modifier = modifier
                .padding(25.dp, 5.dp, 0.dp, 0.dp)
                .size(25.dp)
                .align(Alignment.CenterStart)
                .clickable { navigateUp() }
        )
        Text(
            text = "Search Friends",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
    }
}



@Composable
fun SearchUserChatContent(
    viewModel: chatScreenViewModel,
    navController: NavController
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val userListState = remember { mutableStateOf(emptyList<UserAccount>()) }

    LaunchedEffect(key1 = textState.value.text) {
        userListState.value = viewModel.searchUserDataInFirebase()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.black))
    ) {
        val mainPadding = dimensionResource(id = R.dimen.main_padding)
        SearchView(textState, mainPadding)
        ItemList(userListState, textState, mainPadding, viewModel, navController)
    }
}



@Composable
fun SearchView(state: MutableState<TextFieldValue>, mainPadding : Dp) {
    Row(
        modifier = Modifier
            .background(color = colorResource(id = R.color.black)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val primary = colorResource(id = R.color.primary)

        val containerColor = colorResource(id = R.color.DarkBG)
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            label = { Text(modifier = Modifier
                .padding(bottom = 4.dp),
                text = "Enter Gamer ID",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light)
            ) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colorResource(id = R.color.white),
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                cursorColor = primary,
                focusedBorderColor = primary,
                unfocusedBorderColor = primary,
                unfocusedLabelColor = colorResource(id = R.color.white),
            ),
            modifier = Modifier
                .padding(mainPadding, 0.dp)
                .weight(1f),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search_icon_blue),
                    contentDescription = "SearchIcon",
                    modifier = Modifier
                        .padding(end = mainPadding / 2)
                        .size(25.dp)
                )
            }
        )


    }
}




@Composable
fun ItemList(
    userListState: MutableState<List<UserAccount>>,
    textState: MutableState<TextFieldValue>,
    mainPadding: Dp,
    viewModel: chatScreenViewModel,
    navController: NavController
) {
    val searchedText = textState.value.text
    var chatChannelID by remember { mutableStateOf("") }

    val filteredItems = if (searchedText.isEmpty()) {
        userListState.value
    } else {
        userListState.value.filter { user ->
            user.gamerID.lowercase(Locale.getDefault())
                .contains(searchedText.lowercase(Locale.getDefault()))
        }
    }

    Text(
        text = "Results",
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(id = R.color.primary),
        modifier = Modifier
            .padding(start = mainPadding * 2, top = mainPadding + 8.dp, bottom = mainPadding)
    )

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = mainPadding)
    ) {
        items(filteredItems) { user ->
            ItemListItem(
                userID = user.gamerID,
                userTag = user.gamerTag,
                userProfilePic = user.profilePic,
                onItemClick = {
                    viewModel.currentUserUID.value?.let {
                        val channelID = viewModel.onNewChatClicked(
                            currentUserGamerID = it,
                            isGroupChatpara = false,
                            user2UUID = user.gamerID
                        )
                        if (channelID.isNotEmpty()) {
                            navController.navigate("DMScreen/$channelID")
                        }
                    }
                }
            )
        }
    }
}



@Composable
fun ItemListItem(
    userID: String,
    userTag: String,
    userProfilePic:String,
    onItemClick: () -> Unit)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(0.dp, 8.dp)
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .height(72.dp)
            .border(
                1.dp,
                colorResource(id = R.color.transparentBackdrop),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding) + 6.dp))

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(userProfilePic)
                .crossfade(true)
                .build(),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(
                    (BorderStroke(1.4.dp, colorResource(id = R.color.primary))),
                    RoundedCornerShape(50.dp)
                ),
            error = painterResource(id = R.drawable.close_blue),
            placeholder = painterResource(id = R.drawable.usericon_white)
        )


        Spacer(modifier = Modifier.width(16.dp))

        // Separate modifier for the Column
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = userID,
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.white)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "#$userTag",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.chatPreview)
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding)))
    }
}



//@Preview
//@Composable
//fun SearchUserChatScreenPreview(modifier: Modifier = Modifier) {
//    SearchUserChatScreen()
//}