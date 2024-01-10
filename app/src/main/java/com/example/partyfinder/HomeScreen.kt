package com.example.partyfinder

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partyfinder.ui.theme.PartyFinderTheme
import androidx.compose.ui.zIndex


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
public fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

        HomepageContent()

//        NavigationBar
        Row(modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.DarkBG))
            .align(Alignment.BottomCenter)
            .zIndex(5f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier
                    .background(color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.CallWidgetBorder),
                        shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.networkingone_blue),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(14.dp, 8.dp)
                            .size(24.dp)
                    )
                }
                Text(
                    text = "Community",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.SubliminalText),
                    fontSize = 10.sp,
                    modifier = modifier
//                            .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                )
            }


            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier
                    .background(color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.CallWidgetBorder),
                        shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bookmark_blue),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(14.dp, 6.dp)
                            .size(24.dp)
                    )
                }
                Text(
                    text = "Gamer Calls",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.SubliminalText),
                    fontSize = 10.sp,
                    modifier = modifier
//                            .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                )
            }


            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier
                    .background(color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.CallWidgetBorder),
                        shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.link_blue),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(14.dp, 6.dp)
                            .size(30.dp)
                    )
                }
                Text(
                    text = "PartyUp!",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.SubliminalText),
                    fontSize = 10.sp,
                    modifier = modifier
//                            .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                )
            }

            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier
                    .background(color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.CallWidgetBorder),
                        shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.messagefilled_blue),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(14.dp, 6.dp)
                            .size(24.dp)
                    )
                }

                Text(
                    text = "Chats",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.SubliminalText),
                    fontSize = 10.sp,
                    modifier = modifier
//                            .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                )
            }


            Spacer(modifier = modifier.weight(1f))

            Column(modifier = modifier
                .padding(top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier
                    .background(color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(6.dp))
                    .border(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.CallWidgetBorder),
                        shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user_blue),
                        contentDescription = "Share",
                        modifier = modifier
                            .padding(14.dp, 6.dp)
                            .size(24.dp)
                    )
                }

                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.SubliminalText),
                    fontSize = 10.sp,
                    modifier = modifier
//                            .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                )
            }


            Spacer(modifier = modifier.weight(1f))

        }
    }
}



@Composable
fun PartyUpNavigationBar(modifier: Modifier = Modifier) {

}

@Composable
fun HomepageContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState(), true)
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = colorResource(id = R.color.black))
        .zIndex(1f)
    ) {

        Box(modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.black),
//                shape = RoundedCornerShape(15.dp)
            )
        ) {
            Image(
                painter = painterResource(id = (R.drawable.valorant)),
                contentDescription = "BackIcon",
                modifier = modifier
                    .height(180.dp)
                    .background(color = colorResource(id = R.color.black))
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // Change this to adjust the border thickness
                    .background(Color.Red)
                    .align(Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // Change this to adjust the border thickness
                    .background(Color.Red)
                    .align(Alignment.BottomCenter)
            )
        }

        Row(modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(15.dp)
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "#PoisonBish",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.primary),
                modifier = modifier
                    .padding(12.dp)
            )

//            PlayerStatus
            Row(modifier = modifier
                .padding(16.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(modifier = Modifier
                    .padding(top = 2.dp)
                    .size(12.dp)) {
                    drawCircle(color = Color.Green)
                }
                Text(
                    text = "Online",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.white),
                    modifier = modifier
                        .padding(4.dp, 12.dp)
                )
            }

            Spacer(modifier = modifier.weight(1f))
            Row(modifier = modifier
                .padding(end = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colorResource(id = R.color.tertiary))
            ) {
                Text(
                    text = "Update",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.white),
                    modifier = modifier.padding(10.dp, 6.dp)
                )
            }
        }

        Column(modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(15.dp)
            ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Find Your Allies",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.primary),
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.main_padding))
            )

            LazyRow(modifier = modifier.padding(bottom = 12.dp)) {
                items(4) {
                    G_Calls_Mini()
                }
            }
        }


//        My Calls
        Column(modifier = modifier
            .padding(12.dp, 12.dp, 12.dp, 0.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.DarkBG),
                shape = RoundedCornerShape(15.dp)
            ),
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = modifier
                .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Calls",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.primary),
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.main_padding))
                )
                Spacer(modifier = modifier.weight(1f))
                Row(modifier = modifier
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.tertiary))
                ) {
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white),
                        modifier = modifier.padding(16.dp, 6.dp)
                    )
                }
            }
            LazyRow(modifier = modifier.padding(bottom = 12.dp)) {
                items(4) {
                    My_Calls_Mini()
                }
            }
        }


//        Explore Section

        Text(
            text = "Explore",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.primary),
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.main_padding),16.dp)
        )
        Column {
            Row(modifier = modifier
                .padding(24.dp, 4.dp)
            ){
                CommunityCard_Mini(gameName = "alorant")
                Spacer(modifier = modifier.weight(1f))
                CommunityCard_Mini(gameName = "Valorant")
            }
            Row(modifier = modifier
                .padding(24.dp, 4.dp)
            ){
                CommunityCard_Mini(gameName = "aalorant")
                Spacer(modifier = modifier.weight(1f))
                CommunityCard_Mini(gameName = "Valorant")
            }
        }
    }
}


