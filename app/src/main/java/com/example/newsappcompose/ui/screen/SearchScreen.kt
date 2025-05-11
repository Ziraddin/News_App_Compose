package com.example.newsappcompose.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappcompose.ui.component.NewsItemList
import com.example.newsappcompose.ui.component.SearchBar
import com.example.newsappcompose.ui.viewmodel.BookmarkViewModel
import com.example.newsappcompose.ui.viewmodel.SearchState
import com.example.newsappcompose.ui.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    viewModelSearch: SearchViewModel,
    viewModelBookmark: BookmarkViewModel
) {
    var query by remember { mutableStateOf("") }
    val searchState by viewModelSearch.searchState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Search News",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 24.dp
                ),
            textAlign = TextAlign.Center
        )
        SearchBar(
            query = query, onQueryChange = { newQuery ->
            query = newQuery
        }, onSearch = {
            if (query.isNotEmpty()) {
                viewModelSearch.searchNews(query)
                Log.d("SearchQuery", "Searching for: $query")
            }
        }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("search_bar")
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
                if (result.isEmpty()) {
                    Toast.makeText(
                        LocalContext.current, "No results found", Toast.LENGTH_LONG
                    ).show()
                } else {
                    NewsItemList(
                        result = result,
                        navController = navController,
                        viewModel = viewModelBookmark,
                        modifier = Modifier.testTag(
                            "news_list"
                        ),
                    )
                }
            }

            is SearchState.Error -> {
                val errorMessage = (searchState as SearchState.Error).message
                Toast.makeText(
                    LocalContext.current, errorMessage, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}



