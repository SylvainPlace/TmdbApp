package com.example.premireapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    val navController = rememberNavController()

                    Screen(windowSizeClass, navController)

                }
            }
        }
    }
}


@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavHostController) {

    lightColors(primary = Color.Blue, surface = Color.Green)
    darkColors()
    val mainViewModel = MainViewModel()
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    AppTopBar(navController)
                },
                content = {
                    NavigationHost(
                        navController = navController,
                        windowClass = windowClass,
                        mainViewModel = mainViewModel
                    )
                },
                bottomBar = {
                    if (displayBottomBar(navController)) {
                        BottomNavigationBar(navController = navController)
                    }
                }
            )
        }
        else -> {
            Row{
                NavRailLeft(navController)
                NavigationHost(
                    navController = navController,
                    windowClass = windowClass,
                    mainViewModel = mainViewModel
                )

            }
        }
    }
}


@Composable
fun displayBottomBar(navController: NavHostController): Boolean {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    if (currentRoute == "profile" || currentRoute == "peopleDetail/{idPerson}" || currentRoute == "filmDetail/{idMovie}") {
        return false
    }
    return true
}


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
    }
}

