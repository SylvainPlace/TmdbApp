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

@Composable
fun ScreenActeurs(
    windowClass: WindowSizeClass,
    navController: NavHostController,
    people: List<TmdbPerson>,) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp),
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(people.size) { index ->
                    CardPeople(people[index], navController)
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
                items(people.size) { index ->
                    CardPeople(people[index], navController)
                }

            }
        }
    }
}

@Composable
fun CardPeople(tmdbPerson: TmdbPerson, navController: NavHostController) {
    GeneralCard(route = "peopleDetail/" + tmdbPerson.id, imgPath = tmdbPerson.profile_path, firstText = tmdbPerson.name, secondText = tmdbPerson.known_for_department, navController = navController)
}
