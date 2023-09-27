package com.scccrt.rekto.ui.feature.movie.search

import androidx.lifecycle.viewModelScope
import com.scccrt.rekto.data.repository.MovieRepository
import com.scccrt.rekto.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel<MovieSearchContract.Event, MovieSearchContract.State, MovieSearchContract.Effect>() {

    override fun setInitialState() = MovieSearchContract.State(
        movies = emptyList(),
        isLoading = false,
        isError = false,
        lastSearchQuery = ""
    )

    override fun handleEvents(event: MovieSearchContract.Event) {
        when (event) {
            MovieSearchContract.Event.Refresh -> {
                with(uiState.value.lastSearchQuery) {
                    if (isNotEmpty() && isNotBlank()) {
                        search(uiState.value.lastSearchQuery)
                    }
                }
            }

            is MovieSearchContract.Event.MovieSelection -> {
                setEffect { MovieSearchContract.Effect.Navigation.ToDetail(event.movie) }
            }

            is MovieSearchContract.Event.Search -> search(event.term)
        }
    }

    fun search(term: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }

            movieRepository.search(term)
                .onSuccess { result ->
                    setState {
                        copy(
                            movies = result,
                            isLoading = false,
                            isError = false,
                            lastSearchQuery = term
                        )
                    }
                }
                .onFailure {
                    Timber.e(it)
                    setState {
                        copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
        }
    }
}