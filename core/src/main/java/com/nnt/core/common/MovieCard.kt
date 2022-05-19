package com.nnt.core.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.nnt.core.R
import com.nnt.domain.model.MovieModel
import com.nnt.navigator.Destinations
import com.nnt.utils.buildImageUrl

@ExperimentalUnitApi
@Composable
fun MovieCard(movie: MovieModel, navigator: NavController){
    Card(
        Modifier
            .padding(10.dp, 0.dp)
            .selectable(selected = true, onClick = {
                movie.id?.let {
                    navigator.navigate(route = Destinations.MovieDetail.createRoute(it, movie.name.orEmpty()))
                }
            }), elevation = 2.dp, shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier
            .width(120.dp)) {
            Image(modifier = Modifier
                .height(160.dp)
                .width(120.dp),
                painter = rememberImagePainter(buildImageUrl(movie.posterPath), builder = {
                    error(R.drawable.no_poster_available)
                }),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            RateBar(ratePercent = (movie.voteAverage?:0).toFloat()/20)
            Text(
                text = movie.name ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = TextUnit(16f, TextUnitType.Sp),
                modifier = Modifier.padding(6.dp, 4.dp)
            )
        }
    }
}

@Preview
@ExperimentalUnitApi
@Composable
fun HorizontalMovieCard(@PreviewParameter(MovieModelProvider::class) movie: MovieModel, navigator: NavController = rememberNavController()){
    Card(
        Modifier
            .fillMaxWidth(1f)
            .padding(10.dp, 0.dp)
            .selectable(selected = true, onClick = {
                movie.id?.let {
                    navigator.navigate(route = Destinations.MovieDetail.createRoute(it, movieName = movie.name.orEmpty()))
                }
            }), elevation = 2.dp, shape = RoundedCornerShape(6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp, 0.dp)) {
            Image(
                modifier = Modifier
                    .height(160.dp)
                    .width(120.dp),
                painter = rememberImagePainter(buildImageUrl(movie.posterPath), builder = {
                    error(R.drawable.no_poster_available)
                }), contentDescription = "", contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    text = movie.name ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    modifier = Modifier.padding(6.dp, 4.dp)
                )
                RateBar(ratePercent = (movie.voteAverage?:0).toFloat()/20)
            }
        }
    }
}

class MovieModelProvider : PreviewParameterProvider<MovieModel>{
    override val values: Sequence<MovieModel> = sequenceOf(MovieModel(1,"Kỷ băng hà 2","abc", "cde", 5.0, 0.1, 100))
}