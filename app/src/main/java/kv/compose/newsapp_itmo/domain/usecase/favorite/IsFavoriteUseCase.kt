package kv.compose.newsapp.domain.usecase.favorite

import kv.compose.newsapp.domain.model.News
import kv.compose.newsapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke(news: News) = repository.isFavorite(news)
}