package kv.compose.newsapp.domain.usecase.news

import kv.compose.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String) =
        repository.getNews(
            country = null,
            category = null,
            searchQuery = searchQuery
        )
}