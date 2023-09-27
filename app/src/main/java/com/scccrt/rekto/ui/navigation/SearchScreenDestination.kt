package com.scccrt.rekto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.scccrt.rekto.ui.feature.movie.search.MovieSearchContract
import com.scccrt.rekto.ui.feature.movie.search.MovieSearchViewModel
import com.scccrt.rekto.ui.feature.movie.search.composables.MovieSearchScreen

@Composable
fun SearchScreenDestination(
    navController: NavController,
    viewModel: MovieSearchViewModel = hiltViewModel()
) {
    MovieSearchScreen(
        state = viewModel.uiState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MovieSearchContract.Effect.Navigation.ToDetail) {
                navController.navigateToDetail(navigationEffect.movie.trackId)
            }
        }
    )
}