package com.example.partyfinder.ui.theme

import androidx.compose.runtime.Composable
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
fun PartyFinderApp(){


    val navController : NavHostController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PartyFinderScreen.HomeScreen.name){
        composable(route=PartyFinderScreen.HomeScreen.name){
            HomeScreen(
                navigateToProfileScreen={navController.navigate(PartyFinderScreen.ProfileScreen.name)},
                navigateToChats = {},
                navigateToPartyFinder = {navController.navigate(PartyFinderScreen.FindPartyScreen.name)},
                navigateToGamerCalls = {},
                navigateToCommunities = {}

            )
        }

        composable(route=PartyFinderScreen.ProfileScreen.name){
            ProfileScreen(
                onEditProfileClick = { navController.navigate(PartyFinderScreen.EditProfileScreen.name)},
                onUpdateRanksClick = { navController.navigate(PartyFinderScreen.UpdateRanksScreen.name)}
            )
        }

        composable(route=PartyFinderScreen.EditProfileScreen.name){
            EditProfileScreen(navigateBack = {navigateBack(navController)})
        }

        composable(route=PartyFinderScreen.UpdateRanksScreen.name){
            UpdateRanksScreen(navigateBack = {navigateBack(navController)})
        }
        composable(route=PartyFinderScreen.FindPartyScreen.name){
            FindPartyScreen()
        }
    }
}