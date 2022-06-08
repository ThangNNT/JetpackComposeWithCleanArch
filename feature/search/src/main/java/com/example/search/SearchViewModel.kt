package com.example.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnt.domain.model.MovieModels
import com.nnt.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.nnt.domain.base.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _searchMovies = MutableStateFlow<Result<MovieModels>>(Result.Empty)
    val searchMovies: StateFlow<Result<MovieModels>> = _searchMovies

    private var searchJob: Job? = null

    fun search(keyword: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch{
            delay(1000)
            if(keyword.isNullOrEmpty()){
                _searchMovies.value = Result.Empty
                return@launch
            }
            _searchMovies.value = Result.Loading
            withContext(Dispatchers.IO){
                val result = searchMoviesUseCase.execute(keyword = keyword)
                _searchMovies.value = result
            }

        }
    }
}