package com.nnt.jetpackcomposewithcleanarch

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nnt.home.HomeScreen
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import com.nnt.jetpackcomposewithcleanarch.ui.theme.PrimaryColor
import com.nnt.moviedetail.MovieDetailScreen
import com.nnt.navigator.BottomNavigation
import com.nnt.navigator.Destinations
import com.nnt.navigator.MoreMovieArgs
import com.nnt.navigator.MovieDetailArgs
import com.nnt.viewmore.MoreMovieScreen
import kotlinx.coroutines.InternalCoroutinesApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalUnitApi
@Composable
fun JetApp(){
    JetpackComposeWithCleanArchTheme {
        //setup status bar color
        val systemUiController = rememberSystemUiController()
        if(isSystemInDarkTheme()){
            systemUiController.setSystemBarsColor(
                color = PrimaryColor
            )
        }else{
            systemUiController.setSystemBarsColor(
                color = PrimaryColor
            )
        }
        //setup navigation
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavigation(navController = navController) }
        ){
            NavigationGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalUnitApi::class, InternalCoroutinesApi::class, ExperimentalFoundationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Destinations.Home.route){
        composable(route = Destinations.Home.route){
            HomeScreen(navController)
        }
        composable(route = Destinations.MovieDetail.route, arguments = listOf(navArgument(MovieDetailArgs.MovieId.value){
            type = NavType.IntType
        }, navArgument(MovieDetailArgs.MovieName.value){
            type = NavType.StringType
        })){
            val movieId = it.arguments?.getInt(MovieDetailArgs.MovieId.value)
            val movieName = it.arguments?.getString(MovieDetailArgs.MovieName.value)
            requireNotNull(movieId)
            MovieDetailScreen(navController, movieName = movieName.orEmpty())
        }
        composable(route = Destinations.MoreMovie.route, arguments = listOf(navArgument(MoreMovieArgs.Type.value){
            type = NavType.StringType
        })){
            requireNotNull(it.arguments?.getString(MoreMovieArgs.Type.value)){
                "You need to pass MovieType string when navigate"
            }
            MoreMovieScreen(navController = navController)
        }
    }
}