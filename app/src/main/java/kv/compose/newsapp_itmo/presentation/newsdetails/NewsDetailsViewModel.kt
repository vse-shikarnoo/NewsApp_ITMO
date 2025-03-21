package kv.compose.newsapp.presentation.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kv.compose.newsapp.domain.usecase.favorite.AddFavoriteUseCase
import kv.compose.newsapp.domain.usecase.favorite.IsFavoriteUseCase
import kv.compose.newsapp.domain.usecase.favorite.RemoveFavoriteUseCase
import kv.compose.newsapp.presentation.model.NewsUiModel
import kv.compose.newsapp.presentation.model.NewsUiModel.Companion.toNews
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {
    fun addFavorite(news: NewsUiModel) {
        viewModelScope.launch {
            addFavoriteUseCase(news.toNews())
        }
    }

    fun removeFavorite(news: NewsUiModel) {
        viewModelScope.launch {
            removeFavoriteUseCase(news.toNews())
        }
    }

    fun isFavorite(news: NewsUiModel) = isFavoriteUseCase(news.toNews())
}