@Composable
fun CommunityCard_Mini(modifier: Modifier = Modifier, gameName: String) {
    val gameDrawable = when(gameName) {
        "Valorant" -> R.drawable.valorant
        "CS:GO" -> R.drawable.valorant
        "Overwatch" -> R.drawable.valorant
        "Albion" -> R.drawable.valorant
        "COC" -> R.drawable.valorant
        "WOW" -> R.drawable.valorant
        "LOL" -> R.drawable.valorant
        else -> R.drawable.default_community_filler
    }

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Transparent),
                    startY = 0.0f
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .height(120.dp)
            .width(160.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.CallWidgetBorder),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = painterResource(id = gameDrawable),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.black).copy(alpha = 0.35f))
        ) {}
        Text(
            text = gameName,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = colorResource(id = R.color.primary),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .background(colorResource(id = R.color.black).copy(alpha = 0.5f))
                .align(Alignment.BottomCenter)
        )
    }
}



@Composable
fun G_Calls_Mini(modifier: Modifier = Modifier) {

//        Variable declaration for menu
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .padding(12.dp, 0.dp, 4.dp, 12.dp)
        .width(132.dp)
        .zIndex(1f)
    ){
        Column(
            modifier = modifier
                .background(
                    color = colorResource(id = R.color.DarkBG),
                    shape = RoundedCornerShape(15.dp)
                )
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.CallWidgetBorder),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { isMenuVisible = !isMenuVisible },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Valorant",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(top = 12.dp)
            )

            Text(
                text = "#58008",
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.SubliminalText),
                modifier = modifier
                    .padding(bottom = 12.dp)
            )

            Divider(color = colorResource(id = R.color.CallWidgetBorder), thickness = 1.dp)


            if (!isMenuVisible) {
//                Bottom Row
                Row(
                    modifier = modifier
                        .padding(4.dp, 12.dp)
                        .background(
                            color = colorResource(id = R.color.DarkBG),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .height(16.dp)
                        .fillMaxWidth()
                        .clickable { isMenuVisible = !isMenuVisible },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){

                    Spacer(modifier = modifier.weight(2.5f))


//                    Icons
                    Row(modifier = modifier.padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.playericon_white),
                            contentDescription = "NoOfPlayersIcon",
                            modifier = modifier
                                .padding(bottom = 1.dp)
                                .size(16.dp)
                        )
                        Spacer(modifier = modifier.width(4.dp))
                        Text(
                            text = "5",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(id = R.color.white),
                            modifier = modifier
                                .padding(bottom = 2.dp)
                        )
                    }
                    Text(
                        text = "2hrs ago",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white),
                        modifier = modifier
                            .padding(12.dp, 0.dp)
                    )

                    Spacer(modifier = modifier.weight(1f))

                }
            }

            if (isMenuVisible) {

//                Bottom Row 2
                Row(
                    modifier = modifier
                        .padding(4.dp, 12.dp)
                        .background(
                            color = colorResource(id = R.color.DarkBG),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .height(16.dp)
                        .fillMaxWidth()
                        .zIndex(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Join",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id = R.color.white),
                        modifier = modifier
                            .padding(12.dp, 0.dp)
                    )
                }
            }
        }

    }
}


@Composable
fun My_Calls_Mini(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(12.dp, 0.dp, 4.dp, 12.dp)
        .width(180.dp)
        .background(
            color = colorResource(id = R.color.DarkBG),
            shape = RoundedCornerShape(15.dp)
        )
        .border(
            width = 1.dp,
            color = colorResource(id = R.color.CallWidgetBorder),
            shape = RoundedCornerShape(10.dp)
        )
    ){
        Row(modifier = modifier
            .padding(4.dp, 2.dp)
            .height(28.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Valorant",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 12.sp,
                color = colorResource(id = R.color.white),
                modifier = modifier
                    .padding(start = 12.dp)
            )

            Spacer(modifier = modifier.weight(2.5f))


//            Icons
            Row(modifier = modifier.padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.playericon_white),
                    contentDescription = "NoOfPlayersIcon",
                    modifier = modifier
                        .padding(bottom = 1.dp)
                        .size(12.dp)
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = "5",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.white),
                    modifier = modifier
                        .padding(bottom = 1.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.delete_blue),
                contentDescription = "NoOfPlayersIcon",
                modifier = modifier
                    .padding(4.dp, 0.dp, 8.dp, 1.dp)
                    .size(12.dp)
            )
            Spacer(modifier = modifier.weight(0.00001f))
        }
        Box(modifier = modifier.padding(bottom =  8.dp)){
            Row(
                modifier = modifier
                    .padding(16.dp, 0.dp)
                    .background(
                        color = colorResource(id = R.color.SubliminalText),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(3.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.SubliminalText),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){}
            Row(
                modifier = modifier
                    .padding(16.dp, 0.dp)
                    .background(
                        color = colorResource(id = R.color.progressBarGreen),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(3.dp)
                    .fillMaxWidth(0.5f)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.progressBarGreen),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ){}
            Text(
                text = "2hrs",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.SubliminalText),
                fontSize = 10.sp,
                modifier = modifier
                    .padding(start = 12.dp, top = 2.dp, end = 16.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}


@Preview
@Composable
fun PreviewHomeScreen(){
    PartyFinderTheme {
        HomeScreen()
    }
}


