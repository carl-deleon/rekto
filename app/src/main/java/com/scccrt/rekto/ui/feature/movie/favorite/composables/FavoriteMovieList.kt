package com.scccrt.rekto.ui.feature.movie.favorite.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.scccrt.rekto.ui.base.SIDE_EFFECTS_KEY
import com.scccrt.rekto.ui.feature.movie.favorite.FavoriteMovieContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun FavoriteMovieScreen(
    state: FavoriteMovieContract.State,
    effectFlow: Flow<FavoriteMovieContract.Effect>?,
    onEventSent: (event: FavoriteMovieContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: FavoriteMovieContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is FavoriteMovieContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorites",
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEventSent(FavoriteMovieContract.Event.OnNavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            FavoriteMovieList(state = state, onEventSent = onEventSent)
        }
    }
}

@Composable
fun FavoriteMovieList(
    state: FavoriteMovieContract.State,
    onEventSent: (event: FavoriteMovieContract.Event) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.movies) { movie ->
                FavoriteMovieItem(
                    favoriteMovie = movie,
                    onItemClicked = {
                        onEventSent(FavoriteMovieContract.Event.OnSelect(movie.trackId))
                    },
                    onRemoveFavorite = {
                        onEventSent(FavoriteMovieContract.Event.RemoveFavorite(movie))
                    }
                )
            }
        }
    }
}