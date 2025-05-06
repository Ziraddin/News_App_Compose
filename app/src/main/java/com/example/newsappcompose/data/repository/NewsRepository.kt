package com.example.newsappcompose.data.repository

import com.example.newsappcompose.data.model.NewsResponse
import com.example.newsappcompose.data.network.NewsApi
import com.example.newsappcompose.data.network.RetrofitHelper
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class NewsRepository(private val api: NewsApi = RetrofitHelper.instance) {
    suspend fun getNews(query: String): Response<NewsResponse> {
        return api.getNews(query)
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