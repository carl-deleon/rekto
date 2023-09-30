package com.scccrt.rekto.ui.feature.movie.search.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scccrt.rekto.R
import com.scccrt.rekto.data.model.moviePreview
import com.scccrt.rekto.ui.base.SIDE_EFFECTS_KEY
import com.scccrt.rekto.ui.feature.common.NetworkError
import com.scccrt.rekto.ui.feature.common.Progress
import com.scccrt.rekto.ui.feature.movie.search.MovieSearchContract
import com.scccrt.rekto.ui.feature.movie.search.history.composables.SearchHistoryScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieSearchScreen(
    state: MovieSearchContract.State,
    effectFlow: Flow<MovieSearchContract.Effect>?,
    onEventSent: (event: MovieSearchContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MovieSearchContract.Effect.Navigation) -> Unit
) {
    var searchQuery by remember { mutableStateOf(state.lastSearchQuery) }
    var activeState by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MovieSearchContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            SearchTopAppBar(
                onFavoriteClicked = {
                    onEventSent(MovieSearchContract.Event.NavigateToFavorite)
                }
            )
        }
    ) { innerPadding ->

        val noPadding = 0.dp
        val smallPadding = dimensionResource(id = R.dimen.padding_small)
        val mediumPadding = dimensionResource(id = R.dimen.padding_medium)

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                        onEventSent(MovieSearchContract.Event.Search(it))
                    },
                    onSearch = {
                        activeState = false
                        onEventSent(MovieSearchContract.Event.SaveQuery(it))
                    },
                    active = activeState,
                    onActiveChange = { activeState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = if (activeState) noPadding else mediumPadding,
                            vertical = if (activeState) noPadding else smallPadding
                        ),
                    placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) }
                ) {
                    SearchHistoryScreen(
                        onItemClicked = {
                            searchQuery = it
                            activeState = false

                            onEventSent(MovieSearchContract.Event.Search(it))
                        }
                    )
                }

                when {
                    state.isInitial -> InitialSearchContent(
                        modifier = Modifier.fillMaxSize(),
                        onRecommendationClicked = {
                            onEventSent(MovieSearchContract.Event.Search(it))
                            searchQuery = it
                        }
                    )

                    state.isLoading -> Progress()
                    state.isError -> NetworkError { onEventSent(MovieSearchContract.Event.Refresh) }
                    state.isResultEmpty -> EmptyResult(
                        searchQuery = state.lastSearchQuery,
                        modifier = Modifier.fillMaxSize()
                    )

                    else -> SearchResultList(
                        movies = state.movies,
                        modifier = Modifier.fillMaxSize(),
                        onItemClick = { onEventSent(MovieSearchContract.Event.MovieSelection(it)) },
                        onFavoriteMovie = { movie, checked ->
                            onEventSent(MovieSearchContract.Event.OnFavoriteMovie(movie, checked))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieSearchScreenPreview() {
    MovieSearchScreen(
        state = previewState(),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {}
    )
}

fun previewState() = MovieSearchContract.State(
    movies = List(3) { moviePreview() },
    isLoading = false,
    isError = false,
    lastSearchQuery = "",
    favoriteMovies = emptyList()
)