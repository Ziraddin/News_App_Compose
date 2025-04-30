package com.example.newsappcompose.ui.data.network

import com.example.newsappcompose.ui.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything/")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apikey: String,
    ): Response<NewsResponse>

}