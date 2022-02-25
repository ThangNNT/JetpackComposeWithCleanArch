package com.nnt.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.compose.ui.unit.ExperimentalUnitApi

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import com.nnt.jetpackcomposewithcleanarch.ui.theme.Purple200
import com.nnt.navigator.MovieDetailActivityNavigator
import com.nnt.utils.buildImageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import okhttp3.internal.http2.Header
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigator: MovieDetailActivityNavigator

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalUnitApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeWithCleanArchTheme() {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn() {
                        item {
                            Header(text = "Popular", movieType = MovieType.POPULAR)
                            MovieLists(viewModel.popularMovies, navigator = navigator)
                            Header(text = "Top Rated", movieType = MovieType.TOP_RATED)
                            MovieLists(viewModel.topRatedMovies, navigator = navigator)
                            Header(text = "Upcoming", movieType = MovieType.UPCOMING)
                            MovieLists(viewModel.upcomingMovies, navigator = navigator)
                            Header(text = "Now Playing", movieType = MovieType.NOW_PLAYING)
                            MovieLists(viewModel.nowPlayingMovies, navigator = navigator)
                            Text("",Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieLists(moviesState: StateFlow<Result<MovieModels>>, navigator: MovieDetailActivityNavigator){
    when(val state = moviesState.collectAsState().value){
        is Result.Loading -> {
            CircularProgressIndicator(Modifier.padding(20.dp, 20.dp))
        }
        is Result.Success -> {
            state.data?.movies?.let { movies ->
                LazyRow(){
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

@Composable
fun MovieCard(movie: MovieModel, navigator: MovieDetailActivityNavigator){
    val context = LocalContext.current
    Card(Modifier
        .padding(10.dp, 0.dp)
        .selectable(selected = true, onClick = {
            movie.id?.let {
                context.startActivity(navigator.newIntent(context, it))
            }
        }), elevation = 2.dp) {
        Column(modifier = Modifier
            .width(120.dp)) {
            Image(modifier = Modifier
                .height(200.dp)
                .width(120.dp),
                painter = rememberImagePainter(buildImageUrl(movie.posterPath), builder = {
                    error(R.drawable.no_poster_available)
                }),
                contentDescription = ""
            )

            Text(text = movie.name?:"", maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@ExperimentalUnitApi
@Composable
fun Header(text: String, movieType: MovieType) {
    val textStyle =
        TextStyle(fontWeight = FontWeight.W600, fontSize = TextUnit(24f, TextUnitType.Sp))
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
                    Log.d("AAAAAAAAAAAAAA", movieType.toString())
                }
                .weight(2f), style = textMoreStyle, textAlign = TextAlign.End
        )

    }
}
