package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.dto.response.MovieResponse
import com.scccrt.rekto.data.model.toMovie
import com.scccrt.rekto.data.remote.RektoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: RektoApi,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun search(term: String, count: Int) = safeApiCall {
        api.searchMovies(term, limit = count.toString())
            .results
            .map(MovieResponse::toMovie)
    }

    private suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}