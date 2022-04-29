package com.nnt.jetpackcomposewithcleanarch

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nnt.domain.usecase.MovieType
import com.nnt.home.HomeScreen
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import com.nnt.moviedetail.MovieDetailScreen
import com.nnt.navigator.Destinations
import com.nnt.navigator.MoreMovieArgs
import com.nnt.navigator.MovieDetailArgs
import com.nnt.viewmore.MoreMovieScreen
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalUnitApi
@Composable
fun JetApp(){
    JetpackComposeWithCleanArchTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Destinations.Home.route){
            composable(route = Destinations.Home.route){
                HomeScreen(navController)
            }
            composable(route = Destinations.MovieDetail.route, arguments = listOf(navArgument(MovieDetailArgs.MovieId.value){
                 type = NavType.IntType
            })){
                val movieId = it.arguments?.getInt(MovieDetailArgs.MovieId.value)
                requireNotNull(movieId)
                MovieDetailScreen(navController, movieId) {
                    navController.popBackStack()
                }
            }
            composable(route = Destinations.MoreMovie.route, arguments = listOf(navArgument(MoreMovieArgs.Type.value){
                type = NavType.StringType
            })){
                val typeName = it.arguments?.getString(MoreMovieArgs.Type.value)
                requireNotNull(typeName)
                val type = MovieType.valueOf(typeName)
                MoreMovieScreen(navController = navController, movieType = type)
            }
        }
    }
}