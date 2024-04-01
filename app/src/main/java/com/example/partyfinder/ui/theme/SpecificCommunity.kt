package com.example.partyfinder.ui.theme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.partyfinder.R
import com.example.partyfinder.model.Community
import com.example.partyfinder.model.uiEvent.CommunityUIEvent
import com.example.partyfinder.model.uiEvent.PostUIEvent
import com.example.partyfinder.ui.theme.ViewModels.CommunityViewModel
import com.example.partyfinder.ui.theme.ViewModels.PostViewModel


@Composable
fun SpecificCommunityScreen(
    navigateUp:()->Unit,
    currentCommunityScreenName:String,
    currentCommunityObject:Community,
    context: Context,
    communityViewModel: CommunityViewModel){
    Surface(color= colorResource(id = R.color.black)){
        Box(modifier = Modifier
        ) {
            var newPostOverlay by remember {mutableStateOf(false)}
            Column {
                SpecificCommunityTopBar(navigateUp = navigateUp, communityName = currentCommunityScreenName)
                SpecificCommunityContent(currentCommunity = currentCommunityObject, currentCommunityName = currentCommunityScreenName, context = context)
            }

//            To add a new post
            Button(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(16.dp),
                onClick = { newPostOverlay = !newPostOverlay },
                border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG))
            ) {
                Text(modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = "Add a Post",
                    color = colorResource(id = R.color.primary) )
            }


            if(newPostOverlay){
                Column( modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.transparentMenuBG))
                    .clickable { newPostOverlay = !newPostOverlay },
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Column( modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.black)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))

                        Image(painter = painterResource(id = R.drawable.expanddownarrow_blue),
                            contentDescription = "close menu img",
                            modifier = Modifier
                                .height(24.dp)
                                .width(80.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        CustomTextField(labelValue = "Enter Message",
                            onTextSelected = { communityViewModel.onEvent(CommunityUIEvent.ContentChanged(it))} )

                        Spacer(modifier = Modifier.height(20.dp))
                        Row (
                            modifier = Modifier
                                .padding(
                                    dimensionResource(id = R.dimen.main_padding),
                                    0.dp,
                                    dimensionResource(id = R.dimen.main_padding),
                                    12.dp
                                )
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 24.dp),
                                shape = RoundedCornerShape(4.dp),
                                onClick = { communityViewModel.onEvent(CommunityUIEvent.NewPostAdded) },
                                border = BorderStroke(1.dp, colorResource(id = R.color.primary)),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.DarkBG))
                            ) {
                                Text(modifier = Modifier
                                    .padding(bottom = 4.dp),
                                    text = "Post",
                                    color = colorResource(id = R.color.primary) )
                            }
                        }
                    }

                }
            }
        }
    }
}



