package com.nnt.repository.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MovieResponse(
    val status_message: String? = null,
    val success: Boolean?= null,
    val status_code: Int?= null,

    val page: Int?= null,
    val results: List<Movie>?= null,
    val total_pages: Int?= null,
    val total_results: Int?= null
)

@Keep
@Serializable
data class Movie(
    val adult: Boolean?= null,
    val backdrop_path: String?= null,
    val genre_ids: List<Int>?= null,
    val id: Int?= null,
    val original_language: String?= null,
    val original_title: String?= null,
    val overview: String?= null,
    val popularity: Double?= null,
    val poster_path: String?= null,
    val release_date: String?= null,
    val title: String?= null,
    val video: Boolean?= null,
    val vote_average: Double?= null,
    val vote_count: Int?= null
)