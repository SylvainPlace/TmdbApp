package com.example.premireapplication

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlin.math.roundToInt

@Composable
fun ScreenActeurs(
    windowClass: WindowSizeClass,
    navController: NavHostController,
    people: List<TmdbPerson>,
) {
    var offsetX by remember { mutableStateOf(0f) }
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 55.dp)
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            if (delta > 0) {
                                offsetX += delta
                            }
                        },
                        onDragStopped = {
                            if (offsetX > 100f) {
                                navController.navigate("series")
                            } else {
                                offsetX = 0f
                            }
                        }
                    ),
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
    GeneralCard(
        route = "peopleDetail/" + tmdbPerson.id,
        imgPath = tmdbPerson.profile_path,
        firstText = tmdbPerson.name,
        secondText = tmdbPerson.known_for_department,
        navController = navController
    )
}
