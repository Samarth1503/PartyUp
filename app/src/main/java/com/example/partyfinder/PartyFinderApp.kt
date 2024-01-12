package com.example.partyfinder

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.partyfinder.datasource.datasource
import com.example.partyfinder.ui.theme.ChatScreens.ChatMenu
import com.example.partyfinder.ui.theme.ChatScreens.ChatTopBar
import com.example.partyfinder.ui.theme.ChatScreens.Chats
import com.example.partyfinder.ui.theme.ChatScreens.ChatsScreen
import com.example.partyfinder.ui.theme.ChatScreens.DmScreen
import com.example.partyfinder.ui.theme.ChatScreens.DmTopBar
import com.example.partyfinder.ui.theme.CustomExposedDropDownMenu
import com.example.partyfinder.ui.theme.GamersCallScreens.GamersCall
import com.example.partyfinder.ui.theme.HomeScreen
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
import com.example.partyfinder.ui.theme.ProfileScreens.UpdateRanksScreen
import com.example.partyfinder.ui.theme.SpecificCommunityScreen
import com.example.partyfinder.ui.theme.ViewModels.LoginViewModel
import com.example.partyfinder.ui.theme.ViewModels.PartyFinderViewModel
import com.example.partyfinder.ui.theme.ViewModels.ProfileViewModel
import com.example.partyfinder.ui.theme.ViewModels.RegistrationViewModel
import com.example.partyfinder.ui.theme.ViewModels.chatScreenViewModel


enum class PartyFinderScreen(){
    HomeScreen,
    ProfileScreen,
    EditProfileScreen,
    FindPartyScreen,
    UpdateRanksScreen,
    SpecificCommunityScreen,
    GamerCallsScreen,
    ChatsScreen,
    LoginScreen,
    RegisterScreen,
    TermsAndConditionsScreen,
    DMScreen
}

private fun
        navigateBack(navController: NavController){
            navController.navigateUp()
        }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyFinderApp(
    profileViewModel: ProfileViewModel = viewModel(),
    chatScreenViewModel: chatScreenViewModel = viewModel(),
    partyFinderScreenViewModel: PartyFinderViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel(),
    registerViewModel:RegistrationViewModel = viewModel(),
){

    val profileUiState by profileViewModel.profileState.collectAsState()
    val chatScreenUiState by chatScreenViewModel.chatsScreenUiState.collectAsState()
    val partyFinderScreenUiState by partyFinderScreenViewModel.partyFinderUiState.collectAsState()
    val navController : NavHostController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PartyFinderScreen.LoginScreen.name){
        composable(route= PartyFinderScreen.HomeScreen.name){
            HomeScreen(
                navigateToProfileScreen={navController.navigate(PartyFinderScreen.ProfileScreen.name)},
                navigateToChats = {navController.navigate(PartyFinderScreen.ChatsScreen.name)},
                navigateToPartyFinder = {navController.navigate(PartyFinderScreen.FindPartyScreen.name)},
                navigateToGamerCalls = {navController.navigate(PartyFinderScreen.GamerCallsScreen.name)},
                navigateToCommunities = {navController.navigate(PartyFinderScreen.SpecificCommunityScreen.name)}

            )
        }

        composable(route= PartyFinderScreen.LoginScreen.name){
            LogInPage(
                loginViewModel=loginViewModel,
                navigateToRegisterScreen = { navController.navigate(PartyFinderScreen.RegisterScreen.name) },
                onLogInClicked = {loginViewModel.login(navigateToHomeScreen = { navController.navigate(
                    PartyFinderScreen.HomeScreen.name) })}
            )
        }

        composable(route = PartyFinderScreen.TermsAndConditionsScreen.name ){
            TermsAndConditons(closeTermsScreen = {navController.popBackStack()})
        }

        composable( route = PartyFinderScreen.RegisterScreen.name){
            RegisterPage(
                registrationViewModel = registerViewModel,
                navigateToTermsAndConditions = {navController.navigate(PartyFinderScreen.TermsAndConditionsScreen.name)},
                navigateToLoginScreen = {navController.navigate(PartyFinderScreen.LoginScreen.name)},
                onRegisterButtonClicked = {registerViewModel.OnRegisterButtonClicked { navController.navigate(PartyFinderScreen.HomeScreen.name) }})
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
                )
                },
                chatMenu = { ChatMenu(
                    isMenuClicked = chatScreenUiState.isMenuClicked,
                    onMenuItemClicked = {},
                    onMenuClicked ={chatScreenViewModel.onChatsScreenMenuClick()}
                ) },
                chats = { Chats(
                    chatChannelList = chatScreenUiState.channelList,
                    userAccountList =datasource.UserAccounts,
                    navController = navController
                    )
                },
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
                    }
                )
                }
            )
        }

        composable(route= PartyFinderScreen.ProfileScreen.name){
            ProfileScreen(
                profileBannerWidget = { ProfileBannerWidget(onEditProfileClick = { navController.navigate(
                    PartyFinderScreen.EditProfileScreen.name) }) },
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

                        )
                        },
                        profileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = profileUiState.bio) },
                        profileRanksWidget = { ProfileRanksWidget(onUpdateRanksClick = { navController.navigate(
                            PartyFinderScreen.UpdateRanksScreen.name)}) },
                        profileMyGamerCallsWidget = { ProfileMyGamerCallsWidget(userGamerCalls =profileUiState.UserGamerCalls) })
                }
            )

        }

        composable(route= PartyFinderScreen.EditProfileScreen.name){
            EditProfileScreen(
                gamerID = profileViewModel.gamerIDtextfieldValue,
                onGamerIDchanged = {profileViewModel.onGamerIDtextFieldChanged(it)},
                bio = profileViewModel.gamerBiotextfieldValue,
                onGamerBioChanged = {profileViewModel.onGamerBioFieldChanged(it)},
                navigateBack = { navigateBack(navController) },
                onSaveChanges = {
                    profileViewModel.onSaveChangesClicked()
                    navController.navigate(PartyFinderScreen.ProfileScreen.name){popUpTo(
                        PartyFinderScreen.HomeScreen.name)}
                }
            )
        }

        composable(route= PartyFinderScreen.UpdateRanksScreen.name){
            UpdateRanksScreen(navigateBack = { navigateBack(navController) })
        }


    }
}