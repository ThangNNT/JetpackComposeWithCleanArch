package com.nnt.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnt.core.common.MovieCard
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType
import com.nnt.jetpackcomposewithcleanarch.ui.theme.Purple200
import com.nnt.navigator.Destinations
import kotlinx.coroutines.flow.StateFlow

@ExperimentalUnitApi
@Composable
fun HomeScreen(navController: NavController){
    val viewModel = hiltViewModel<MainViewModel>()
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn() {
            item {
                Header(text = "Popular", movieType = MovieType.POPULAR, navController)
                MovieLists(viewModel.popularMovies, navigator = navController)
                Header(text = "Top Rated", movieType = MovieType.TOP_RATED, navController)
                MovieLists(viewModel.topRatedMovies, navigator = navController)
                Header(text = "Upcoming", movieType = MovieType.UPCOMING, navController)
                MovieLists(viewModel.upcomingMovies, navigator = navController)
                Header(text = "Now Playing", movieType = MovieType.NOW_PLAYING, navController)
                MovieLists(viewModel.nowPlayingMovies, navigator = navController)
                Text("",Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp))
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun MovieLists(moviesState: StateFlow<Result<MovieModels>>, navigator: NavController){
    when(val state = moviesState.collectAsState().value){
        is Result.Loading -> {
            CircularProgressIndicator(Modifier.padding(20.dp, 20.dp))
        }
        is Result.Success -> {
            state.data?.movies?.let { movies ->
                LazyRow{
                    items(movies,
                        key = { movie -> movie
                        }){ movie ->
                        MovieCard(movie = movie, navigator = navigator)
                    }
                }
            }
        }
        is Result.Error -> {
        }
        else -> {

        }
    }

}

@ExperimentalUnitApi
@Composable
fun Header(text: String, movieType: MovieType, navController: NavController) {
    val textStyle =
        TextStyle(fontWeight = FontWeight.W600, fontSize = TextUnit(20f, TextUnitType.Sp))
    val textMoreStyle = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = TextUnit(14f, TextUnitType.Sp),
        color = Purple200
    )
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text,
            Modifier
                .padding(16.dp, vertical = 16.dp)
                .weight(8f), style = textStyle)
        Text("More",
            Modifier
                .padding(16.dp, vertical = 16.dp)
                .clickable {
                    navController.navigate(Destinations.MoreMovie.createRoute(movieType = movieType))
                }
                .weight(2f), style = textMoreStyle, textAlign = TextAlign.End
        )

    }
}