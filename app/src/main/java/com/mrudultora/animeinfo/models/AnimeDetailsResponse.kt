package com.mrudultora.animeinfo.models

import com.google.gson.annotations.SerializedName

data class AnimeDetailsResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    @SerializedName("mal_id")
    val id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val trailer: Trailer?,
    val genres: List<Genre>,
    val images: Images
) {
    data class Images(
        val jpg: Jpg
    ) {
        data class Jpg(
            @SerializedName("large_image_url")
            val imageUrl: String?
        )
    }

    data class Trailer(
        @SerializedName("embed_url")
        val embedUrl: String?
    )

    data class Genre(
        val name: String
    )
}