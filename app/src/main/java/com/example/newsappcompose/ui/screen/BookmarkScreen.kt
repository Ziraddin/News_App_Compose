package com.example.newsappcompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappcompose.ui.component.NewsItemList
import com.example.newsappcompose.ui.viewmodel.BookmarkViewModel

@Composable
fun BookmarkScreen(navController: NavController, viewModelBookmark: BookmarkViewModel) {
    val bookmarks by viewModelBookmark.bookmarks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 24.dp
                ),
            textAlign = TextAlign.Center
        )

        NewsItemList(
            result = bookmarks,
            navController = navController,
            viewModel = viewModelBookmark
        )
    }
}
