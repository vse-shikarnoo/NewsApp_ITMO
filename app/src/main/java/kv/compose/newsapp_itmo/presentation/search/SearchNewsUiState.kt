package kv.compose.newsapp.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kv.compose.newsapp.presentation.model.NewsUiModel

data class SearchNewsUiState(
    val searchValue: String = "",
    val news: Flow<PagingData<NewsUiModel>>? = null
)