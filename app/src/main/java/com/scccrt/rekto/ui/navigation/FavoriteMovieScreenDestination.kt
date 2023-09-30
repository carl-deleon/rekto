package com.scccrt.rekto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.scccrt.rekto.ui.feature.movie.favorite.FavoriteMovieContract
import com.scccrt.rekto.ui.feature.movie.favorite.FavoriteMovieViewModel
import com.scccrt.rekto.ui.feature.movie.favorite.composables.FavoriteMovieScreen

@Composable
fun FavoriteMovieScreenDestination(
    navController: NavController,
    viewModel: FavoriteMovieViewModel = hiltViewModel()
) {
    FavoriteMovieScreen(
        state = viewModel.uiState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                FavoriteMovieContract.Effect.Navigation.Back -> navController.popBackStack()
                is FavoriteMovieContract.Effect.Navigation.ToDetail -> navController.navigateToDetail(navigationEffect.id)
            }
        }
    )
}