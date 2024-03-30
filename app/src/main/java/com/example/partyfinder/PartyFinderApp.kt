package com.example.partyfinder

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.model.UserAccount
import com.example.partyfinder.model.uiEvent.LoginUIEvent
import com.example.partyfinder.model.uiEvent.RegisterUIEvent
import com.example.partyfinder.ui.theme.ChatScreens.ChatMenu
import com.example.partyfinder.ui.theme.ChatScreens.ChatTopBar
import com.example.partyfinder.ui.theme.ChatScreens.Chats
import com.example.partyfinder.ui.theme.ChatScreens.ChatsScreen
import com.example.partyfinder.ui.theme.ChatScreens.DmChatInput
import com.example.partyfinder.ui.theme.ChatScreens.DmScreen
import com.example.partyfinder.ui.theme.ChatScreens.DmTopBar
import com.example.partyfinder.ui.theme.ChatScreens.SearchUserChatContent
import com.example.partyfinder.ui.theme.ChatScreens.SearchUserChatScreen
import com.example.partyfinder.ui.theme.ChatScreens.SearchUserChatTopBar
import com.example.partyfinder.ui.theme.CustomExposedDropDownMenu
import com.example.partyfinder.ui.theme.GamersCallScreens.CreateGamerCallContent
import com.example.partyfinder.ui.theme.GamersCallScreens.CreateGamerCallScreen
import com.example.partyfinder.ui.theme.GamersCallScreens.CreateGamerCallScreenTopBar
import com.example.partyfinder.ui.theme.GamersCallScreens.FilteredGamersCallContent
import com.example.partyfinder.ui.theme.GamersCallScreens.FilteredGamersCallScreen
import com.example.partyfinder.ui.theme.GamersCallScreens.FilteredGamersCallTopBar
import com.example.partyfinder.ui.theme.GamersCallScreens.GamersCall
import com.example.partyfinder.ui.theme.GamersCallScreens.GamersCallContent
import com.example.partyfinder.ui.theme.GamersCallScreens.GamersCallTopBar
import com.example.partyfinder.ui.theme.HomeScreen
import com.example.partyfinder.ui.theme.HomepageContent
import com.example.partyfinder.ui.theme.LoadingIndicator
import com.example.partyfinder.ui.theme.LoginAndRegisterScreens.LogInPage
import com.example.partyfinder.ui.theme.LoginAndRegisterScreens.RegisterPage
import com.example.partyfinder.ui.theme.LoginAndRegisterScreens.TermsAndConditons
import com.example.partyfinder.ui.theme.PartyFinderScreens.FindPartyScreen
import com.example.partyfinder.ui.theme.PartyFinderScreens.FindPartyScreenTopBar
import com.example.partyfinder.ui.theme.PartyFinderScreens.PartyFinderContent
import com.example.partyfinder.ui.theme.ProfileScreens.EditProfileScreen
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileBannerWidget
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileDataWidget
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileMyGamerCallsWidget
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileRanksWidget
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileScreen
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileScreenBioWidget
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileScreenContent
import com.example.partyfinder.ui.theme.ProfileScreens.ProfileUpdateStatus
import com.example.partyfinder.ui.theme.SpecificCommunityScreen
import com.example.partyfinder.ui.theme.ViewModels.CommunityViewModel
import com.example.partyfinder.ui.theme.ViewModels.CreateGamerCallsViewModel
import com.example.partyfinder.ui.theme.ViewModels.FilteredGamerCallsViewModel
import com.example.partyfinder.ui.theme.ViewModels.GamerCallsViewModel
import com.example.partyfinder.ui.theme.ViewModels.LoginViewModel
import com.example.partyfinder.ui.theme.ViewModels.PartyFinderViewModel
import com.example.partyfinder.ui.theme.ViewModels.ProfileViewModel
import com.example.partyfinder.ui.theme.ViewModels.RegistrationViewModel
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel


