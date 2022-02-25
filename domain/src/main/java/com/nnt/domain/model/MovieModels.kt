package com.nnt.domain.model

import com.nnt.domain.base.BaseModel

data class MovieModels(val movies: List<MovieModel>?): BaseModel()
data class MovieModel(
    val id: Int?,
    val name: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val popularity: Double?,
    val voteCount: Int?
) : BaseModel()
