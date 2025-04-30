package com.example.newsappcompose.ui.ui.screen

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsappcompose.ui.data.repository.NewsRepository
import com.example.newsappcompose.ui.ui.viewmodel.SearchState
import com.example.newsappcompose.ui.ui.viewmodel.SearchViewModel

@Composable
fun SearchScreen(navController: NavController, newsRepository: NewsRepository) {
    val newsRepository = newsRepository
    var query by remember { mutableStateOf("") }
    val viewModeSearch = SearchViewModel(repository = newsRepository)
    val searchState by viewModeSearch.searchState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Search Screen", Modifier.padding(horizontal = 16.dp))
        SearchBar(
            query = query, onQueryChange = { newQuery ->
            query = newQuery
        }, onSearch = {
            if (query.isNotEmpty()) {
                viewModeSearch.searchNews(query)
            }
        }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        when (searchState) {
            is SearchState.Idle -> {
                Log.d("SearchState", "Idle")
            }

            is SearchState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {
                val result = (searchState as SearchState.Success).result
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(result.size) { index ->
                        val newsItem = result[index]
                        Text(text = newsItem.title, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            is SearchState.Error -> {
                val errorMessage = (searchState as SearchState.Error).message
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Search News") },
        singleLine = true,
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
    )
}

