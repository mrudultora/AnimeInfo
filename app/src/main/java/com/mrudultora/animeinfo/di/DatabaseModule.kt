package com.mrudultora.animeinfo.di

import android.content.Context
import androidx.room.Room
import com.mrudultora.animeinfo.room.AnimeInfoDao
import com.mrudultora.animeinfo.room.AnimeInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimeInfoDatabase {
        return Room.databaseBuilder(
            context,
            AnimeInfoDatabase::class.java,
            "anime_info_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeInfoDao(database: AnimeInfoDatabase): AnimeInfoDao {
        return database.animeInfoDao()
    }
}