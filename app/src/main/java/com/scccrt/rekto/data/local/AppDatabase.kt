package com.scccrt.rekto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scccrt.rekto.data.local.dao.FavoriteMovieDao
import com.scccrt.rekto.data.local.dao.MovieDao
import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.local.entities.MovieEntity
import com.scccrt.rekto.data.local.entities.SearchQuery

@Database(
    entities = [SearchQuery::class, MovieEntity::class, FavoriteMovie::class],
    version = 1,
    exportSchema = false
)
abstract class RektoDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun movieDao(): MovieDao

    abstract fun favoritesDao(): FavoriteMovieDao
}