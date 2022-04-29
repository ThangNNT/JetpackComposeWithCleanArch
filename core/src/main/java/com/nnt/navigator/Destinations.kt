package com.nnt.navigator

import android.util.Log
import com.nnt.domain.usecase.MovieType

sealed class Destinations(val route: String){
    object Home: Destinations("home")
    object MovieDetail: Destinations("movie_detail/{${MovieDetailArgs.MovieId.value}}"){
        fun createRoute(movieId: Int) = "movie_detail/$movieId"
    }
    object MoreMovie: Destinations("home/more/{${MoreMovieArgs.Type.value}}"){
        fun createRoute(movieType: MovieType) = "home/more/${movieType.name}"
    }
}

enum class MovieDetailArgs(val value: String){
    MovieId("movieId")
}

enum class MoreMovieArgs(val value: String){
    Type("type")
}
