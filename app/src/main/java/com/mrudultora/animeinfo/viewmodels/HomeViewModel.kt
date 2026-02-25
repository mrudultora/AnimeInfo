package com.mrudultora.animeinfo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mrudultora.animeinfo.navigation.AnimeDetailNavigationAction
import com.mrudultora.animeinfo.navigation.HomeNavigationAction
import com.mrudultora.animeinfo.network.models.AnimeModel
import com.mrudultora.animeinfo.room.AnimeInfoEntity
import com.mrudultora.animeinfo.usecases.GetTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeViewModel {
    val state: StateFlow<HomeState>
    val navigationAction: Flow<HomeNavigationAction>

    fun onAnimeCardClick(animeId: Int, animeTitle: String)
}

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase,
) : HomeViewModel, ViewModel() {
    override val state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    override val navigationAction: MutableSharedFlow<HomeNavigationAction> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            state.update {
                it.copy(
                    topAnimesFlow = getTopAnimeUseCase().cachedIn(viewModelScope)
                )
            }
        }
    }

    override fun onAnimeCardClick(animeId: Int, animeTitle: String) {
        viewModelScope.launch {
            navigationAction.emit(
                HomeNavigationAction.NavigateToAnimeDetail(animeId = animeId, animeTitle = animeTitle)
            )
        }
    }
}

data class HomeState(
    val topAnimesFlow: Flow<PagingData<AnimeInfoEntity>> = flowOf()
)