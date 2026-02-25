package com.mrudultora.animeinfo.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mrudultora.animeinfo.navigation.AnimeDetailNavigationAction
import com.mrudultora.animeinfo.navigation.AnimeDetailRoute
import com.mrudultora.animeinfo.network.models.AnimeInfo
import com.mrudultora.animeinfo.usecases.GetAnimeDetailsWithCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mrudultora.animeinfo.network.Result

interface AnimeDetailViewModel {
    val state: StateFlow<AnimeDetailState>
    val navigationAction: Flow<AnimeDetailNavigationAction>
    fun onUpClick()
}

@HiltViewModel
class AnimeDetailViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeDetailsWithCharactersUseCase: GetAnimeDetailsWithCharactersUseCase,
) : AnimeDetailViewModel, ViewModel() {
    private val animeDetailRoute: AnimeDetailRoute = savedStateHandle.toRoute()

    override val state: MutableStateFlow<AnimeDetailState> = MutableStateFlow(AnimeDetailState())

    override val navigationAction: MutableSharedFlow<AnimeDetailNavigationAction> = MutableSharedFlow()

    init {
        state.update {
            it.copy(
                animeId = animeDetailRoute.animeId,
                animeTitle = animeDetailRoute.animeTitle
            )
        }
        viewModelScope.launch {
            state.update {
                it.copy(
                    animeInfoLoadingState = getAnimeDetailsWithCharactersUseCase(animeDetailRoute.animeId)
                )
            }
        }
    }

    override fun onUpClick() {
        viewModelScope.launch {
            navigationAction.emit(AnimeDetailNavigationAction.NavigateUp)
        }
    }
}

data class AnimeDetailState(
    val animeId: Int? = null,
    val animeTitle: String = "",
    val animeInfoLoadingState: Result<AnimeInfo> = Result.Loading,
)