package com.scccrt.rekto.di

import android.content.Context
import androidx.room.Room
import com.scccrt.rekto.data.local.RektoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRektoDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RektoDatabase::class.java, "com.scccrt.rekto.db").build()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(database: RektoDatabase) = database.searchHistoryDao()

    @Singleton
    @Provides
    fun provideMovieDao(database: RektoDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideFavoritesDao(database: RektoDatabase) = database.favoritesDao()
}