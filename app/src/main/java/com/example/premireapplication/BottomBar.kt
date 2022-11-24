package com.example.premireapplication

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BarItem(
    val title: String,
    val image: Int,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Films",
            image = R.drawable.movie,
            route = "films"
        ),
        BarItem(
            title = "Series",
            image = R.drawable.tv,
            route = "series"
        ),
        BarItem(
            title = "Acteurs",
            image = R.drawable.actor,
            route = "people"
        )
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation{
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = false
                        restoreState = false
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.image),
                        contentDescription = navItem.title,
                        modifier = Modifier.height(25.dp)
                    )
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}