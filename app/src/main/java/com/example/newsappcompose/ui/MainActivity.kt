package com.example.newsappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            navController
                        )
                    },
                ) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        viewModelSearch = viewModelSearch,
                        viewModelBookmark = viewModelBookmark
                    )
                }
            }
        }
    }
}