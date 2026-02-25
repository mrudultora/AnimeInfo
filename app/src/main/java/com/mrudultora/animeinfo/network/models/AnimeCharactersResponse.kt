package com.mrudultora.animeinfo.network.models

import com.google.gson.annotations.SerializedName

data class AnimeCharactersResponse(
    @SerializedName("data")
    val data: List<CharacterData>
)

data class CharacterData(
    val role: String,
    val character: Character
)

data class Character(
    val name: String,
    val images: Images
) {
    data class Images(
        val jpg: Jpg
    )

    data class Jpg(
        @SerializedName("image_url")
        val imageUrl: String
    )

}