package com.nnt.moviedetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nnt.core.common.Toolbar
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.utils.buildImageUrl
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailScreen(navController: NavController, movieId: Int, navigateUp: ()-> Unit){
    val viewModel = hiltViewModel<MovieDetailViewModel>()
    Column {
        Toolbar(title = "Detail", navigateUp = {
            navController.popBackStack()

        })
        MovieDetailBanner(movieDetailState = viewModel.movieDetail)
    }
    viewModel.getMovieDetail(movieId = movieId)
}

@Composable
fun MovieDetailBanner(movieDetailState: StateFlow<Result<MovieDetailModel>>){
    when(val state = movieDetailState.value){
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
        }
        is Result.Error -> {

        }
    }
}