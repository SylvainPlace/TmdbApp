package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    var text by remember { mutableStateOf(TextFieldValue("")) }
    NavigationRail(
        header = {
            ReturnArrow(navController, searchBool)

            AnimatedVisibility(
                visible = searchBool.value,
                enter = expandHorizontally(expandFrom = Alignment.Start)+ expandVertically(),
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start),
            ) {
                TextField(
                    modifier = Modifier.animateContentSize().width(150.dp).padding(bottom = 10.dp),
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    placeholder = { Text(text = "Rechercher") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            navController.navigate("filmsSearch/" + text.text)
                        }
                    )
                )
            }
            FloatingActionButton(
                onClick = {searchBool.value = !searchBool.value},
            ){
                if (searchBool.value) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.height(25.dp),
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        modifier = Modifier.height(25.dp)
                    )
                }
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
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
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
        exit = slideOutVertically()+ shrinkVertically(),
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