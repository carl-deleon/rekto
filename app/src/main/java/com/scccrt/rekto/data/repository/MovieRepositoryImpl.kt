package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.dto.response.MovieResponse
import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.local.entities.SearchQuery
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.toMovie
import com.scccrt.rekto.data.remote.RektoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: RektoApi,
    private val searchHistoryDao: SearchHistoryDao,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun search(term: String, count: Int): Result<List<Movie>> {
        val result = safeApiCall {
            api.searchMovies(term, limit = count.toString())
                .results
                .map(MovieResponse::toMovie)
        }

        if (result.isSuccess) {
            searchHistoryDao.insert(SearchQuery(query = term))
        }

        return result
    }

    private suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}