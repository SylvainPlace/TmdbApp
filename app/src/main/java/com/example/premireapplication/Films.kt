package com.example.premireapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ScreenFilms(
    windowClass: WindowSizeClass,
    navController: NavHostController,
    movies: List<TmdbMovie>
) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp),
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(movies.size) { index ->
                    CardMovie(movies[index], navController)
                }
            }
        }
        else -> {
            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp),
                rows = GridCells.Adaptive(200.dp)
            ) {
                items(movies.size) { index ->
                    CardMovie(movies[index], navController)
                }

            }
        }
    }
}

@Composable
fun CardMovie(tmdbMovie: TmdbMovie, navController: NavHostController) {
    val text = if (!tmdbMovie.release_date.isNullOrBlank()) {
        stringToDate(tmdbMovie.release_date)
    } else {
        "No date"
    }
    GeneralCard(
        route = "filmDetail/" + tmdbMovie.id,
        imgPath = tmdbMovie.poster_path,
        firstText = tmdbMovie.title,
        secondText = text,
        navController = navController
    )
}