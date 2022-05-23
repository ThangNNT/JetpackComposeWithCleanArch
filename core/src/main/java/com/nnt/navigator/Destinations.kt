package com.nnt.navigator

import com.nnt.domain.usecase.MovieType

sealed class Destinations(val route: String){
    object Home: Destinations("home")
    object Search: Destinations("search")
    object MovieDetail: Destinations("movie_detail/{${MovieDetailArgs.MovieId.value}}?${MovieDetailArgs.MovieName.value}={${MovieDetailArgs.MovieName.value}}"){
        fun createRoute(movieId: Int, movieName: String) = "movie_detail/$movieId?${MovieDetailArgs.MovieName.value}=$movieName"
    }
    object MoreMovie: Destinations("home/more/{${MoreMovieArgs.Type.value}}"){
        fun createRoute(movieType: MovieType) = "home/more/${movieType.name}"
    }
}

enum class MovieDetailArgs(val value: String){
    MovieId("movieId"),
    MovieName("movieName")
}

enum class MoreMovieArgs(val value: String){
    Type("type")
}
