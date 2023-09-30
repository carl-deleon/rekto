package com.scccrt.rekto.ui.feature.movie.search.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.moviePreview

@Composable
fun SearchResultList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    onItemClick: (Movie) -> Unit,
    onFavoriteMovie: (Movie, Boolean) -> Unit
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(movies) { movie ->
                MovieResultItem(
                    movie = movie,
                    onItemClick = onItemClick,
                    onFavoriteMovie = onFavoriteMovie
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultPreview() {
    val movies = List(3) { moviePreview() }
    SearchResultList(movies = movies, onItemClick = {}, onFavoriteMovie = { _, _ -> })
}