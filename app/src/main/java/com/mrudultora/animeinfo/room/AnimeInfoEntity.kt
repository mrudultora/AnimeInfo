package com.mrudultora.animeinfo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeInfoEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val page: Int,
)