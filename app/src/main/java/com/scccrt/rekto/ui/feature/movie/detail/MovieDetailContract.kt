package com.scccrt.rekto.ui.feature.movie.detail

import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.ui.base.UiEvent
import com.scccrt.rekto.ui.base.UiSideEffect
import com.scccrt.rekto.ui.base.UiState

class MovieDetailContract {

    sealed class Event : UiEvent {
        data object OnNavigateBack : Event()

        data class ToggleFavorite(val movie: Movie, val checked: Boolean) : Event()
    }

    data class State(
        val favoriteMovies: List<FavoriteMovie>,
        val movie: Movie?
    ) : UiState

    sealed class Effect : UiSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}