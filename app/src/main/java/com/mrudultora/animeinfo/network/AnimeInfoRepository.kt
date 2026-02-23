package com.mrudultora.animeinfo.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mrudultora.animeinfo.room.models.AnimeInfoDao
import com.mrudultora.animeinfo.room.models.AnimeInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeInfoRepository @Inject constructor(
    private val animeInfoApi: AnimeInfoApi,
    private val animeInfoDao: AnimeInfoDao,
) {

    fun getTopAnime(): Flow<PagingData<AnimeInfoEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                AnimePagingSource(api = animeInfoApi, dao = animeInfoDao)
            }
        ).flow
    }

    suspend fun getAnimeDetails(id: Int) =
        animeInfoApi.getAnimeDetails(id)

    suspend fun getAnimeCharacters(id: Int) =
        animeInfoApi.getAnimeCharacters(id)
}

private const val PAGE_SIZE = 25