package com.nnt.core

import com.nnt.domain.repository.MovieRepository
import com.nnt.domain.usecase.GetMovieDetailUseCase
import com.nnt.domain.usecase.GetMovieUseCase
import com.nnt.domain.usecase.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    @ViewModelScoped
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCase(movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailUseCase(movieRepository: MovieRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(movieRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideSearchMoviesUseCase(movieRepository: MovieRepository): SearchMoviesUseCase {
        return SearchMoviesUseCase(movieRepository)
    }
}