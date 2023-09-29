package com.scccrt.rekto.ui.feature.movie.search.history.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.scccrt.rekto.data.local.entities.SearchQuery
import com.scccrt.rekto.ui.base.SIDE_EFFECTS_KEY
import com.scccrt.rekto.ui.feature.movie.search.history.SearchHistoryContract
import com.scccrt.rekto.ui.feature.movie.search.history.SearchHistoryViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SearchHistoryScreen(
    viewModel: SearchHistoryViewModel = hiltViewModel(),
    onItemClicked: (String) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is SearchHistoryContract.Effect.Action.Search -> onItemClicked(effect.query)
            }
        }.collect()
    }

    SearchHistoryList(
        state = viewModel.uiState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
    )
}

@Composable
fun SearchHistoryList(
    state: SearchHistoryContract.State,
    onEventSent: (event: SearchHistoryContract.Event) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.searchHistory) { searchQuery ->
                SearchHistoryItem(
                    searchQuery = searchQuery,
                    onItemClick = {
                        onEventSent(SearchHistoryContract.Event.OnSelect(it))
                    },
                    onDeleteClick = {
                        onEventSent(SearchHistoryContract.Event.OnRemove(it))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHistoryListPreview() {
    val searchHistory = List(3) { SearchQuery(query = "Search Query") }
    SearchHistoryList(
        state = SearchHistoryContract.State(searchHistory),
        onEventSent = {}
    )
}