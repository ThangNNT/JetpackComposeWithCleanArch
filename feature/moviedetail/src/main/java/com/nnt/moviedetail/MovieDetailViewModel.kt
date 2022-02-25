package com.nnt.moviedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieDetailModel
import com.nnt.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: GetMovieDetailUseCase): ViewModel() {
    private val _movieDetail = MutableStateFlow<Result<MovieDetailModel>>(Result.Empty)
    val movieDetail: StateFlow<Result<MovieDetailModel>> = _movieDetail

    fun getMovieDetail(movieId: Int){
        _movieDetail.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = movieDetailUseCase.execute(movieId = movieId)
            _movieDetail.value = result
        }
    }
}