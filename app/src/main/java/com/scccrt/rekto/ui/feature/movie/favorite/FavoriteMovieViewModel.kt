package com.scccrt.rekto.ui.feature.movie.favorite

import androidx.lifecycle.viewModelScope
import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.data.repository.MovieRepository
import com.scccrt.rekto.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<FavoriteMovieContract.Event, FavoriteMovieContract.State, FavoriteMovieContract.Effect>() {

    init {
        viewModelScope.launch {
            movieRepository.getFavoriteMovies()
                .collect { favoriteMovies ->
                    setState { copy(movies = favoriteMovies) }
                }
        }
    }

    override fun setInitialState() = FavoriteMovieContract.State(movies = emptyList())

    override fun handleEvents(event: FavoriteMovieContract.Event) {
        when (event) {
            is FavoriteMovieContract.Event.RemoveFavorite -> removeFromFavorites(event.movie)
            is FavoriteMovieContract.Event.OnSelect -> setEffect {
                FavoriteMovieContract.Effect.Navigation.ToDetail(
                    event.trackId
                )
            }

            FavoriteMovieContract.Event.OnNavigateBack -> setEffect {
                FavoriteMovieContract.Effect.Navigation.Back
            }
        }
    }

    private fun removeFromFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            movieRepository.removeFavorite(movie)
        }
    }
}