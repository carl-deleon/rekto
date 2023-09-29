package com.scccrt.rekto.ui.feature.movie.search.history

import androidx.lifecycle.viewModelScope
import com.scccrt.rekto.data.local.entities.SearchQuery
import com.scccrt.rekto.data.repository.SearchHistoryRepository
import com.scccrt.rekto.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : BaseViewModel<SearchHistoryContract.Event, SearchHistoryContract.State, SearchHistoryContract.Effect>() {

    init {
        viewModelScope.launch {
            searchHistoryRepository.searchHistory
                .collect { searchHistory ->
                    setState { copy(searchHistory = searchHistory.toList()) }
                }
        }
    }

    override fun setInitialState() = SearchHistoryContract.State(emptyList())

    override fun handleEvents(event: SearchHistoryContract.Event) {
        when (event) {
            is SearchHistoryContract.Event.OnSelect -> {
                setEffect { SearchHistoryContract.Effect.Action.Search(event.query) }
            }

            is SearchHistoryContract.Event.OnRemove -> removeSearchItem(event.searchQuery)
        }
    }

    private fun removeSearchItem(searchQuery: SearchQuery) {
        viewModelScope.launch {
            searchHistoryRepository.delete(searchQuery)
        }
    }
}