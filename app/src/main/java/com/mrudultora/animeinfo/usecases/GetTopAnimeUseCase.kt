package com.mrudultora.animeinfo.usecases

import androidx.paging.PagingData
import com.mrudultora.animeinfo.network.AnimeInfoRepository
import com.mrudultora.animeinfo.room.AnimeInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAnimeUseCase @Inject constructor(
    private val repository: AnimeInfoRepository
) {
    operator fun invoke(): Flow<PagingData<AnimeInfoEntity>> {
        return repository.getTopAnime()
    }
}