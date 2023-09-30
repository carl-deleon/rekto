package com.scccrt.rekto.ui.feature.movie.favorite.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.scccrt.rekto.R
import com.scccrt.rekto.data.local.entities.FavoriteMovie
import com.scccrt.rekto.ui.feature.common.MovieBoxImage

@Composable
fun FavoriteMovieItem(
    favoriteMovie: FavoriteMovie,
    onItemClicked: (FavoriteMovie) -> Unit,
    onRemoveFavorite: (FavoriteMovie) -> Unit
) {

    val paddingXXsmall = dimensionResource(id = R.dimen.padding_xxsmall)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val thumbnailSize = dimensionResource(id = R.dimen.artwork_large)

    Column(
        modifier = Modifier
            .clickable {
                onItemClicked(favoriteMovie)
            }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            MovieBoxImage(
                url = favoriteMovie.artworkLarge,
                modifier = Modifier
                    .size(thumbnailSize)
                    .padding(end = paddingMedium)
            )
            Column {
                Text(
                    text = favoriteMovie.trackName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(paddingXXsmall))

                Text(
                    text = favoriteMovie.shortDescription ?: favoriteMovie.longDescription ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = paddingMedium, end = paddingMedium)
        )
    }
}