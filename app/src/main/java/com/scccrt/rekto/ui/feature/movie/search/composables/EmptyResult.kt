package com.scccrt.rekto.ui.feature.movie.search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.scccrt.rekto.R

@Composable
fun EmptyResult(
    searchQuery: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val displayText = stringResource(id = R.string.no_search_result, searchQuery)
        val start = displayText.indexOf(searchQuery)
        val spanStyles = listOf(
            AnnotatedString.Range(
                SpanStyle(fontWeight = FontWeight.Bold),
                start = start,
                end = start + searchQuery.length
            )
        )

        Text(
            text = AnnotatedString(text = displayText, spanStyles = spanStyles),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyResultPreview() {
    EmptyResult(searchQuery = "Hotdog", modifier = Modifier.fillMaxSize())
}