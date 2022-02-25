package com.nnt.repository.di

import com.nnt.domain.repository.MovieRepository
import com.nnt.remote.datasource.RemoteMovieDataSource
import com.nnt.repository.mapper.MapperFacade
import com.nnt.repository.repo.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: RemoteMovieDataSource, mapperFacade: MapperFacade): MovieRepository {
        return MovieRepositoryImpl(movieDataSource, mapperFacade)
    }
}