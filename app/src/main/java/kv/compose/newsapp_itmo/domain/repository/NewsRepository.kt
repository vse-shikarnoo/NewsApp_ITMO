package kv.compose.newsapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kv.compose.newsapp.domain.model.News

interface NewsRepository {
    fun getNews(
        country: String?,
        category: String?,
        searchQuery: String?
    ): Flow<PagingData<News>>
}