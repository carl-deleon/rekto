package com.scccrt.rekto.di

import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.remote.RektoApi
import com.scccrt.rekto.data.repository.MovieRepository
import com.scccrt.rekto.data.repository.MovieRepositoryImpl
import com.scccrt.rekto.data.repository.SearchHistoryRepository
import com.scccrt.rekto.data.repository.SearchHistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(
        api: RektoApi,
        searchHistoryDao: SearchHistoryDao,
        dispatcher: CoroutineDispatcher
    ): MovieRepository = MovieRepositoryImpl(api, searchHistoryDao, dispatcher)

    @Provides
    @ViewModelScoped
    fun provideSearchHistoryRepository(
        searchHistoryDao: SearchHistoryDao,
        dispatcher: CoroutineDispatcher
    ): SearchHistoryRepository = SearchHistoryRepositoryImpl(searchHistoryDao, dispatcher)
}