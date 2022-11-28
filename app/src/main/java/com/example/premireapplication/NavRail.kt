package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            FloatingActionButton(
                onClick = { searchBool.value = !searchBool.value },
            ) {
                ResearchIcon(searchBool.value)
            }
        },
        content = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
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