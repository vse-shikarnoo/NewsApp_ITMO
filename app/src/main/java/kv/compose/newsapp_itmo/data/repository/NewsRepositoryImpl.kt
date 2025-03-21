package kv.compose.newsapp.data.repository

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kv.compose.newsapp.data.network.NewsApi
import kv.compose.newsapp.data.paging.NewsPagingSource
import kv.compose.newsapp.domain.model.News
import kv.compose.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(
        country: String?,
        category: String?,
        searchQuery: String?
    ): Flow<PagingData<News>> = Pager(
        config =
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newsApi,
                country = country,
                category = category,
                searchQuery = searchQuery
            )
        }
    ).flow
}