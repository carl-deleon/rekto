package com.scccrt.rekto.ui.feature.movie.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.toFavoriteMovie
import com.scccrt.rekto.data.repository.MovieRepository
import com.scccrt.rekto.ui.base.BaseViewModel
import com.scccrt.rekto.ui.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) : BaseViewModel<MovieDetailContract.Event, MovieDetailContract.State, MovieDetailContract.Effect>() {

    private val trackId: String =
        savedStateHandle.get<String>(Navigation.Args.TRACK_ID) ?: "[TRACK_ID] argument is required."

    init {
        viewModelScope.launch {
            movieRepository
                .getFavoriteMovies()
                .collect { favoriteMovies ->
                    setState {
                        copy(
                            favoriteMovies = favoriteMovies,
                            movie = movie?.copy(isFavorite = favoriteMovies.find { it.trackId == movie.trackId } != null)
                        )
                    }
                }
        }

        getMovie()
    }

    override fun setInitialState() = MovieDetailContract.State(movie = null, favoriteMovies = emptyList())

    override fun handleEvents(event: MovieDetailContract.Event) {
        when (event) {
            MovieDetailContract.Event.OnNavigateBack -> {
                setEffect { MovieDetailContract.Effect.Navigation.Back }
            }

            is MovieDetailContract.Event.ToggleFavorite -> toggleFavoriteMovie(event.movie, event.checked)
        }
    }

    private fun getMovie() {
        viewModelScope.launch {
            val movie = movieRepository.getMovie(trackId)
            setState {
                copy(
                    movie = movie.copy(isFavorite = favoriteMovies.find { it.trackId == movie.trackId } != null)
                )
            }
        }
    }

    private fun toggleFavoriteMovie(movie: Movie, checked: Boolean) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movie.toFavoriteMovie(), checked)
        }
    }
}