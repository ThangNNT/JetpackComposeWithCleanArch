package com.nnt.repository.repo

import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.repository.MovieRepository
import com.nnt.remote.datasource.RemoteMovieDataSource
import com.nnt.repository.base.BaseRepository
import com.nnt.repository.mapper.MapperFacade


class MovieRepositoryImpl constructor(private val movieRemoteDataSource: RemoteMovieDataSource, private val mapperFacade: MapperFacade) :
    BaseRepository(),
    MovieRepository {
    override suspend fun getMovies(type: com.nnt.domain.usecase.MovieType, page: Int?): Result<MovieModels> {
        return mapperFacade.movieResultMapper.map(
            movieRemoteDataSource.getMovies(
                mapperFacade.movieTypeInversionMapper.map(type), page
            )
        )
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetailModel> {
        return mapperFacade.movieDetailMapper.map(movieRemoteDataSource.getMovieDetail(movieId))
    }
}