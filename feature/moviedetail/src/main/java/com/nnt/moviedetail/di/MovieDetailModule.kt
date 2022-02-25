package com.nnt.moviedetail.di

import com.nnt.moviedetail.navigator.MovieDetailActivityNavigatorImpl
import com.nnt.navigator.MovieDetailActivityNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Provides
    @Singleton
    fun provideMovieDetailNavigator(): MovieDetailActivityNavigator{
        return MovieDetailActivityNavigatorImpl()
    }
}