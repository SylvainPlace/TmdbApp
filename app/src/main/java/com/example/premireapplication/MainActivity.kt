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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
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
            Row {
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
    if (currentRoute == "profile" || currentRoute == "peopleDetail/{idPerson}" || currentRoute == "serieDetail/{idSerie}" || currentRoute == "filmDetail/{idMovie}") {
        return false
    }
    return true
}