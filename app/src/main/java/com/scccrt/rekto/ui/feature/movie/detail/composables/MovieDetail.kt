package com.scccrt.rekto.ui.feature.movie.detail.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scccrt.rekto.R
import com.scccrt.rekto.data.model.Movie
import com.scccrt.rekto.data.model.moviePreview
import com.scccrt.rekto.ui.feature.common.FavoriteButton
import com.scccrt.rekto.ui.feature.common.MovieBoxImage

@Composable
fun MovieDetail(
    movie: Movie,
    onFavoriteClicked: (Movie, Boolean) -> Unit
) {

    val mediumPadding = dimensionResource(id = R.dimen.padding_medium)
    val largePadding = dimensionResource(id = R.dimen.padding_large)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(largePadding),
                verticalAlignment = Alignment.Bottom
            ) {
                MovieBoxImage(
                    url = movie.artwork.largeArtworkUrl?.fakeEnlargedImageUrl(),
                    modifier = Modifier.height(200.dp),
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.size(largePadding))

                Row {
                    FilledTonalButton(onClick = {
                        // noop
                    }) {
                        Text(text = stringResource(id = R.string.buy_for, movie.price.displayPrice))
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    FavoriteButton(isChecked = movie.isFavorite) { checked ->
                        onFavoriteClicked(movie, checked)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(largePadding),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = movie.displayTitle,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = movie.primaryGenre,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(mediumPadding)
                ) {
                    Text(
                        text = "About this movie",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.size(mediumPadding))

                    Text(
                        text = movie.description.long ?: movie.description.short ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

fun String.fakeEnlargedImageUrl() = "${split("/").dropLast(1).joinToString(separator = "/")}/200x300.jpg"

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieDetailPreview() {
    MovieDetail(movie = moviePreview(), onFavoriteClicked = { _, _ -> })
}