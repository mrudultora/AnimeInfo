package com.mrudultora.animeinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AnimeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        homeNavigation(navController)
        animeDetailNavigation(navController)
    }
}