package com.nnt.viewmore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnt.core.common.HorizontalMovieCard
import com.nnt.core.common.Toolbar
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.model.MovieModels
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalUnitApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun MoreMovieScreen(navController: NavController) {
    val viewModel = hiltViewModel<MoreMovieViewModel>()
    Column {
        Toolbar(title = viewModel.type.readableName, navigateUp = {
            navController.popBackStack()
        })
        HorizontalMovies(viewModel.movies, viewModel.state, moviesStateFlow = viewModel.moviesState, navController = navController)
        viewModel.state.OnBottomReached(10) {
            viewModel.getMorePageMovies()
        }
    }
}

@ExperimentalUnitApi
@ExperimentalFoundationApi
@Composable
fun HorizontalMovies(moviesInit: ArrayList<MovieModel>, state: LazyListState, moviesStateFlow: StateFlow<Result<MovieModels>>, navController: NavController){
    when (val result = moviesStateFlow.collectAsState().value) {
        is Result.Empty -> {

        }
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        is Result.Success -> {
            moviesInit.addAll(result.data?.movies.orEmpty())
        }
    }
    LazyColumn(state = state, modifier = Modifier.padding(16.dp, 0.dp)) {
        items(moviesInit, key = {
            it.id ?: 0
        }) { item ->
            //first item has higher spacer
            if (item.id == moviesInit.getOrNull(0)?.id)
                Spacer(modifier = Modifier.height(16.dp))
            else Spacer(modifier = Modifier.height(2.dp))
            HorizontalMovieCard(movie = item, navigator = navController)
            Spacer(modifier = Modifier.height(2.dp))
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