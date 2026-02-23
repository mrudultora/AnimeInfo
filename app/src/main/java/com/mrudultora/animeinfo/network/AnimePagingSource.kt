package com.mrudultora.animeinfo.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrudultora.animeinfo.room.models.AnimeInfoDao
import com.mrudultora.animeinfo.room.models.AnimeInfoEntity

class AnimePagingSource(
    private val api: AnimeInfoApi,
    private val dao: AnimeInfoDao,
) : PagingSource<Int, AnimeInfoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeInfoEntity> {

        val page = params.key ?: 1

        return try {
            val response = api.getTopAnime(page)

            val entities = response.data.map {
                AnimeInfoEntity(
                    id = it.id,
                    title = it.title,
                    episodes = it.episodes,
                    score = it.score,
                    imageUrl = it.images.jpg.imageUrl
                )
            }

            // cache in room
            dao.insertAll(entities)

            LoadResult.Page(
                data = entities,
                prevKey = null,
                nextKey = if (response.pagination.hasNextPage) page + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeInfoEntity>): Int? {
        return state.anchorPosition
    }
}