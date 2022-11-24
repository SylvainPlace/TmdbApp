package com.example.premireapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

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

    var text = if(tmdbSerie.first_air_date!=null&&tmdbSerie.first_air_date!=""){
        val date = LocalDate.parse(tmdbSerie.first_air_date)

        date.dayOfMonth.toString() + " " + date.month.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        ) + " " + date.year.toString()

    }else{
        "No date"
    }
    GeneralCard(route = "filmDetail/" + tmdbSerie.id, imgPath = tmdbSerie.poster_path, firstText = tmdbSerie.name, secondText = text, navController = navController)


}