package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.dto.response.MovieResponse
import com.scccrt.rekto.data.local.dao.FavoriteMovieDao
import com.scccrt.rekto.data.local.dao.MovieDao
import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.local.entities.SearchQuery
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.toEntity
import com.scccrt.rekto.data.model.toMovie
import com.scccrt.rekto.data.remote.RektoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: RektoApi,
    private val searchHistoryDao: SearchHistoryDao,
    private val movieDao: MovieDao,
    private val favoriteMovieDao: FavoriteMovieDao,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun search(term: String): Result<List<Movie>> {
        val result = safeApiCall {
            api.searchMovies(term)
                .results
                .map(MovieResponse::toMovie)
        }

        if (result.isSuccess) {
            val moviesResult = result.getOrDefault(emptyList())

            if (moviesResult.isNotEmpty()) {
                movieDao.deleteAll()
                movieDao.insertAll(moviesResult.map { it.toEntity() })
            }
        }

        return result
    }

    override suspend fun getMovie(trackId: String): Movie {
        return withContext(dispatcher) {
            movieDao.getMovie(trackId).toMovie()
        }
    }

    override suspend fun saveSearchQuery(term: String) {
        searchHistoryDao.insert(SearchQuery(query = term))
    }

    override fun getFavoriteMovies(): Flow<MutableList<FavoriteMovie>> {
        return favoriteMovieDao.getFavoriteMovies()
    }

    override suspend fun toggleFavorite(movie: FavoriteMovie, checked: Boolean) {
        withContext(dispatcher) {
            if (checked) favoriteMovieDao.addToFavorites(movie) else favoriteMovieDao.removeFromFavorites(movie.trackId)
        }
    }

    private suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}