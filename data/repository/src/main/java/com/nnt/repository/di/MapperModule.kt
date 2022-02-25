package com.nnt.repository.di

import com.nnt.repository.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    @Singleton
    fun provideErrorMapper(): ErrorMapper{
        return ErrorMapper()
    }

    @Provides
    @Singleton
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper()
    }

    @Provides
    @Singleton
    fun provideMovieResultMapper(errorMapper: ErrorMapper, movieMapper: MovieMapper): MovieResultMapper {
        return MovieResultMapper(errorMapper, movieMapper)
    }

    @Provides
    @Singleton
    fun provideMovieTypeInversionMapper(): MovieTypeInversionMapper {
        return MovieTypeInversionMapper()
    }

    @Provides
    @Singleton
    fun provideGenreMapper(): GenreMapper {
        return GenreMapper()
    }

    @Provides
    @Singleton
    fun provideProductCompanyMapper(): ProductionCompanyMapper {
        return ProductionCompanyMapper()
    }

    @Provides
    @Singleton
    fun provideProductionCountryMapper(): ProductionCountryMapper {
        return ProductionCountryMapper()
    }

    @Provides
    @Singleton
    fun provideSpokenLanguageMapper(): SpokenLanguageMapper {
        return SpokenLanguageMapper()
    }

    @Provides
    @Singleton
    fun provideMovieDetailMapper(
        errorMapper: ErrorMapper,
        genreMapper: GenreMapper,
        productionCompanyMapper: ProductionCompanyMapper,
        productionCountryMapper: ProductionCountryMapper,
        spokenLanguageMapper: SpokenLanguageMapper
    ): MovieDetailMapper {
        return MovieDetailMapper(errorMapper, genreMapper, productionCompanyMapper, productionCountryMapper, spokenLanguageMapper)
    }

    @Provides
    @Singleton
    fun provideMapperFacade(movieResultMapper: MovieResultMapper, movieMapper: MovieMapper, movieDetailMapper: MovieDetailMapper, movieTypeInversionMapper: MovieTypeInversionMapper): MapperFacade {
        return MapperFacade(movieResultMapper, movieMapper, movieDetailMapper, movieTypeInversionMapper)
    }
}