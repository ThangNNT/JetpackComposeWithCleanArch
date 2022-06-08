package com.example.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnt.domain.base.Result
import com.nnt.domain.model.MovieModels
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.nnt.core.common.MovieCard
import com.nnt.jetpackcomposewithcleanarch.ui.theme.SearchHintColor
import com.nnt.jetpackcomposewithcleanarch.ui.theme.SearchInputBackgroundColor

@ExperimentalUnitApi
@Composable
fun SearchScreen(navController: NavController){
    val viewModel = hiltViewModel<SearchViewModel>()
    Column {
        SearchBar(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            viewModel.search(it)
        }
        SearchResultList(
            state = viewModel.searchMovies.collectAsState(Result.Empty),
            navController = navController
        )
    }
}

@ExperimentalUnitApi
@Composable
fun SearchResultList(state: State<Result<MovieModels>>, navController: NavController){
    when(val result = state.value){
        is Result.Empty -> {

        }
        is Result.Loading -> {

        }
        is Result.Success -> {
            result.data?.movies?.let { movies ->
                LazyVerticalGrid(columns = GridCells.Fixed(2)){
                    items(movies, key = { movie -> movie.id ?: 0 })
                    { movie ->
                        MovieCard(movie = movie, navigator = navController)
                    }
                }
            }
        }
        is Result.Error -> {

        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchHint: String = "Search movie",
    onSearch: (keyword: String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onSearch.invoke(it)
        },
        placeholder = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "search image",
                    colorFilter = ColorFilter.tint(SearchHintColor)
                )
                Text(text = searchHint, color = SearchHintColor)
            }
        },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(SearchInputBackgroundColor),
        colors = textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}