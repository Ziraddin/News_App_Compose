package com.example.newsappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsappcompose.data.local.SharedPreferences
import com.example.newsappcompose.data.repository.NewsRepository
import com.example.newsappcompose.ui.component.BottomNavigationBar
import com.example.newsappcompose.ui.navigation.AppNavHost
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme
import com.example.newsappcompose.ui.viewmodel.BookmarkViewModel
import com.example.newsappcompose.ui.viewmodel.SearchViewModel

class MainActivity : ComponentActivity() {
    private lateinit var newsRepository: NewsRepository
    private lateinit var sharedPreferences: SharedPreferences

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        newsRepository = NewsRepository()
        sharedPreferences = SharedPreferences(this)
        setContent {
            val viewModelSearch = remember { SearchViewModel(repository = newsRepository) }
            val viewModelBookmark = remember { BookmarkViewModel(sharedPreferences) }
            val navController = rememberNavController()
            NewsAppComposeTheme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route

                Scaffold(
                    containerColor = Color(0xFFE3E0E0),
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentDestination != "article_detail") {
                            BottomNavigationBar(
                                navController = navController,
                                modifier = Modifier.semantics {
                                    testTagsAsResourceId = true
                                },
                            )
                        }
                    },
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                            .semantics {
                                testTagsAsResourceId = true
                            },
                        viewModelSearch = viewModelSearch,
                        viewModelBookmark = viewModelBookmark
                    )
                    LaunchedEffect(Unit) {
                        reportFullyDrawn()
                    }
                }
            }
        }
    }
}