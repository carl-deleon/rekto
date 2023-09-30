package com.scccrt.rekto.ui.feature.movie.favorite

import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.ui.base.UiEvent
import com.scccrt.rekto.ui.base.UiSideEffect
import com.scccrt.rekto.ui.base.UiState

class FavoriteMovieContract {

    sealed class Event : UiEvent {
        data object OnNavigateBack : Event()
        data class OnSelect(val trackId: String) : Event()
        data class RemoveFavorite(val movie: FavoriteMovie) : Event()
    }

    data class State(
        val movies: List<FavoriteMovie>
    ) : UiState

    sealed class Effect : UiSideEffect {
        sealed class Navigation : Effect() {
            data class ToDetail(val id: String) : Navigation()
            data object Back : Navigation()
        }
    }
}