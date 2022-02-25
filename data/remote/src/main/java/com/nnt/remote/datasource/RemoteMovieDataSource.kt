package com.nnt.remote.datasource

import arrow.core.Either
import com.nnt.remote.base.BaseRemoteDataSource
import com.nnt.remote.api.MovieApi
import com.nnt.remote.response.MovieDetailResponse
import com.nnt.repository.model.ErrorResponse
import com.nnt.repository.model.MovieResponse
import javax.inject.Inject

interface RemoteMovieDataSource {
    suspend fun getMovies(type: MovieType, page: Int? = null): Either<ErrorResponse, MovieResponse>
    suspend fun getMovieDetail(movieId: Int): Either<ErrorResponse, MovieDetailResponse>
}

class RemoteMovieDataSourceImpl(private val movieApi: MovieApi) : BaseRemoteDataSource(),
    RemoteMovieDataSource {
    override suspend fun getMovies(
        type: MovieType,
        page: Int?
    ): Either<ErrorResponse, MovieResponse> =
        getResult { movieApi.getMovies(type.value, page = null) }

    override suspend fun getMovieDetail(movieId: Int): Either<ErrorResponse, MovieDetailResponse> =
        getResult {
            movieApi.getMovieDetail(movieId)
        }
}

enum class MovieType(val value: String){
    LATEST("latest"),
    NOW_PLAYING("now_playing"),
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming")
}