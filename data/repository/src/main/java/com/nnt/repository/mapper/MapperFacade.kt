package com.nnt.repository.mapper


class MapperFacade(
    val movieResultMapper: MovieResultMapper,
    val movieMapper: MovieMapper,
    val movieDetailMapper: MovieDetailMapper,
    val movieTypeInversionMapper: MovieTypeInversionMapper
)