package com.nnt.domain.usecase

import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.repository.MovieRepository

class GetMovieDetailUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieId: Int): Result<MovieDetailModel>{
        return movieRepository.getMovieDetail(movieId)
    }
}