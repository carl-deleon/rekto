package com.scccrt.rekto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.scccrt.rekto.ui.navigation.Navigation.Args.TRACK_ID

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.SEARCH
    ) {
        composable(
            route = Navigation.Routes.SEARCH
        ) {
            SearchScreenDestination(navController)
        }

        composable(
            route = Navigation.Routes.MOVIE_DETAIL,
            arguments = listOf(navArgument(name = TRACK_ID) {
                type = NavType.StringType
            })
        ) {
            MovieDetailDestination(navController)
        }

        composable(
            route = Navigation.Routes.FAVORITE
        ) {
            FavoriteMovieScreenDestination(navController)
        }
    }
}

object Navigation {

    object Args {
        const val TRACK_ID = "track_id"
    }

    object Routes {
        const val SEARCH = "search"
        const val MOVIE_DETAIL = "$SEARCH/{$TRACK_ID}"
        const val FAVORITE = "favorite"
    }
}

fun NavController.navigateToDetail(trackId: String) {
    navigate(route = "${Navigation.Routes.SEARCH}/$trackId")
}

fun NavController.navigateToFavorites() {
    navigate(route = Navigation.Routes.FAVORITE)
}