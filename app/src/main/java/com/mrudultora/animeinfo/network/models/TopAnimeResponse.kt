package com.mrudultora.animeinfo.network.models

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    val data: List<AnimeModel>,
    val pagination: Pagination
) {
    data class Pagination(
        @SerializedName("last_visible_page")
        val lastVisiblePage: Int,

        @SerializedName("has_next_page")
        val hasNextPage: Boolean,

        @SerializedName("current_page")
        val currentPage: Int
    )
}

data class AnimeModel(
    @SerializedName("mal_id")
    val id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Images
) {
    data class Images(
        val jpg: Jpg
    ) {
        data class Jpg(
            @SerializedName("image_url")
            val imageUrl: String
        )
    }
}