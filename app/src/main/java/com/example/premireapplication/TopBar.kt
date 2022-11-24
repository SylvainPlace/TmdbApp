package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun AppTopBar(navController: NavHostController) {
    val searchBool = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TopAppBar(
        title = {
            AnimatedVisibility(
                visible = searchBool.value,
                enter = expandHorizontally(expandFrom = Alignment.End),
                exit = shrinkHorizontally(shrinkTowards = Alignment.End),
            ) {
                TextField(
                    modifier = Modifier.animateContentSize(),
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
    if (currentRoute == "filmDetail/{idMovie}" ||currentRoute == "peopleDetail/{idPerson}" || currentRoute == "filmsSearch/{searchTerm}") {
        searchBool.value = false
        return true
    }
    return false
}