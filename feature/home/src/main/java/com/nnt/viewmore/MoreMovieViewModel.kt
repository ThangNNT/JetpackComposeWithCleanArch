package com.nnt.viewmore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.nnt.domain.base.Result
import com.nnt.domain.usecase.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MoreMovieViewModel @Inject constructor(val getMovieUseCase: GetMovieUseCase): ViewModel() {

    private val _moviesState = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val moviesState: StateFlow<Result<MovieModels>> = _moviesState

    fun getMovies(type: MovieType?, page: Int){
        _moviesState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _moviesState.value = getMovieUseCase.execute(type?: MovieType.POPULAR, page)
        }
    }
}