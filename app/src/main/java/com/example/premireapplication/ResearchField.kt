package com.example.premireapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun ResearchField(size: Int, navController: NavHostController) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    TextField(
        modifier = Modifier
            .animateContentSize()
            .width(size.dp),
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
                when (backStackEntry?.destination?.route) {
                    "films" -> navController.navigate("filmsSearch/" + text.text)
                    "filmDetail/{idMovie}" -> navController.navigate("filmsSearch/" + text.text)
                    "filmsSearch/{searchTerm}" -> navController.navigate("filmsSearch/" + text.text)
                    "series" -> navController.navigate("seriesSearch/" + text.text)
                    "serieDetail/{idSerie}" -> navController.navigate("seriesSearch/" + text.text)
                    "seriesSearch/{searchTerm}" -> navController.navigate("seriesSearch/" + text.text)
                    "people" -> navController.navigate("peopleSearch/" + text.text)
                    "peopleDetail/{idPerson}" -> navController.navigate("peopleSearch/" + text.text)
                    "peopleSearch/{searchTerm}" -> navController.navigate("peopleSearch/" + text.text)
                    else -> navController.navigate("filmsSearch/" + text.text)
                }
            }
        )
    )
}

@Composable
fun ResearchIcon(searchBool: Boolean) {
    if (searchBool) {
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