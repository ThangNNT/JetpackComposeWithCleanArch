package com.nnt.repository.mapper

import arrow.core.Either
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.model.MovieModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType
import com.nnt.remote.response.MovieDetailResponse
import com.nnt.repository.model.ErrorResponse
import com.nnt.repository.model.Movie
import com.nnt.repository.model.MovieResponse


class MovieResultMapper(private val errorMapper: ErrorMapper, private val movieMapper: MovieMapper): Mapper<Either<ErrorResponse, MovieResponse>,Result<MovieModels>>(){
    override fun map(input: Either<ErrorResponse, MovieResponse>): Result<MovieModels> {
        return input.fold({
            Result.Error(errorMapper.map(it))
        }, {
            Result.Success(MovieModels(it.results?.map { movie->
                movieMapper.map(
                    movie
                )
            }))
        })
    }

}
class MovieMapper : Mapper<Movie, MovieModel>() {
    override fun map(input: Movie): MovieModel {
        return MovieModel(
            id = input.id,
            name = input.title,
            posterPath = input.poster_path,
            backdropPath = input.backdrop_path,
            voteAverage = input.vote_average,
            voteCount = input.vote_count,
            popularity = input.popularity
        )
    }
}

class MovieTypeInversionMapper: Mapper<MovieType, com.nnt.remote.datasource.MovieType>() {
    override fun map(input: MovieType): com.nnt.remote.datasource.MovieType {
        return when(input){
            MovieType.LATEST -> com.nnt.remote.datasource.MovieType.LATEST
            MovieType.NOW_PLAYING -> com.nnt.remote.datasource.MovieType.NOW_PLAYING
            MovieType.POPULAR -> com.nnt.remote.datasource.MovieType.POPULAR
            MovieType.TOP_RATED -> com.nnt.remote.datasource.MovieType.TOP_RATED
            MovieType.UPCOMING -> com.nnt.remote.datasource.MovieType.UPCOMING
        }
    }
}

class MovieDetailMapper(
    private val errorMapper: ErrorMapper,
    private val genreMapper: GenreMapper,
    private val companyMapper: ProductionCompanyMapper,
    private val countryMapper: ProductionCountryMapper,
    private val spokenLanguageMapper: SpokenLanguageMapper
) : Mapper<Either<ErrorResponse, MovieDetailResponse>, Result<MovieDetailModel>>() {
    override fun map(input: Either<ErrorResponse, MovieDetailResponse>): Result<MovieDetailModel> {
        return input.fold({
            Result.Error(errorModel = errorMapper.map(it))
        }, {
            Result.Success(
                MovieDetailModel(
                    adult = it.adult,
                    backdrop_path = it.backdrop_path,
                    belongs_to_collection = it.belongs_to_collection_test,
                    budget = it.budget,
                    genres = genreMapper.mapList(it.genres),
                    homepage = it.homepage,
                    id = it.id,
                    imdb_id = it.imdb_id,
                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = it.poster_path,
                    production_companies = companyMapper.mapList(it.production_companies),
                    production_countries = countryMapper.mapList(it.production_countries),
                    release_date = it.release_date,
                    revenue = it.revenue,
                    runtime = it.runtime,
                    spoken_languages = spokenLanguageMapper.mapList(it.spoken_languages),
                    status = it.status,
                    tagline = it.tagline,
                    title = it.title,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count
                )
            )
        })
    }
}