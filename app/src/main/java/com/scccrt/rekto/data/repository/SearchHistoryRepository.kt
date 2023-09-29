package com.scccrt.rekto.data.repository

import androidx.annotation.WorkerThread
import com.scccrt.rekto.data.local.entities.SearchQuery
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    val searchHistory: Flow<MutableList<SearchQuery>>

    @WorkerThread
    suspend fun delete(searchQuery: SearchQuery)
}