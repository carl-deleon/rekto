package com.scccrt.rekto.ui.feature.movie.search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.scccrt.rekto.R

@Composable
fun InitialSearchContent(
    modifier: Modifier = Modifier,
    onRecommendationClicked: (String) -> Unit
) {
    val movies = listOf(
        "The Godfather",
        "Star Wars",
        "Batman",
        "Alien",
        "Tokyo",
        "Pulp Fiction",
        "Joker",
        "Spider-Man",
        "Toy Story",
        "Terminator"
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val randomMovie = movies.random()

        Text(
            text = stringResource(id = R.string.search_suggestion),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            textAlign = TextAlign.Center
        )

        TextButton(
            onClick = { onRecommendationClicked(randomMovie) }
        ) {
            Text(text = randomMovie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitialSearchContentPreview() {
    InitialSearchContent(modifier = Modifier.fillMaxSize(), onRecommendationClicked = {})
}