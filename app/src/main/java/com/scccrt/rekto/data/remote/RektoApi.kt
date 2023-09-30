package com.scccrt.rekto.data.remote

import com.scccrt.rekto.data.dto.response.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RektoApi {

    @GET("search")
    suspend fun searchMovies(
        @Query("term") searchTerm: String,
        @Query("country") country: String = DEFAULT_COUNTRY_CODE,
        @Query("media") media: String = DEFAULT_MEDIA
    ): MovieResult

    companion object {
        const val DEFAULT_COUNTRY_CODE = "au"
        const val DEFAULT_MEDIA = "movie"
    }
}