package com.nnt.domain.usecase

import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModels
import com.nnt.domain.repository.MovieRepository

class SearchMoviesUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(keyword: String? = null, language: String? = null, page: Int? = null, includeAdult: Boolean? = null, region: String? = null, year: Int? = null, primaryReleaseYear: Int? = null): Result<MovieModels>{
        return movieRepository.searchMovies(keyword, language, page, includeAdult, region, year, primaryReleaseYear)
    }
}

