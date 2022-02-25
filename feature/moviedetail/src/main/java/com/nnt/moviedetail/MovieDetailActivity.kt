package com.nnt.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.util.DebugLogger
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import com.nnt.moviedetail.navigator.MovieDetailActivityNavigatorImpl
import com.nnt.utils.buildImageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private val movieId by lazy { intent.getIntExtra(MovieDetailActivityNavigatorImpl.MOVIE_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeWithCleanArchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MovieDetailBanner(movieDetailState = viewModel.movieDetail)
                }
            }
        }
        viewModel.getMovieDetail(movieId)
    }
}

@Composable
fun MovieDetailBanner(movieDetailState: StateFlow<Result<MovieDetailModel>>){
    when(val state = movieDetailState.collectAsState().value){
        is Result.Empty -> {
        }
        is Result.Loading -> {
            
        }
        is Result.Success -> {
            val movieDetail = state.data
            LazyColumn(Modifier.padding(16.dp)){
                item {
                    Image(modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                        painter = rememberImagePainter(buildImageUrl(movieDetail?.backdrop_path), builder = {
                            error(R.drawable.no_poster_available)
                        }),
                        contentDescription = ""
                    )
                }
                item {
                    Text(text = movieDetail?.overview?:"", Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp))
                }
            }


            Log.d("AAAAAAAAAAAAAA", "${buildImageUrl(movieDetail?.backdrop_path)}")
        }
        is Result.Error -> {
            
        }
    }
}

