package kv.compose.newsapp.domain.usecase.favorite

import kv.compose.newsapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke() = repository.getFavorites()
}