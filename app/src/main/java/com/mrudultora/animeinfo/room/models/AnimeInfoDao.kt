package com.mrudultora.animeinfo.room.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeInfoDao {

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AnimeInfoEntity>)
}