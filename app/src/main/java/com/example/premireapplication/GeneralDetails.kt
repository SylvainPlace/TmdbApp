package com.example.premireapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FirstTitle(title: String?) {
    if (title != null) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ImgWithSubtitle(path: String?, subtitle: String?) {
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
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun Overview(overview: String?) {
    if (overview != null) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = overview,
            modifier = Modifier.padding(bottom = 20.dp),
        )
    }
}

@Composable
fun Genres(genres: List<Genre>?) {
    if (!genres.isNullOrEmpty()) {
        Text(
            text = "Genres",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 20.dp)
        )
        for (genre in genres) {
            Text(
                text = genre.name
            )
        }
    }
}

@Composable
fun Note(note: Double?) {
    if (note != null) {
        Row(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text(text = "  Note ")
            LinearProgressIndicator(
                progress = (note / 10).toFloat(),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colors.surface
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
            Text(text = "  Note ")
            LinearProgressIndicator(
                progress = (popularity / 100).toFloat(),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colors.surface
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
        Text(
            text = "Casting",
            style = MaterialTheme.typography.h5,
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