package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavRailLeft(navController: NavHostController) {
    val searchBool = remember { mutableStateOf(false) }
    NavigationRail(
        header = {
            ReturnArrow(navController, searchBool)
            AnimatedVisibility(
                visible = searchBool.value,
                enter = expandHorizontally(expandFrom = Alignment.Start) + expandVertically(),
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start) + shrinkVertically(),
            ) {
                Column {
                    ResearchField(150, navController)
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
            FloatingActionButton(
                onClick = { searchBool.value = !searchBool.value },
            ) {
                ResearchIcon(searchBool.value)
            }
        },
        content = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            NavBarItems.BarItems.forEach { navItem ->
                NavigationRailItem(
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
    )
}

@Composable
fun ReturnArrow(navController: NavHostController, searchBool: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = displayReturn(navController, searchBool),
        enter = slideInVertically(),
        exit = slideOutVertically() + shrinkVertically(),
    ) {
        IconButton(onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.height(25.dp)
            )
        }
    }
}