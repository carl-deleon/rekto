package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.model.Movie

interface MovieRepository {

    suspend fun search(term: String, count: Int = 15): Result<List<Movie>>
}