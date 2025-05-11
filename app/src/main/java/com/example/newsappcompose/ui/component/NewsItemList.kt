package com.example.newsappcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappcompose.data.model.NewsItem
import com.example.newsappcompose.ui.screen.Screen
import com.example.newsappcompose.ui.viewmodel.BookmarkViewModel

@Composable
fun NewsItemList(
    result: List<NewsItem>,
    navController: NavController,
    viewModel: BookmarkViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            result.size,
            key = { index -> index },
        ) { index ->
            val newsItem = result[index]

            NewsItemCard(
                modifier = Modifier
                    .testTag("news_item"),
                newsItem = newsItem,
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "newsItem",
                        newsItem
                    )
                    navController.navigate(Screen.ArticleDetail.route)
                },
                addBookmark = {
                    viewModel.addBookmark(newsItem)
                },
                removeBookmark = {
                    viewModel.removeBookmark(newsItem)
                }
            )
        }
    }
}
