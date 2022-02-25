package com.nnt.moviedetail.navigator

import android.content.Context
import android.content.Intent
import com.nnt.moviedetail.MovieDetailActivity
import com.nnt.navigator.MovieDetailActivityNavigator

class MovieDetailActivityNavigatorImpl: MovieDetailActivityNavigator {

    override fun newIntent(context: Context, movieId: Int): Intent {
        return Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_ID, movieId)
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}