package kv.compose.newsapp.domain.usecase.favorite

import kv.compose.newsapp.domain.model.News
import kv.compose.newsapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(news: News) = repository.addFavorite(news)
}