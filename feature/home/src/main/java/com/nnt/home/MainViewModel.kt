package com.nnt.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.GetMovieUseCase
import com.nnt.domain.usecase.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieUseCase: GetMovieUseCase): ViewModel() {
    private val _popularMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val popularMovies: StateFlow<Result<MovieModels>> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val upcomingMovies: StateFlow<Result<MovieModels>> = _upcomingMovies

    private val _topRatedMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val topRatedMovies: StateFlow<Result<MovieModels>> = _topRatedMovies

    private val _nowPlayingMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val nowPlayingMovies: StateFlow<Result<MovieModels>> = _nowPlayingMovies

    private val _latestMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val latestMovies: StateFlow<Result<MovieModels>> = _latestMovies

    init {
        getMovies()
    }

    private fun getMovies(){
        getPopularMovies()
        getUpcomingMovies()
        getTopRatedMovies()
        getLatestMovies()
        getNowPlayingMovies()
    }

    fun refresh(){
        getMovies()
    }

    private fun getPopularMovies(){
        _popularMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _popularMovies.value = movieUseCase.execute(MovieType.POPULAR)
        }
    }

    private fun getUpcomingMovies(){
        _upcomingMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMovies.value = movieUseCase.execute(MovieType.UPCOMING)
        }
    }

    private fun getTopRatedMovies(){
        _topRatedMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.value = movieUseCase.execute(MovieType.TOP_RATED)
        }
    }

    private fun getNowPlayingMovies(){
        _nowPlayingMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlayingMovies.value = movieUseCase.execute(MovieType.NOW_PLAYING)
        }
    }

    private fun getLatestMovies(){
        _latestMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _latestMovies.value = movieUseCase.execute(MovieType.LATEST)
        }
    }
}