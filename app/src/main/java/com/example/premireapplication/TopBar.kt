package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppTopBar(navController: NavHostController) {
    val searchBool = remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = searchBool.value,
                enter = expandHorizontally(expandFrom = Alignment.End),
                exit = shrinkHorizontally(shrinkTowards = Alignment.End),
            ) {
                ResearchField(250, navController)
            }
            AnimatedVisibility(
                visible = !searchBool.value,
                enter = expandHorizontally(expandFrom = Alignment.End),
                exit = shrinkHorizontally(shrinkTowards = Alignment.End),
            ) {
                Text(
                    text = "Fav'App",
                    color = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {
            IconButton(
                onClick = { searchBool.value = !searchBool.value },
            ) {
                ResearchIcon(searchBool.value)
            }
        },
        navigationIcon = {
            ReturnArrow(navController, searchBool)
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White,
        elevation = 12.dp
    )
}

@Composable
fun displayReturn(navController: NavHostController, searchBool: MutableState<Boolean>): Boolean {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    if (currentRoute == "filmDetail/{idMovie}" || currentRoute == "peopleDetail/{idPerson}" || currentRoute == "serieDetail/{idSerie}" || currentRoute == "filmsSearch/{searchTerm}"|| currentRoute == "seriesSearch/{searchTerm}"|| currentRoute == "peopleSearch/{searchTerm}") {
        searchBool.value = false
        return true
    }
    return false
}