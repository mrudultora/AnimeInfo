package com.mrudultora.animeinfo.network

import javax.inject.Inject

class AnimeInfoRepository @Inject constructor(
    private val animeInfoApi: AnimeInfoApi
) {

    suspend fun getTopAnime() = animeInfoApi.getTopAnime()

    suspend fun getAnimeDetails(id: Int) =
        animeInfoApi.getAnimeDetails(id)

    suspend fun getAnimeCharacters(id: Int) =
        animeInfoApi.getAnimeCharacters(id)
}