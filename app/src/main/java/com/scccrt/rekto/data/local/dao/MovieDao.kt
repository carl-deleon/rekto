package com.scccrt.rekto.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.scccrt.rekto.data.local.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE trackId=:trackId")
    fun getMovie(trackId: String): MovieEntity
}