package com.mrudultora.animeinfo.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mrudultora.animeinfo.ui.screens.HomeScreen
import com.mrudultora.animeinfo.viewmodels.HomeViewModel
import com.mrudultora.animeinfo.viewmodels.HomeViewModelImpl

fun NavGraphBuilder.homeNavigation(navHostController: NavHostController) {
    composable<HomeRoute> {
        val homeViewModel: HomeViewModel = hiltViewModel<HomeViewModelImpl>()
        val navigator = remember { HomeNavigator(navHostController) }
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.navigationAction.collect { action ->
                    when (action) {
                        is HomeNavigationAction.NavigateToAnimeDetail -> {
                            navigator.navigateToAnimeDetail(animeId = action.animeId, animeTitle = action.animeTitle)
                        }
                    }
                }
            }
        }
        HomeScreen(viewModel = homeViewModel)
    }
}

sealed interface HomeNavigationAction {
    data class NavigateToAnimeDetail(val animeId: Int, val animeTitle: String) : HomeNavigationAction
}

class HomeNavigator(private val navController: NavController) {
    fun navigateToAnimeDetail(animeId: Int, animeTitle: String) {
        navController.navigate(AnimeDetailRoute(animeId = animeId, animeTitle = animeTitle))
    }
}