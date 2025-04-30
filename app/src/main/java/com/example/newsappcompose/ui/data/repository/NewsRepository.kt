package com.example.newsappcompose.ui.data.repository

import com.example.newsappcompose.ui.data.model.NewsResponse
import com.example.newsappcompose.ui.data.network.NewsApi
import com.example.newsappcompose.ui.data.network.RetrofitHelper
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class NewsRepository(private val api: NewsApi = RetrofitHelper.getInstance()) {
    private val apiKey = "0e2fcd9688a742aa9cd692b971cc2099"

    suspend fun getNews(query: String): Response<NewsResponse> {
        return api.getNews(query, apiKey)
    }
}

fun main() {
    runBlocking {
        val repository = NewsRepository()
        val response = repository.getNews("tesla")
        if (response.isSuccessful) {
            val searchResponse = response.body()
            searchResponse?.articles?.forEach {
                println("${it.title}, ${it.url}")
            }
        }
    }
}