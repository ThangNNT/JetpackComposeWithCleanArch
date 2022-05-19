package com.nnt.domain.usecase

import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModels
import com.nnt.domain.repository.MovieRepository

class GetMovieUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(type: MovieType, page: Int?= null): Result<MovieModels>{
        return movieRepository.getMovies(type, page)
    }
}

enum class MovieType(val readableName: String){
    LATEST("Latest"),
    NOW_PLAYING("Now playing"),
    POPULAR("Popular"),
    TOP_RATED("Top rated"),
    UPCOMING("Upcoming")
}