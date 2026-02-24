package com.mrudultora.animeinfo.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mrudultora.animeinfo.room.AnimeInfoDao
import com.mrudultora.animeinfo.room.AnimeInfoEntity

class AnimePagingSource(
    private val api: AnimeInfoApi,
    private val dao: AnimeInfoDao,
) : PagingSource<Int, AnimeInfoEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeInfoEntity> {

        val currentPage = params.key ?: 1

        return try {
            val response = api.getTopAnime(currentPage)

            val entities = response.data.map {
                AnimeInfoEntity(
                    id = it.id,
                    title = it.title,
                    episodes = it.episodes,
                    score = it.score,
                    imageUrl = it.images.jpg.imageUrl,
                    page = currentPage,
                )
            }

            // cache in room
            dao.insertAll(entities)

            LoadResult.Page(
                data = entities,
                prevKey = null,
                nextKey = if (response.pagination.hasNextPage) currentPage + 1 else null
            )

        } catch (e: Exception) {
            // If network fails → load from Room
            val cachedPage = dao.getAnimeByPage(currentPage)

            if (cachedPage.isNotEmpty()) {
                LoadResult.Page(
                    data = cachedPage,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = currentPage + 1
                )
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimeInfoEntity>): Int? {
        return state.anchorPosition
    }
}