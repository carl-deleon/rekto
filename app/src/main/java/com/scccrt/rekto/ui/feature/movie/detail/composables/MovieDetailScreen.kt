package com.scccrt.rekto.ui.feature.movie.detail.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.scccrt.rekto.ui.base.SIDE_EFFECTS_KEY
import com.scccrt.rekto.ui.feature.movie.detail.MovieDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieDetailScreen(
    state: MovieDetailContract.State,
    effectFlow: Flow<MovieDetailContract.Effect>?,
    onEventSent: (event: MovieDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MovieDetailContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MovieDetailContract.Effect.Navigation.Back -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieDetailTopAppBar(title = state.movie?.trackName ?: "") {
                onEventSent(MovieDetailContract.Event.OnNavigateBack)
            }
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            if (state.movie != null) {
                MovieDetail(
                    movie = state.movie,
                    onFavoriteClicked = {
                        onEventSent(MovieDetailContract.Event.AddFavorite(it))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {

}