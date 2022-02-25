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

    suspend fun  getMovieDetail(movieId: Int): MovieDetailResponse {
        return client.get(path = "movie/$movieId")
    }
}