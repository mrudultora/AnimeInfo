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
import com.mrudultora.animeinfo.ui.screens.AnimeDetailScreen
import com.mrudultora.animeinfo.ui.screens.HomeScreen
import com.mrudultora.animeinfo.viewmodels.AnimeDetailViewModel
import com.mrudultora.animeinfo.viewmodels.AnimeDetailViewModelImpl
import com.mrudultora.animeinfo.viewmodels.HomeViewModel
import com.mrudultora.animeinfo.viewmodels.HomeViewModelImpl

fun NavGraphBuilder.animeDetailNavigation(navHostController: NavHostController) {
    composable<AnimeDetailRoute> {
        val animeDetailViewModel: AnimeDetailViewModel = hiltViewModel<AnimeDetailViewModelImpl>()
        val navigator = remember { AnimeDetailNavigator(navHostController) }
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                animeDetailViewModel.navigationAction.collect { action ->
                    when (action) {
                        AnimeDetailNavigationAction.NavigateUp -> navigator.navigateUp()
                    }
                }
            }
        }
        AnimeDetailScreen(viewModel = animeDetailViewModel)
    }
}

sealed interface AnimeDetailNavigationAction {
    data object NavigateUp : AnimeDetailNavigationAction
}

class AnimeDetailNavigator(private val navController: NavController) {
    fun navigateUp() {
        navController.navigateUp()
    }
}