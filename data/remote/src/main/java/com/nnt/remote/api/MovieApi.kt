package com.nnt.remote.api

import com.nnt.remote.response.MovieDetailResponse
import com.nnt.repository.model.MovieResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class MovieApi(private val client: HttpClient) {
    suspend fun getMovies(type: String, page: Int? = null): MovieResponse {
        return client.get(path = "movie/$type"){
           parameter("page", page)
        }
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return client.get(path = "movie/$movieId")
    }

    suspend fun searchMovies(keyword: String?, language: String?, page: Int?, include_adult: Boolean?, region: String?, year: Int?, primary_release_year: Int?): MovieResponse {
        return client.get(path ="search/movie"){
            parameter("query", keyword)
            parameter("language", language)
            parameter("page", page)
            parameter("include_adult", include_adult)
            parameter("region", region)
            parameter("year", year)
            parameter("primary_release_year", primary_release_year)
        }
    }
}