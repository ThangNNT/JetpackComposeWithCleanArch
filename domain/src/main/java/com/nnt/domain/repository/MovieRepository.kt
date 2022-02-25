package com.nnt.domain.repository

import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType

interface MovieRepository{
    suspend fun getMovies(type: MovieType, page: Int? = null): Result<MovieModels>

    suspend fun getMovieDetail(movieId: Int): Result<MovieDetailModel>
}