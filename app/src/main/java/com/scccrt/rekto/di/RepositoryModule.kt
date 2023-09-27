package com.scccrt.rekto.di

import com.scccrt.rekto.data.remote.RektoApi
import com.scccrt.rekto.data.repository.MovieRepository
import com.scccrt.rekto.data.repository.MovieRepositoryImpl
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
        dispatcher: CoroutineDispatcher
    ): MovieRepository = MovieRepositoryImpl(api, dispatcher)
}