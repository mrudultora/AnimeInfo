package com.mrudultora.animeinfo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mrudultora.animeinfo.ui.screens.AnimeDetailScreen

fun NavGraphBuilder.animeDetailNavigation(navHostController: NavHostController) {
    composable<AnimeDetailRoute> {
        AnimeDetailScreen()
    }
}