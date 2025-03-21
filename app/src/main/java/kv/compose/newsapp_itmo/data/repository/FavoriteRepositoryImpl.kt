package kv.compose.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kv.compose.newsapp.data.cache.NewsDao
import kv.compose.newsapp.data.cache.NewsEntity.Companion.toEntity
import kv.compose.newsapp.data.cache.NewsEntity.Companion.toNews
import kv.compose.newsapp.domain.model.News
import kv.compose.newsapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : FavoriteRepository {
    override suspend fun addFavorite(news: News) {
        newsDao.insert(news.toEntity())
    }

    override suspend fun removeFavorite(news: News) {
        newsDao.delete(news.toEntity())
    }

    override fun getFavorites(): Flow<List<News>> = newsDao.getAll().map { newsList ->
        newsList.map {
            it.toNews()
        }
    }

    override fun isFavorite(news: News): Flow<Boolean> = newsDao.isFavorite(news.title)
}