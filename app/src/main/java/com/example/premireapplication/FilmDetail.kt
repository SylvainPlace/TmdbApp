package com.example.premireapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController

@Composable
fun ScreenFilmsDetail(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavHostController,
    windowClass: WindowSizeClass
) {
    if (id != null) {
        viewModel.getFilmDetails(id)
        val movieDetails by viewModel.filmDetail.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            movieDetails?.let {
                FirstTitle(it.title)
                ImgWithSubtitle(it.poster_path, it.tagline)
                Overview(it.overview)
                ReleaseDate(it.release_date)
                Genres(it.genres)
                Note(it.vote_average)
                Casting(it.credits.cast, windowClass, navController)
            }
        }
    }
}

@Composable
fun ReleaseDate(date: String?) {
    if (!date.isNullOrEmpty()) {
        Text(text = stringResource(R.string.released_on) + " " + stringToDate(date))
    }
}