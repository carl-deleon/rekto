package com.scccrt.rekto.data.repository

import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.local.entities.SearchQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchHistoryRepositoryImpl(
    private val searchHistoryDao: SearchHistoryDao,
    private val dispatcher: CoroutineDispatcher
) : SearchHistoryRepository {

    override val searchHistory: Flow<MutableList<SearchQuery>>
        get() = searchHistoryDao.getSearchHistory()

    override suspend fun delete(searchQuery: SearchQuery) {
        withContext(dispatcher) {
            searchHistoryDao.delete(searchQuery)
        }
    }
}