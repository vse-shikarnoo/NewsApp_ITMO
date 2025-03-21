package kv.compose.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kv.compose.newsapp.data.repository.FavoriteRepositoryImpl
import kv.compose.newsapp.data.repository.NewsRepositoryImpl
import kv.compose.newsapp.domain.repository.FavoriteRepository
import kv.compose.newsapp.domain.repository.NewsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository
}