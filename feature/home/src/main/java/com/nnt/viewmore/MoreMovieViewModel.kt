package com.nnt.viewmore

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModel
import com.nnt.domain.usecase.MovieType
import com.nnt.navigator.MoreMovieArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MoreMovieViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase, savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _moviesState = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val moviesState: StateFlow<Result<MovieModels>> = _moviesState

    val state = LazyListState()
    val movies: ArrayList<MovieModel> = ArrayList()
    var page = START_PAGE
    private set
    var type = MovieType.POPULAR
    private set

    init {
        val typeString = savedStateHandle.get<String>(MoreMovieArgs.Type.value)
        if (typeString != null) {
            type = MovieType.valueOf(typeString)
        }
        getFirstPageMovies()
    }

    private fun getMovies(){
        _moviesState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _moviesState.value = getMovieUseCase.execute(type, page)
        }
    }

    fun getFirstPageMovies(){
        page = START_PAGE
        getMovies()
    }

    fun getMorePageMovies(){
        page++
        getMovies()
    }



    companion object {
        const val START_PAGE = 1
    }
}