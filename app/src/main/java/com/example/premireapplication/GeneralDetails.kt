package com.example.premireapplication

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FirstTitle(title: String?) {
    if (title != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ImgWithSubtitle(path: String?, subtitle: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (path != null) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w780$path",
                modifier = Modifier.defaultMinSize(minHeight = 420.dp),
                contentDescription = null,
                alignment = Alignment.Center,
            )
        }
        if (subtitle != null) {
            Text(
                text = subtitle,
                modifier = Modifier.padding(bottom = 40.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun Overview(overview: String?) {
    if (overview != null) {
        Column {
            Text(
                text = "Description",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = overview,
                modifier = Modifier.padding(bottom = 20.dp),
            )
        }
    }
}

@Composable
fun Genres(genres: List<Genre>?) {
    if (!genres.isNullOrEmpty()) {
        Column {
            Text(
                text = "Genres",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 20.dp)
            )
            for (genre in genres) {
                Text(
                    text = "- " + genre.name
                )
            }
        }
    }
}

@Composable
fun Note(note: Double?) {
    if (note != null) {
        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text(text = "Note ")
            LinearProgressIndicator(
                progress = (note / 10).toFloat(),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.surface
            )
            Text(text = "  $note")
        }
    }
}

@Composable
fun Popularity(popularity: Double?) {
    if (popularity != null) {
        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text(text = "Popularité ")
            LinearProgressIndicator(
                progress = (popularity / 100).toFloat(),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.surface
            )
            Text(text = "  $popularity")
        }
    }
}

@Composable
fun Casting(
    casting: List<TmdbPerson>?,
    windowClass: WindowSizeClass,
    navController: NavHostController,
) {
    if (!casting.isNullOrEmpty()) {
        val display = remember { mutableStateOf(false) }
        AnimatedVisibility(
            visible = !display.value,
            exit = shrinkVertically(),
        ) {
            Button(
                onClick = { display.value = !display.value },
                Modifier.padding(bottom = 80.dp),
            ) {
                Text(text = "Casting")
            }
        }
        AnimatedVisibility(
            visible = display.value,
            enter = expandVertically() + slideInVertically(),
        ) {
            Column {
                Text(
                    text = "Casting",
                    style = MaterialTheme.typography.headlineSmall,
                )
                FlowRow(
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                ) {
                    val itemSize: Dp = when (windowClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            (LocalConfiguration.current.screenWidthDp.dp / 2)
                        }
                        else -> {
                            (LocalConfiguration.current.screenHeightDp.dp / 2)
                        }
                    }
                    for (person in casting) {
                        Row(
                            modifier = Modifier
                                .height(350.dp)
                                .size(itemSize)
                        ) {
                            CardPeople(person, navController)
                        }
                    }
                }
            }
        }
    }
}