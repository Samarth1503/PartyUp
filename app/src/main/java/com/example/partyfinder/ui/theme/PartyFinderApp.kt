package com.example.partyfinder.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



enum class PartyFinderScreen(){
    HomeScreen,
    ProfileScreen,
    EditProfileScreen,
    FindPartyScreen,
    UpdateRanksScreen,


}

private fun
        navigateBack(navController: NavController){
            navController.navigateUp()
        }

@Composable
fun PartyFinderApp(profileViewModel: ProfileViewModel= viewModel()){

    val profileUiState by profileViewModel.profileState.collectAsState()
    val navController : NavHostController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PartyFinderScreen.HomeScreen.name){
        composable(route=PartyFinderScreen.HomeScreen.name){
            HomeScreen(navigateToProfileScreen = {navController.navigate(PartyFinderScreen.ProfileScreen.name)})
        }

        composable(route=PartyFinderScreen.ProfileScreen.name){
            ProfileScreen(
                ProfileBannerWidget = {
                    ProfileBannerWidget(onEditProfileClick = {
                            navController.navigate(PartyFinderScreen.EditProfileScreen.name)
                        })
                                      },
                ProfileScreenContent = {
                    ProfileScreenContent(
                        ProfileDataWidget = { ProfileDataWidget(
                            gamerID =profileUiState.gamerID,
                            gamerTag =profileUiState.gamerTag,
                            userStatus =profileUiState.status 
                        ) },
                        ProfileScreenBioWidget = { ProfileScreenBioWidget(gamerBio = profileUiState.bio)},
                        ProfileRanksWidget = { ProfileRanksWidget(onUpdateRanksClick = { navController.navigate(PartyFinderScreen.UpdateRanksScreen.name) })}) {
                        
                    }
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
                    navController.navigate(PartyFinderScreen.ProfileScreen.name)
                }
            )
        }

        composable(route=PartyFinderScreen.UpdateRanksScreen.name){
            UpdateRanksScreen(navigateBack = {navigateBack(navController)})
        }
        composable(route=PartyFinderScreen.FindPartyScreen.name){
            FindPartyScreen()
        }


    }
}