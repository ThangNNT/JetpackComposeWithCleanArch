package com.example.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.nnt.domain.base.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _searchMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val searchMovies: StateFlow<Result<MovieModels>> = _searchMovies

    init {
        search("spider")
    }

    fun search(keyword: String?){
        _searchMovies.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO){
            val result = searchMoviesUseCase.execute(keyword = keyword)
            _searchMovies.value = result
        }
    }
}