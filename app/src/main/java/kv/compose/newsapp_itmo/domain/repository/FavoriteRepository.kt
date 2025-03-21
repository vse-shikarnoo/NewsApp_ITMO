package kv.compose.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kv.compose.newsapp.domain.model.News

interface FavoriteRepository {
    suspend fun addFavorite(news: News)

    suspend fun removeFavorite(news: News)

    fun getFavorites(): Flow<List<News>>

    fun isFavorite(news: News): Flow<Boolean>
}