package com.example.newsappcompose.ui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsappcompose.ui.ui.screen.ArticleDetailScreen
import com.example.newsappcompose.ui.ui.screen.BookmarkScreen
import com.example.newsappcompose.ui.ui.screen.Screen
import com.example.newsappcompose.ui.ui.screen.SearchScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Search.route
    ) {
        composable(Screen.Search.route) {
            SearchScreen(
                navController = navController
            )
        }
        composable(Screen.Bookmark.route) {
            BookmarkScreen()
        }
        composable(Screen.ArticleDetail.route) {
            ArticleDetailScreen()
        }
    }
}
