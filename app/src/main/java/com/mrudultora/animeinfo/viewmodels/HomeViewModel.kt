package com.mrudultora.animeinfo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrudultora.animeinfo.navigation.AnimeDetailNavigationAction
import com.mrudultora.animeinfo.navigation.HomeNavigationAction
import com.mrudultora.animeinfo.usecases.GetTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeViewModel {
    val navigationAction: Flow<HomeNavigationAction>

    fun onButtonClick()
}

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase,
) : HomeViewModel, ViewModel() {

    override val navigationAction: MutableSharedFlow<HomeNavigationAction>
        get() = MutableSharedFlow()

    init {
        // do something
    }

    override fun onButtonClick() {
        viewModelScope.launch {
            navigationAction.emit(HomeNavigationAction.NavigateToAnimeDetail(animeId = 786))
        }
    }
}