@Composable
fun SpecificCommunityTopBar(
    communityName:String,
    navigateUp:()->Unit,
    modifier: Modifier = Modifier) {
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
                .size(25.dp)
                .align(Alignment.CenterStart)
                .clickable { navigateUp() }
        )
        Text(
            text = communityName,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary)
        )
        Image(
            painter = painterResource(id = (R.drawable.close_white)),
            contentDescription = "BackIcon",
            modifier = modifier
                .clickable { navigateUp() }
                .padding(25.dp, 2.dp, 16.dp, 0.dp)
                .size(20.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun SpecificCommunityContent(
    modifier: Modifier = Modifier,
    currentCommunity: Community?,
    context:Context,
    currentCommunityName:String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.black),
//                shape = RoundedCornerShape(15.dp)
            )
    ) {
        val gameDrawable = when(currentCommunityName) {
            "Valorant" -> R.drawable.valorantimage2
            "CS:GO" -> R.drawable.csgo
            "Overwatch" -> R.drawable.overwatch
            "Albion" -> R.drawable.albion
            "COC" -> R.drawable.coc
            "WOW" -> R.drawable.wow
            "LOL" -> R.drawable.lol
            else -> R.drawable.default_community_filler
        }
        Image(
            painter = painterResource(id = (gameDrawable)),
            contentDescription = "BackIcon",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(200.dp)
                .background(color = colorResource(id = R.color.black))
                .fillMaxWidth()

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Change this to adjust the border thickness
                .background(Color.Cyan)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Change this to adjust the border thickness
                .background(Color.Cyan)
                .align(Alignment.BottomCenter)
        )
    }
    LazyColumn(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(dimensionResource(id = R.dimen.main_padding), 0.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       items(currentCommunity!!.communityPosts.values.toList()){
                CommunityPosts(
                    postID = it.postId,
                    userName = it.userName,
                    userProfilePic = it.userProfilepic,
                    postContent = it.postContent,
                    likes = it.likes,
                    reports = it.reports,
                    communityName = currentCommunityName,
                    context = context
                )
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CommunityPosts(
    modifier: Modifier = Modifier,
    postID: String,
    userName: String,
    context: Context,
    userProfilePic:String,
    postContent:String,
    likes: Int,
    reports: Int,
    communityName: String,
    postViewModel: PostViewModel = viewModel()) {

    postViewModel.postDataLoading(
        id = postID,
        postLikes = likes,
        postReports = reports,
        community = communityName
    )

    var postImage: Int? = null

    var isLiked by remember { mutableStateOf(false) }
    var likeIsClicked by remember { mutableStateOf(false) }

    Box ( modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(0.dp, 12.dp, 0.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
//                .height(160.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp, 8.dp, 16.dp, 0.dp)
                    .background(
                        color = colorResource(id = R.color.DarkBG),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .heightIn(min = 80.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = modifier
                        .padding(0.dp, 4.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = modifier
                            .padding(0.dp, 4.dp, 0.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 10.dp, 0.dp)
                                .size(45.dp)
                                .clip(CircleShape),
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(userProfilePic)
                                .crossfade(true)
                                .build() ,
                            contentDescription = null,
                            error = painterResource(id = R.drawable.sadpaprx),
                            placeholder = painterResource(id = R.drawable.defaultpp)
                        )
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(id = R.color.primary),
                            modifier = modifier
                                .padding(start = 8.dp)
                        )
                    }
                }
                Row {
                    if (postImage != null){
                        Image(painter = painterResource(id = postImage), contentDescription = "Post's Image")
                    }
                    Text(
                        text = postContent,
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white),
                        modifier = modifier
                            .padding(0.dp, 8.dp, 0.dp, 12.dp)
                    )
                }
            }


            Divider(color = colorResource(id = R.color.CallWidgetBorder), thickness = 1.dp)

//        Description
            Row(
                modifier = modifier
                    .padding(bottom = 2.dp)
                    .height(36.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = modifier
                        .padding(start = 16.dp)
                        .clickable {
                            isLiked = !isLiked
                            likeIsClicked = true
                        },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(modifier = modifier
                            .padding(end = 4.dp),
                        text = "${postViewModel.communityPostUIState.value.likes}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(id = R.color.white)
                    )
                    if (!isLiked) {
                        Image(
                            painter = painterResource(id = R.drawable.empty_heart_blue),
                            contentDescription = "Like",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 6.dp, 4.dp)
                                .size(16.dp) )
                    }
                    if (isLiked) {
                        Image(
                            painter = painterResource(id = R.drawable.filled_heart_blue),
                            contentDescription = "Like",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 6.dp, 4.dp)
                                .size(16.dp) )
                    }
                    Text(
                        text = "Like",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(id = R.color.white)
                    )
                }
//                Like Post functionality
                if (likeIsClicked){
                    if (isLiked){
                        postViewModel.onEvent(PostUIEvent.PostLiked)
                    }
                    if (!isLiked){
                        postViewModel.onEvent(PostUIEvent.PostUnLiked)
                    }
                    likeIsClicked = false
                }

                Row(
                    modifier = modifier
                        .padding(start = 20.dp)
                        .clickable { postViewModel.onEvent(PostUIEvent.PostShared) },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row(modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "Read the following post by $userName:\n$postContent \n Download The app now to read the post in App")
                        val shareIntent = Intent.createChooser(intent, null)
                        context.startActivity(shareIntent) // Use the context parameter here
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.share_blue_1),
                            contentDescription = "Share",
                            modifier = modifier
                                .padding(0.dp, 5.dp, 6.dp, 4.dp)
                                .size(16.dp)
                        )
                        Text(
                            modifier=Modifier.padding(top = 5.dp),
                            text = "Share",
                            style = MaterialTheme.typography.labelLarge,
                            color = colorResource(id = R.color.white)
                        )
                    }

                }
                Row(
                    modifier = modifier
                        .padding(start = 20.dp)
                        .clickable { postViewModel.onEvent(PostUIEvent.PostReported) },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.report_blue),
                        contentDescription = "Report",
                        modifier = modifier
                            .padding(0.dp, 5.dp, 6.dp, 4.dp)
                            .size(16.dp)
                    )
                    Text(
                        text = "Report",
                        style = MaterialTheme.typography.labelLarge,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewCommunity(){
//    PartyFinderTheme {
//        SpecificCommunityScreen(navigateUp = {}, communityViewModel = CommunityViewModel(),,)
//    }
//}


