package com.scccrt.rekto.ui.feature.movie.search.history.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scccrt.rekto.R
import com.scccrt.rekto.data.local.entities.SearchQuery

@Composable
fun SearchHistoryItem(
    searchQuery: SearchQuery,
    onItemClick: (String) -> Unit,
    onDeleteClick: (SearchQuery) -> Unit
) {
    val paddingMedium = dimensionResource(id = R.dimen.padding_medium)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium)
            .clickable {
                onItemClick(searchQuery.query)
            }
    ) {
        Box(
            modifier = Modifier.size(24.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_history_24),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.size(paddingMedium))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = searchQuery.query)
        }

        Spacer(modifier = Modifier.size(paddingMedium))

        IconButton(onClick = { onDeleteClick(searchQuery) }) {
            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHistoryItemPreview() {
    SearchHistoryItem(
        searchQuery = SearchQuery(0, "Sample Query"),
        onItemClick = {},
        onDeleteClick = {}
    )
}