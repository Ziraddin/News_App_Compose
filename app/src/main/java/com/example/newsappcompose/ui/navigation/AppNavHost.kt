package com.example.newsappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappcompose.data.model.NewsItem
import com.example.newsappcompose.ui.screen.ArticleDetailScreen
import com.example.newsappcompose.ui.screen.BookmarkScreen
import com.example.newsappcompose.ui.screen.Screen
import com.example.newsappcompose.ui.screen.SearchScreen
import com.example.newsappcompose.ui.viewmodel.BookmarkViewModel
import com.example.newsappcompose.ui.viewmodel.SearchViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelSearch: SearchViewModel,
    viewModelBookmark: BookmarkViewModel
) {
    NavHost(
        navController = navController, modifier = modifier, startDestination = Screen.Search.route
    ) {
        composable(Screen.Search.route) {
            SearchScreen(
                navController = navController, viewModelSearch = viewModelSearch,
                viewModelBookmark = viewModelBookmark
            )
        }
        composable(Screen.Bookmark.route) {
            BookmarkScreen(navController, viewModelBookmark)
        }
        composable(Screen.ArticleDetail.route) {
            val newsItem =
                navController.previousBackStackEntry?.savedStateHandle?.get<NewsItem>("newsItem")
            if (newsItem != null) {
                ArticleDetailScreen(
                    article = newsItem,
                    onBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}
