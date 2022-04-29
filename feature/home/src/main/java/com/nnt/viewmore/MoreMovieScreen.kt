package com.nnt.viewmore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.usecase.MovieType
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun MoreMovieScreen(navController: NavController, movieType: MovieType) {
    val viewModel = hiltViewModel<MoreMovieViewModel>()
    val state = rememberLazyListState()
    val movies: ArrayList<MovieModel> = remember { ArrayList() }
    var indexPage: Int = remember { 1 }

    when (val result = viewModel.moviesState.value) {
        is Result.Empty -> {

        }
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        is Result.Success -> {
            movies.addAll(result.data?.movies.orEmpty())
            LazyVerticalGrid(cells = GridCells.Fixed(2), state = state) {
                items(movies) { item ->
                    // MovieCard(movie = item, navigator = navigator)
                }
            }
        }
    }
    state.OnBottomReached(2) {
        viewModel.getMovies(movieType, indexPage++)
    }
    viewModel.getMovies(movieType, indexPage++)
}


@InternalCoroutinesApi
@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) {
    // state object which tells us if we should load more
    // Buffer must be positive.
    // Or our list will never reach the bottom.
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            // subtract buffer from the total items
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) loadMore() }
    }
}