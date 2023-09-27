package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.toMovie
import com.scccrt.rekto.data.remote.RektoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: RektoApi,
    private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun search(term: String): Result<List<Movie>> = safeApiCall(dispatcher) {
        api.searchMovies(term).results.map { it.toMovie() }
    }

    private suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        call: suspend () -> T
    ): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}