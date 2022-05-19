package com.nnt.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.usecase.GetMovieDetailUseCase
import com.nnt.navigator.MovieDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: GetMovieDetailUseCase, savedStateHandle: SavedStateHandle): ViewModel() {
    private val _movieDetail = MutableStateFlow<Result<MovieDetailModel>>(Result.Empty)
    val movieDetail: StateFlow<Result<MovieDetailModel>> = _movieDetail
    private var detailJob: Job? = null
    init {
        val id = savedStateHandle.get<Int>(MovieDetailArgs.MovieId.value)
        id?.let {
            getMovieDetail(it)
        }
    }
    fun getMovieDetail(movieId: Int){
        detailJob?.cancel()
        _movieDetail.value = Result.Loading
        detailJob = viewModelScope.launch(Dispatchers.IO) {
            val result = movieDetailUseCase.execute(movieId = movieId)
            _movieDetail.value = result
        }
    }
}