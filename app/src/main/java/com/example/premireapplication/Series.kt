package com.example.premireapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ScreenSeries(
    windowClass: WindowSizeClass,
    navController: NavHostController,
    series: List<TmdbSerie>
) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp),
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(series.size) { index ->
                    CardSerie(series[index], navController)
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
                items(series.size) { index ->
                    CardSerie(series[index], navController)
                }
            }
        }
    }
}

@Composable
fun CardSerie(tmdbSerie: TmdbSerie, navController: NavHostController) {
    val text = if (!tmdbSerie.first_air_date.isNullOrBlank()) {
        stringToDate(tmdbSerie.first_air_date)
    } else {
        "No date"
    }
    GeneralCard(
        route = "serieDetail/" + tmdbSerie.id,
        imgPath = tmdbSerie.poster_path,
        firstText = tmdbSerie.name,
        secondText = text,
        navController = navController
    )
}