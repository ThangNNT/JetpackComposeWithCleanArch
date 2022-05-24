package com.nnt.domain.repository

import arrow.core.Either
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType

interface MovieRepository{
    suspend fun getMovies(type: MovieType, page: Int? = null): Result<MovieModels>

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailModel>

    suspend fun searchMovies(keyword: String?, language: String?, page: Int?, includeAdult: Boolean?, region: String?, year: Int?, primaryReleaseYear: Int?): Result<MovieModels>
}