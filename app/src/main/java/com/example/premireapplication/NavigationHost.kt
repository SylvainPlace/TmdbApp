package com.example.premireapplication

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument

@Composable
fun NavigationHost(
    navController: NavHostController,
    windowClass: WindowSizeClass,
    mainViewModel: MainViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    NavHost(
        navController = navController,
        startDestination = "profile",
    ) {
        composable("profile") {
            ScreenProfile(windowClass, navController)
        }
        composable("films") {
            val movies by mainViewModel.movies.collectAsState()
            ScreenFilms(windowClass, navController, movies)
        }
        composable("series") {
            val series by mainViewModel.series.collectAsState()
            ScreenSeries(windowClass, navController, series)
        }
        composable("people") {
            val people by mainViewModel.people.collectAsState()
            ScreenActeurs(windowClass, navController, people)
        }
        composable(
            "filmDetail/{idMovie}",
            arguments = listOf(navArgument("idMovie") { type = NavType.IntType })
        ) {
            ScreenFilmsDetail(
                mainViewModel,
                backStackEntry?.arguments?.getInt("idMovie"),
                navController,
                windowClass
            )
        }
        composable(
            "serieDetail/{idSerie}",
            arguments = listOf(navArgument("idSerie") { type = NavType.IntType })
        ) {
            ScreenSerieDetail(
                mainViewModel,
                backStackEntry?.arguments?.getInt("idSerie"),
                navController,
                windowClass
            )
        }
        composable(
            "peopleDetail/{idPerson}",
            arguments = listOf(navArgument("idPerson") { type = NavType.IntType })
        ) {
            ScreenActeurDetail(
                mainViewModel,
                backStackEntry?.arguments?.getInt("idPerson"),
                navController,
                windowClass
            )
        }
        composable(
            "filmsSearch/{searchTerm}",
            arguments = listOf(navArgument("searchTerm") { type = NavType.StringType })
        ) {
            backStackEntry?.arguments?.getString("searchTerm")
                ?.let { it1 ->
                    mainViewModel.getFilmSearch(it1)
                    val movies by mainViewModel.searchMovies.collectAsState()
                    ScreenFilms(windowClass, navController, movies)
                }
        }
        composable(
            "seriesSearch/{searchTerm}",
            arguments = listOf(navArgument("searchTerm") { type = NavType.StringType })
        ) {
            backStackEntry?.arguments?.getString("searchTerm")
                ?.let { it1 ->
                    mainViewModel.getSerieSearch(it1)
                    val series by mainViewModel.searchSeries.collectAsState()
                    ScreenSeries(windowClass, navController, series)
                }
        }
        composable(
            "peopleSearch/{searchTerm}",
            arguments = listOf(navArgument("searchTerm") { type = NavType.StringType })
        ) {
            backStackEntry?.arguments?.getString("searchTerm")
                ?.let { it1 ->
                    mainViewModel.getPeopleSearch(it1)
                    val people by mainViewModel.searchPeople.collectAsState()
                    ScreenActeurs(windowClass, navController, people)
                }
        }
    }
}