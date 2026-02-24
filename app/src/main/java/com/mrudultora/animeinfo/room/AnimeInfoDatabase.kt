package com.mrudultora.animeinfo.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeInfoEntity::class], version = 1)
abstract class AnimeInfoDatabase : RoomDatabase() {
    abstract fun animeInfoDao(): AnimeInfoDao
}