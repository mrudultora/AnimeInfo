package com.mrudultora.animeinfo.network.models

data class AnimeInfo(
    val animeDetails: AnimeDetailsResponse,
    val animeCharacters: AnimeCharactersResponse,
)
