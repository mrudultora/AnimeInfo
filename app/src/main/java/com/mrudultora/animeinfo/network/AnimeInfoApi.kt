package com.mrudultora.animeinfo.network

import com.mrudultora.animeinfo.models.AnimeCharactersResponse
import com.mrudultora.animeinfo.models.AnimeDetailsResponse
import com.mrudultora.animeinfo.models.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeInfoApi {
    @GET("top/anime")
    suspend fun getTopAnime(): TopAnimeResponse

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: Int
    ): AnimeDetailsResponse

    @GET("anime/{anime_id}/characters")
    suspend fun getAnimeCharacters(
        @Path("anime_id") animeId: Int
    ): AnimeCharactersResponse
}