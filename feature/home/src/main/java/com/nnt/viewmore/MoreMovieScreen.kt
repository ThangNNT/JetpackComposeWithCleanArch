package com.nnt.viewmore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnt.core.common.HorizontalMovieCard
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.MovieType
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalUnitApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun MoreMovieScreen(navController: NavController, movieType: MovieType) {
    val viewModel = hiltViewModel<MoreMovieViewModel>()
    val state = rememberLazyListState()
    var indexPage: Int = remember { 1 }
    HorizontalMovies(state, moviesStateFlow = viewModel.moviesState, navController = navController)
    state.OnBottomReached(2) {
        viewModel.getMovies(movieType, indexPage++)
    }
    viewModel.getMovies(movieType, indexPage++)
}

@ExperimentalUnitApi
@ExperimentalFoundationApi
@Composable
fun HorizontalMovies(state: LazyListState, moviesStateFlow: StateFlow<Result<MovieModels>>, navController: NavController){
    val movies: ArrayList<MovieModel> = remember { ArrayList() }
    when (val result = moviesStateFlow.collectAsState().value) {
        is Result.Empty -> {

        }
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        is Result.Success -> {
            movies.addAll(result.data?.movies.orEmpty())
            LazyVerticalGrid(cells = GridCells.Fixed(1), state = state) {
                items(movies) { item ->
                    HorizontalMovieCard(movie = item, navigator = navController)
                }
            }
        }
    }
}


@Composable
fun LazyListState.OnBottomReached(
    // tells how many items before we reach the bottom of the list
    // to call onLoadMore function
    buffer : Int = 0,
    onLoadMore : () -> Unit
) {
    // Buffer must be positive.
    // Or our list will never reach the bottom.
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                return@derivedStateOf true

            // subtract buffer from the total items
            lastVisibleItem.index >=  layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) onLoadMore() }
    }
}