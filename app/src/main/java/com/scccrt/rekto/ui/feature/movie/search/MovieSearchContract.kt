package com.scccrt.rekto.ui.feature.movie.search

import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.ui.base.UiEvent
import com.scccrt.rekto.ui.base.UiSideEffect
import com.scccrt.rekto.ui.base.UiState

class MovieSearchContract {

    sealed class Event : UiEvent {
        data object Refresh : Event()
        data class MovieSelection(val movie: Movie) : Event()
        data class Search(val term: String) : Event()
        data class SaveQuery(val term: String) : Event()
        data object NavigateToFavorite : Event()
        data class OnFavoriteMovie(val movie: Movie, val checked: Boolean) : Event()
    }

    data class State(
        val movies: List<Movie>,
        val isLoading: Boolean,
        val isError: Boolean,
        val lastSearchQuery: String,
        val favoriteMovies: List<FavoriteMovie>
    ) : UiState {

        val isInitial: Boolean get() = movies.isEmpty() && !isLoading && !isError && lastSearchQuery.isEmpty()
        val isResultEmpty: Boolean get() = movies.isEmpty() && lastSearchQuery.isNotEmpty()
    }

    sealed class Effect : UiSideEffect {
        sealed class Navigation : Effect() {
            data class ToDetail(val movie: Movie) : Navigation()
            data object ToFavorite : Navigation()
        }
    }
}