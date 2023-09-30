package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun search(term: String, count: Int = 15): Result<List<Movie>>

    suspend fun getMovie(trackId: String): Movie

    suspend fun saveSearchQuery(term: String)

    fun getFavoriteMovies(): Flow<MutableList<FavoriteMovie>>

    suspend fun addFavorite(movie: FavoriteMovie)

    suspend fun removeFavorite(movie: FavoriteMovie)
}