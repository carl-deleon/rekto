package com.scccrt.rekto.ui.feature.movie.search.history

import com.scccrt.rekto.data.local.entities.SearchQuery
import com.scccrt.rekto.ui.base.UiEvent
import com.scccrt.rekto.ui.base.UiSideEffect
import com.scccrt.rekto.ui.base.UiState

class SearchHistoryContract {

    sealed class Event : UiEvent {
        data class OnSelect(val query: String) : Event()
        data class OnRemove(val searchQuery: SearchQuery) : Event()
    }

    data class State(
        val searchHistory: List<SearchQuery>,
    ) : UiState

    sealed class Effect : UiSideEffect {
        sealed class Action : Effect() {
            data class Search(val query: String) : Action()
        }
    }
}