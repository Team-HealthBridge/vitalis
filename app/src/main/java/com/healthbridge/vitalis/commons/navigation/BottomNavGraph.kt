package com.healthbridge.vitalis.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_communities.presentation.CommunitiesScreen
import com.healthbridge.vitalis.feature_home.presentation.MainActivity
import com.healthbridge.vitalis.feature_records.presentation.RecordsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route)
    {
        composable(route = BottomNavItem.Home.route){
            println("Home has been clicked")
            MainActivity()
        }
        composable(route = BottomNavItem.Communities.route){
            println("Communities has been clicked")
            CommunitiesScreen()
        }

        composable(route = BottomNavItem.Records.route){
            println("Records has been clicked")
            RecordsScreen()
        }

        composable(route = "chat"){
            println("Chat has been clicked")
            ChatScreen()
        }
    }

}