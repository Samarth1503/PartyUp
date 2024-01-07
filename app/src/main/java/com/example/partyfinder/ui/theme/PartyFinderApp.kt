package com.example.partyfinder.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.partyfinder.datasource.datasource


enum class PartyFinderScreen(){
    HomeScreen,
    ProfileScreen,
    EditProfileScreen,
    FindPartyScreen,
    UpdateRanksScreen,
    SpecificCommunityScreen,
    GamerCallsScreen,
    ChatsScreen,
    DMScreen
}

private fun
        navigateBack(navController: NavController){
            navController.navigateUp()
        }

@Composable
fun PartyFinderApp(
    profileViewModel: ProfileViewModel= viewModel(),
    chatScreenViewModel: chatScreenViewModel = viewModel()
){

    val profileUiState by profileViewModel.profileState.collectAsState()
    val chatScreenUiState by chatScreenViewModel.chatsScreenUiState.collectAsState()
    val navController : NavHostController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PartyFinderScreen.HomeScreen.name){
        composable(route=PartyFinderScreen.HomeScreen.name){
            HomeScreen(
                navigateToProfileScreen={navController.navigate(PartyFinderScreen.ProfileScreen.name)},
                navigateToChats = {navController.navigate(PartyFinderScreen.ChatsScreen.name)},
                navigateToPartyFinder = {navController.navigate(PartyFinderScreen.FindPartyScreen.name)},
                navigateToGamerCalls = {navController.navigate(PartyFinderScreen.GamerCallsScreen.name)},
                navigateToCommunities = {navController.navigate(PartyFinderScreen.SpecificCommunityScreen.name)}

            )
        }

        composable(route = PartyFinderScreen.SpecificCommunityScreen.name){
            SpecificCommunityScreen()
        }

        composable(route = PartyFinderScreen.GamerCallsScreen.name){
            GamersCall()
        }

        composable(route = PartyFinderScreen.ChatsScreen.name){
            ChatsScreen(
                chatTopBar = { ChatTopBar(
                    isMenuClicked = chatScreenUiState.isMenuClicked,
                    navigateBack = { navigateBack(navController) },
                    onMenuClick ={chatScreenViewModel.onChatsScreenMenuClick()}
                )},
                chatMenu = { ChatMenu(
                    isMenuClicked = chatScreenUiState.isMenuClicked,
                    onMenuItemClicked = {},
                    onMenuClicked ={chatScreenViewModel.onChatsScreenMenuClick()}
                ) },
                chats = { Chats(
                    chatChannelList = chatScreenUiState.channelList,
                    userAccountList =datasource.UserAccounts,
                    navController = navController
                    )},
                isMenuClicked = chatScreenUiState.isMenuClicked,
            )
        }

        composable("DMScreen/{channelID}", arguments = listOf(navArgument("channelID"){type= NavType.StringType}))
        {backStackEntry ->
            DmScreen(
                currentChatChannel = chatScreenViewModel.retreiveCurrentChannel(backStackEntry.arguments?.getString("channelID")),
                dmTopBar ={
                    DmTopBar(
                        isMenuClicked = chatScreenViewModel.isDmScreenMenuClicked,
                        onMenuItemClicked = {},
                        currentChatChannel =chatScreenViewModel.retreiveCurrentChannel(backStackEntry.arguments?.getString("channelID")),
                        onMenuClicked = {chatScreenViewModel.onDmScreenMenuClicked()},
                        navigateBack = { navigateBack(navController) },
                        retreivedGamerAccount =chatScreenViewModel.retreiveGamerAccount(chatScreenViewModel.retreiveCurrentChannel(backStackEntry.arguments?.getString("channelID")),datasource.UserAccounts) )
                })
        }

        composable(route=PartyFinderScreen.FindPartyScreen.name){
            FindPartyScreen()
        }

        composable(route=PartyFinderScreen.ProfileScreen.name){
            ProfileScreen(
                profileBannerWidget = { ProfileBannerWidget(onEditProfileClick = { navController.navigate(PartyFinderScreen.EditProfileScreen.name) }) }, 
                profileScreenContent = {
                    ProfileScreenContent(
                        profileDataWidget = { ProfileDataWidget(
                            gamerID = profileUiState.gamerID,
                            gamerTag = profileUiState.gamerTag,
                            userStatus =profileUiState.status,
                            isChangeStatusExapanded = profileUiState.isChangeStatusExpanded,
                            onChangeStatusClick = {profileViewModel.onChangeStatusClicked(profileUiState.isChangeStatusExpanded,profileViewModel.selectedStatus)},
                            profileUpdateStatus = {
                                ProfileUpdateStatus(
                                    selectedStatusOption = profileViewModel.selectedStatus,
                                    onSelectionChanged = { profileViewModel.updateStatus(it)},
                                    options =datasource.userStatusOption
                                )
                            }

                        )},
                        profileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = profileUiState.bio) },
                        profileRanksWidget = { ProfileRanksWidget(onUpdateRanksClick = { navController.navigate(PartyFinderScreen.UpdateRanksScreen.name)}) },
                        profileMyGamerCallsWidget = { ProfileMyGamerCallsWidget(userGamerCalls =profileUiState.UserGamerCalls) })
                }
            )

        }

        composable(route=PartyFinderScreen.EditProfileScreen.name){
            EditProfileScreen(
                gamerID = profileViewModel.gamerIDtextfieldValue,
                onGamerIDchanged = {profileViewModel.onGamerIDtextFieldChanged(it)},
                bio = profileViewModel.gamerBiotextfieldValue,
                onGamerBioChanged = {profileViewModel.onGamerBioFieldChanged(it)},
                navigateBack = {navigateBack(navController)},
                onSaveChanges = {
                    profileViewModel.onSaveChangesClicked()
                    navController.navigate(PartyFinderScreen.ProfileScreen.name){popUpTo(PartyFinderScreen.HomeScreen.name)}
                }
            )
        }

        composable(route=PartyFinderScreen.UpdateRanksScreen.name){
            UpdateRanksScreen(navigateBack = {navigateBack(navController)})
        }


    }
}