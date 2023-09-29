package com.scccrt.rekto.ui.feature.movie.detail

import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.ui.base.UiEvent
import com.scccrt.rekto.ui.base.UiSideEffect
import com.scccrt.rekto.ui.base.UiState

class MovieDetailContract {

    sealed class Event : UiEvent {
        data object OnNavigateBack : Event()

        // TODO
        //data object ToggleFavorite : Event()
    }

    data class State(val movie: Movie?) : UiState

    sealed class Effect : UiSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }
}