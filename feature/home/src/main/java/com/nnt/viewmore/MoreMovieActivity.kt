package com.nnt.viewmore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.usecase.MovieType
import com.nnt.home.MovieCard
import com.nnt.home.MovieLists
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import com.nnt.navigator.MovieDetailActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MoreMovieActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: MovieDetailActivityNavigator
    private val viewModel: MoreMovieViewModel by viewModels()
    private val movieType: MovieType? by lazy { intent.getSerializableExtra(MOVIE_TYPE) as? MovieType }
    private var indexPage = 1
    private val movies = ArrayList<MovieModel>()

    @InternalCoroutinesApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeWithCleanArchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val state = rememberLazyListState()
                    when(val result = viewModel.moviesState.value){
                        is Result.Empty ->{

                        }
                        is Result.Loading ->{

                        }
                        is Result.Error -> {

                        }
                        is Result.Success ->{
                            movies.addAll(result.data?.movies.orEmpty())
                            LazyVerticalGrid(cells = GridCells.Fixed(2), state = state ){
                                items(movies) { item ->
                                    MovieCard(movie = item, navigator = navigator)
                                }
                            }
                        }
                    }
                    state.OnBottomReached(2) {
                        viewModel.getMovies(movieType, indexPage++)
                    }
                }
                viewModel.getMovies(movieType, indexPage++)
            }
        }
    }


    companion object {
        const val MOVIE_TYPE = "MOVIE_TYPE"
        fun newIntent(context: Context, movieType: MovieType) = Intent(context, MoreMovieActivity::class.java).apply {
            putExtra(MOVIE_TYPE, movieType)
        }
    }
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