enum class PartyFinderScreen{
    HomeScreen,
    ProfileScreen,
    EditProfileScreen,
    FindPartyScreen,
    SpecificCommunityScreen,
    GamerCallsScreen,
    CreateGamerCallsScreen,
    FilteredGamerCallsScreen,
    ChatsScreen,
    LoginScreen,
    RegisterScreen,
    TermsAndConditionsScreen,
    SearchUserChatScreen
}

private fun navigateBack(navController: NavController) {
    navController.navigateUp()
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PartyFinderApp(
    localDBUserUID: String?,
    profileViewModel: ProfileViewModel,
    chatScreenViewModel: chatScreenViewModel,
    partyFinderScreenViewModel: PartyFinderViewModel,
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel,
    gamersCallViewModel:GamerCallsViewModel,
    createGamerCallViewModel: CreateGamerCallsViewModel,
    filterGamerCallsViewModel:FilteredGamerCallsViewModel,
    communityViewModel:CommunityViewModel
){
    val profileUiState by profileViewModel.profileState.collectAsState()
    val chatScreenUiState by chatScreenViewModel.chatsScreenUiState.collectAsState()
    val partyFinderScreenUiState by partyFinderScreenViewModel.partyFinderUiState.collectAsState()
    val gamerCallsUiState by gamersCallViewModel.GamerCallsUiState.collectAsState()
    val createGamerCallsUiState by createGamerCallViewModel.CreateGamerCallUiState.collectAsState()
    val filteredGamerCallsUiState by filterGamerCallsViewModel.FilteredGamerCallUiState.collectAsState()
    val communityUIState by communityViewModel.communityUiState.collectAsState()
    val navController : NavHostController= rememberNavController()

    val localUserEmail = remember { mutableStateOf("") }
    val localUserUID = remember { mutableStateOf("") }

    if (localDBUserUID != null){
        localUserUID.value = localDBUserUID
    }

    // Use LaunchedEffect to call getUserEmail()
    LaunchedEffect(key1 = registrationViewModel) {
        localUserEmail.value = registrationViewModel.importUserEmail()
        Log.d("PartyFinderApp TestCase", localUserEmail.value)
    }



    NavHost(
        navController = navController,
        startDestination = if (localUserUID.value == "") PartyFinderScreen.RegisterScreen.name else PartyFinderScreen.HomeScreen.name
//        startDestination = PartyFinderScreen.RegisterScreen.name
    ){
        composable(route= PartyFinderScreen.HomeScreen.name){
            HomeScreen(
                navigateToProfileScreen={navController.navigate(PartyFinderScreen.ProfileScreen.name)},
                navigateToChats = {navController.navigate(PartyFinderScreen.ChatsScreen.name)},
                navigateToPartyFinder = {navController.navigate(PartyFinderScreen.FindPartyScreen.name)},
                navigateToGamerCalls = {navController.navigate(PartyFinderScreen.GamerCallsScreen.name)},
                navigateToCommunities = {navController.navigate("SpecificCommunityScreen/${communityUIState.communityName}")},
                homepageContent = {
                    HomepageContent(
                        communitylist = communityUIState.communityList,
                        navController = navController,
                        gamerID = profileViewModel._profileUiState.value.gamerID,
                        userStatus = profileUiState.status,
                        myGamerCallsList = profileUiState.UserGamerCalls,
                        chatScreenViewModel = chatScreenViewModel,
                        randomGamerCallsList = profileUiState.random4GamerCallsOnHomeScreen
                    )
                }
            )
        }

        composable(route= PartyFinderScreen.LoginScreen.name){
            LogInPage(
                registrationViewModel = registrationViewModel,
                loginViewModel = loginViewModel,
                navigateToRegisterScreen = { navController.navigate(PartyFinderScreen.RegisterScreen.name) },
                onLogInClicked = { loginViewModel.onEvent(LoginUIEvent.LoginButtonCLicked) } ,
                navigateToHomeScreen = { navController.navigate(PartyFinderScreen.HomeScreen.name) }
            )
        }

        composable(route = PartyFinderScreen.TermsAndConditionsScreen.name ){
            TermsAndConditons(closeTermsScreen = {navController.popBackStack()})
        }

        composable(route = PartyFinderScreen.RegisterScreen.name) {
            RegisterPage(
                registrationViewModel = registrationViewModel,
                navigateToTermsAndConditions = {navController.navigate(PartyFinderScreen.TermsAndConditionsScreen.name)},
                navigateToLoginScreen = {navController.navigate(PartyFinderScreen.LoginScreen.name)},
                navigateToEditProfileScreen = {navController.navigate(PartyFinderScreen.EditProfileScreen.name)},
                onRegisterButtonClicked = {
                    registrationViewModel.onEvent(RegisterUIEvent.RegisterButtonClicked)
                    if (registrationViewModel.registrationSuccessful.value) {
                        localUserUID.value = registrationViewModel.importUserUID()
                    }
                }
            ) }

        composable(route = "SpecificCommunityScreen/{communityName}",arguments = listOf(navArgument("communityName"){type= NavType.StringType})){
                backStackEntry ->
                communityViewModel.updateCurrentCommunityName(backStackEntry.arguments!!.getString("communityName")!!)
            SpecificCommunityScreen(navigateUp = {navController.navigateUp()},
                communityViewModel = communityViewModel,
                currentCommunityScreenName = communityUIState.communityName,
                currentCommunityObject = communityUIState.communityObject,
                context = LocalContext.current)
        }

        composable(route = PartyFinderScreen.GamerCallsScreen.name){
            GamersCall(
                gamersCallsTopBar = { GamersCallTopBar {
                    navController.navigateUp()
                }},
                gamersCallContent = { GamersCallContent(
                    listOfGamerCalls = gamerCallsUiState.listOfGamersCall,
                    context = LocalContext.current,
                    chatScreenViewModel = chatScreenViewModel,
                    navController = navController)},
                onCreateClick = {navController.navigate(PartyFinderScreen.CreateGamerCallsScreen.name)}
            )
        }
        composable(route= PartyFinderScreen.CreateGamerCallsScreen.name){
            CreateGamerCallScreen(
                createGamerCallScreenTopBar = { CreateGamerCallScreenTopBar(onCloseButtonClick = { navController.navigateUp() })},
                createGamerCallContent = {
                    CreateGamerCallContent(
                        GameName = createGamerCallsUiState.gameName ,
                        NoOfGamers = createGamerCallsUiState.noOfGamers,
                        CallDescription = createGamerCallsUiState.DescriptionOfCall,
                        CallDuration = createGamerCallsUiState.CallDuration,
                        onGameNameValueChange = {createGamerCallViewModel.onGameNameValueChange(it)},
                        onNoOfGamersValueChange ={createGamerCallViewModel.onNoOfGamersValueChange(it)} ,
                        onCallDescriptionValueChange ={createGamerCallViewModel.onCallDescriptionValueChange(it)} ,
                        onCallDurationValueChange ={createGamerCallViewModel.onCallDurationValueChange(it)},
                        onPostButtonClick = {
                            createGamerCallViewModel.postGamerCall(
                                navigateAfterPost = { navController.navigateUp() },
                                GamerID = profileViewModel._profileUiState.value.gamerID,
                                GamerTag = profileViewModel._profileUiState.value.gamerTag,
                                GamerProfilePic = profileViewModel._profileUiState.value.profilePic
                            ) }
                    )
                }
            )
        }

        composable( route= PartyFinderScreen.FilteredGamerCallsScreen.name){
            FilteredGamersCallScreen(
                filteredGamersCallsTopBar = { FilteredGamersCallTopBar(onBackClick = {})},
                filteredGamersCallsContent = {
                    FilteredGamersCallContent(
                        FilterGamerCallGameMenu = { CustomExposedDropDownMenu(
                            placeholder = "Select the Game",
                            isDropDownExpanded =filteredGamerCallsUiState.isFGameNameDropDownExpanded ,
                            onExpandChange = {newValue -> filterGamerCallsViewModel.onGameNameExapandedChange(newValue)  },
                            DropDownSelectedValue =filteredGamerCallsUiState.FGameNameDropDownValue,
                            onValueChange = {newValue -> filterGamerCallsViewModel.onGameNameValueChange(newValue)  },
                            onDismissRequest = { filterGamerCallsViewModel.onGameNameDismiss()},
                            exposedMenuContent = {
                                filteredGamerCallsUiState.listOfGameNameItems.forEach{item ->
                                    DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { filterGamerCallsViewModel.onFGameNameItemClick(item)})
                                }
                            })
                        },
                        FilterNoOfGamerMenu = {
                            CustomExposedDropDownMenu(
                                placeholder = "Count" ,
                                isDropDownExpanded =filteredGamerCallsUiState.isFNoOfGamersDropDownExpanded ,
                                onExpandChange ={newValue -> filterGamerCallsViewModel.onNoOfGamersExapandedChange(newValue)} ,
                                onValueChange = {newValue ->  filterGamerCallsViewModel.onNoOfPlayersvalueChange(newValue)},
                                DropDownSelectedValue = filteredGamerCallsUiState.FNoOfGamersDropDownvalue,
                                onDismissRequest = {filterGamerCallsViewModel.onNoOfGamersDismiss()}) {
                                    filteredGamerCallsUiState.listofNoOfGamersItems.forEach { item ->
                                        DropdownMenuItem(
                                            text = { Text(text = item, color = colorResource(id = R.color.primary)) },
                                            onClick = {filterGamerCallsViewModel.onFNoOfGamersItemClick(item)})
                                    }
                            }
                        })
                }
                 )
        }

        composable(route = PartyFinderScreen.ChatsScreen.name){
            ChatsScreen(

                chatTopBar = { ChatTopBar(
                    isMenuClicked = chatScreenUiState.isMenuClicked,
                    navigateBack = { navigateBack(navController) },
                    onMenuClick ={chatScreenViewModel.onChatsScreenMenuClick()}
                )
                },
                chatMenu = { ChatMenu(
                    isMenuClicked = chatScreenUiState.isMenuClicked,
                    onMenuItemClicked = {},
                    onClearAllChat = {chatScreenViewModel.clearAllChatChannels()},
                    onMenuClicked ={chatScreenViewModel.onChatsScreenMenuClick()}
                ) },
                chats = { Chats(
                    chatChannelList = chatScreenUiState.channelList,
                    navController = navController,
                    onNewChatClicked = {chatScreenViewModel.onNewChatClicked(currentUserGamerID = profileUiState.gamerID, isGroupChatpara = false, user2UUID = "8llkiilC3QTG1O3JlB51eChAm083")},
                    chatScreenViewModel = chatScreenViewModel

                    )
                },
                isMenuClicked = chatScreenUiState.isMenuClicked,

            )
        }

        composable("DMScreen/{channelID}", arguments = listOf(navArgument("channelID"){type= NavType.StringType}))
        {backStackEntry ->
            chatScreenViewModel.setCurrentChatChannel(backStackEntry.arguments!!.getString("channelID")!!)
            var userAccount by remember { mutableStateOf<UserAccount?>(null) }

            if(chatScreenUiState.currentChannelObject != null && chatScreenUiState.currentChannelObject!!.memberTags.size > 1) {
                LaunchedEffect(chatScreenUiState.currentChannelObject!!.memberTags.get(1)) {
                    userAccount = chatScreenViewModel.retrieveUserAccount(
                        if(chatScreenUiState.currentChannelObject!!.memberTags.get(1) == profileViewModel.currentUserUID.value){
                            chatScreenUiState.currentChannelObject!!.memberTags.get(0)
                        } else {
                            chatScreenUiState.currentChannelObject!!.memberTags.get(1)
                        }
                    )
                }
            }
            else{
                Log.d("DmScreenUI TestCase" ,"CurrentChatChannelObject is empty or member Tags are less than 1")
            }

            if (userAccount != null) {
                DmScreen(
                    currentChatChannel = chatScreenUiState.currentChannelObject!!,
                    UserTag = profileUiState.gamerID,
                    dmTopBar ={
                        DmTopBar(
                            isMenuClicked = chatScreenViewModel.isDmScreenMenuClicked,
                            onMenuItemClicked = {},
                            currentChatChannel = chatScreenUiState.currentChannelObject!!,
                            onMenuClicked = {chatScreenViewModel.onDmScreenMenuClicked()},
                            navigateBack = { navigateBack(navController) },
                            retreivedGamerAccount = userAccount,
                            onClearChatClicked = { chatScreenViewModel.clearDMs()}
                        )
                    },
                    dmChatInput = {
                        DmChatInput(onSendButtonClick =   {
                            chatScreenViewModel.sendChatButtonClick(
                                author = profileUiState.gamerID,
                                fireBaseUniqueID = chatScreenUiState.currentChannel,
                                message = chatScreenUiState.message)
                        },
                            message = chatScreenUiState.message,
                            onMessageChange = { it -> chatScreenViewModel.onMessageValueChanged(it)})
                    }
                )
            }
            else{
                LoadingIndicator()
            }
        }


        composable(route= PartyFinderScreen.FindPartyScreen.name){
            FindPartyScreen(
                findPartyScreenTopBar = { FindPartyScreenTopBar() },
                partyFinderContent = { PartyFinderContent(
                    gameNameExposedDD = { CustomExposedDropDownMenu(
                        placeholder = "Select the Game",
                        isDropDownExpanded =partyFinderScreenUiState.isGameNameDDExtended ,
                        onExpandChange = { newValue ->  partyFinderScreenViewModel.onGameNameExpandedChanged(newValue)},
                        DropDownSelectedValue =partyFinderScreenUiState.gameNameSelectedValue ,
                        onValueChange = {newValue -> partyFinderScreenViewModel.onGameNameValueChange(newValue)  },
                        onDismissRequest = { partyFinderScreenViewModel.onGameNameDismissRequest() },
                        exposedMenuContent = {
                            partyFinderScreenUiState.listOfGameNameDDitems.forEach{item ->
                                DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { partyFinderScreenViewModel.onGameNameItemClicked(item) })
                            }
                        })
                    },
                    noOfPlayerInParty = { CustomExposedDropDownMenu(
                        placeholder = "Count",
                        isDropDownExpanded = partyFinderScreenUiState.isNoOfPlayerDDExtended,
                        onExpandChange ={newValue ->partyFinderScreenViewModel.onNoOfPartyExpandedChanged(newValue) } ,
                        onValueChange = {newValue -> partyFinderScreenViewModel.onNoOfPartyValueChange(newValue)  },
                        DropDownSelectedValue =partyFinderScreenUiState.noOfPlayersInParty,
                        onDismissRequest = { partyFinderScreenViewModel.onNoOfPartyDismissRequest() }) {
                        partyFinderScreenUiState.listOfNoOfPLayerDDitems.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { partyFinderScreenViewModel.onNoOfPartyItemClicked(item) })
                        }

                    }
                    },
                    noOfPlayersRequired = { CustomExposedDropDownMenu(
                        placeholder = "Count",
                        isDropDownExpanded = partyFinderScreenUiState.isNoOfPlayerRequiredDDExtended,
                        onExpandChange = {newValue -> partyFinderScreenViewModel.onNoOfRequiredExpandedChanged(newValue)  },
                        onValueChange = {newValue -> partyFinderScreenViewModel.onNoOfRequiredValueChange(newValue)  },
                        DropDownSelectedValue = partyFinderScreenUiState.noOfPlayerRequired,
                        onDismissRequest = { partyFinderScreenViewModel.onNoOfRequiredDismissRequest() }) {
                        partyFinderScreenUiState.listOfNoOfPLayerDDitems.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item, color = colorResource(id = R.color.primary)) }, onClick = { partyFinderScreenViewModel.onNoOfRequiredItemClicked(item) })
                        }
                    }
                    },
                    hideDetails = partyFinderScreenUiState.hideDetails,
                    onClickHideDetails = {partyFinderScreenViewModel.onHideDetailsClicked(partyFinderScreenUiState.hideDetails)},
                    onClickClearDetails = {partyFinderScreenViewModel.onClickClearDetails()},
                    isGamerCallLive = partyFinderScreenUiState.isGamerCallLive,
                    onClickSearch = {partyFinderScreenViewModel.onSearchClick()},
                    onClickStopCall = {partyFinderScreenViewModel.onStopCallClick()},
                    liveGamerCallResultList = partyFinderScreenUiState.liveGamerCallResultLits,
                    chatScreenViewModel = chatScreenViewModel,
                    navController = navController
                )
                }
            )
        }

        composable(route= PartyFinderScreen.ProfileScreen.name){
            ProfileScreen(
                profileBannerWidget = {
                    ProfileBannerWidget(
                        onEditProfileClick = { navController.navigate(PartyFinderScreen.EditProfileScreen.name) },
                        profilePic = profileViewModel._profileUiState.value.profilePic,
                        coverImage = profileViewModel._profileUiState.value.coverImageLink
                    ) },
                profileScreenContent = {
                    ProfileScreenContent(
                        profileDataWidget = { ProfileDataWidget(
                            gamerID = profileUiState.gamerID,
                            gamerTag = profileUiState.gamerTag,
                            userStatus = profileUiState.status,
                            isChangeStatusExapanded = profileUiState.isChangeStatusExpanded,
                            onChangeStatusClick = {profileViewModel.onChangeStatusClicked(profileUiState.isChangeStatusExpanded,profileViewModel.selectedStatus)},
                            profileUpdateStatus = {
                                ProfileUpdateStatus(
                                    selectedStatusOption = profileViewModel.selectedStatus,
                                    onSelectionChanged = { profileViewModel.updateStatus(it)},
                                    options = datasource.userStatusOption
                                )
                            }
                        )
                        },
                        profileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = profileUiState.bio) },
                        profileRanksWidget = {
                            ProfileRanksWidget(
                                onUpdateRanksClick = { navController.navigate(PartyFinderScreen.EditProfileScreen.name)},
                                ranks = profileViewModel.getProfileRanks()
                            ) },
                        profileMyGamerCallsWidget = { ProfileMyGamerCallsWidget(
                            userGamerCalls = profileUiState.UserGamerCalls,
                            context = LocalContext.current) },
                        logoutButtonClicked = {profileViewModel.logoutUser { navController.navigate(PartyFinderScreen.LoginScreen.name) }}
                    )
                }
            )

        }

        composable(route= PartyFinderScreen.EditProfileScreen.name) {
            EditProfileScreen(
                viewModel = profileViewModel,
                navigateBack = { navigateBack(navController) },
                navigateToHomeScreen = {navController.navigate(PartyFinderScreen.HomeScreen.name)},
                userUID = localUserUID.value,
                profilePic = profileUiState.profilePic
            )
        }

        composable(route = PartyFinderScreen.SearchUserChatScreen.name){
            SearchUserChatScreen(searchUserChatTopBar = { SearchUserChatTopBar(navigateUp = { navigateBack(navController)})},
                searchUserChatContent = { SearchUserChatContent(viewModel = chatScreenViewModel)}
            )
        }
    }
}