package kv.compose.newsapp.data.network

import kv.compose.newsapp.BuildConfig
import kv.compose.newsapp.data.network.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("q") searchQuery: String?,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponseDto
}