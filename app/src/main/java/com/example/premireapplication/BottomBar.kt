package com.example.premireapplication

import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BarItem(
    val title: Int,
    val image: Int,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = R.string.menu_movie,
            image = R.drawable.movie,
            route = "films"
        ),
        BarItem(
            title = R.string.menu_tv,
            image = R.drawable.tv,
            route = "series"
        ),
        BarItem(
            title = R.string.menu_person,
            image = R.drawable.actor,
            route = "people"
        )
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar{
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
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
                        contentDescription = stringResource(navItem.title),
                        modifier = Modifier.height(25.dp)
                    )
                },
                label = {
                    Text(text = stringResource(navItem.title))
                },
            )
        }
    }
}