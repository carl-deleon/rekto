package com.scccrt.rekto.ui.feature.movie.search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import com.scccrt.rekto.R
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.moviePreview
import com.scccrt.rekto.ui.feature.common.MovieBoxImage

@Composable
fun MovieResultItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val paddingXXsmall = dimensionResource(id = R.dimen.padding_xxsmall)
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)
    val thumbnailSize = dimensionResource(id = R.dimen.artwork_mini)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clickable {
                onItemClick(movie)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingMedium)
        ) {
            MovieBoxImage(
                url = movie.artwork.largeArtworkUrl,
                modifier = Modifier
                    .size(thumbnailSize)
                    .padding(end = paddingMedium)
            )
            Column {
                Text(
                    text = movie.trackName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.size(paddingXXsmall))

                Text(
                    text = movie.description.short ?: movie.description.long ?: "",
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

@Preview(showBackground = true)
@Composable
fun MovieResultItemPreview() {
    MovieResultItem(movie = moviePreview(), onItemClick = {})
}