package com.example.premireapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

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
    var text = if (tmdbMovie.release_date != null && tmdbMovie.release_date != "") {
        val date = LocalDate.parse(tmdbMovie.release_date)
        date.dayOfMonth.toString() + " " + date.month.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        ) + " " + date.year.toString()
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

