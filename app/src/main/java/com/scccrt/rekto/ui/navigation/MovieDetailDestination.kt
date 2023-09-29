package com.scccrt.rekto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.scccrt.rekto.ui.feature.movie.detail.MovieDetailContract
import com.scccrt.rekto.ui.feature.movie.detail.MovieDetailViewModel
import com.scccrt.rekto.ui.feature.movie.detail.composables.MovieDetailScreen

@Composable
fun MovieDetailDestination(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    MovieDetailScreen(
        state = viewModel.uiState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MovieDetailContract.Effect.Navigation.Back) {
                navController.popBackStack(route = Navigation.Routes.SEARCH, inclusive = false)
            }
        }
    )
}