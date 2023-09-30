package com.scccrt.rekto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scccrt.rekto.data.local.entities.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorites ORDER BY dateAdded DESC")
    fun getFavoriteMovies(): Flow<MutableList<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(favoriteMovie: FavoriteMovie)

    @Query("DELETE FROM favorites WHERE trackId=:id")
    fun removeFromFavorites(id: String)
}