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
import androidx.navigation.NavHostController

@Composable
fun ScreenSerieDetail(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavHostController,
    windowClass: WindowSizeClass
) {
    if (id != null) {
        viewModel.getSerieDetails(id)
        val serieDetails by viewModel.serieDetail.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            serieDetails?.let {
                FirstTitle(it.name)
                ImgWithSubtitle(it.poster_path, it.tagline)
                Overview(it.overview)
                AirDate(it.first_air_date)
                Genres(it.genres)
                Note(it.vote_average)
                Casting(it.credits.cast, windowClass, navController)
            }
        }
    }
}

@Composable
fun AirDate(date: String?) {
    if (!date.isNullOrEmpty()) {
        Text(text = "Premier épisode le " + stringToDate(date))
    }
}