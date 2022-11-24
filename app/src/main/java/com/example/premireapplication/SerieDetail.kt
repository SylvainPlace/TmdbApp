package com.example.premireapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@Composable
fun ScreenSerieDetail(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavHostController,
    windowClass: WindowSizeClass
) {
    if (id != null) {
        viewModel.getSerieDetails(id)
        val serieDetails by viewModel.serieDetail.collectAsState()
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            serieDetails?.let {
                Text(
                    text = it.name,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                )
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w780" + it.poster_path,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.defaultMinSize(minHeight = 420.dp)
                )
                Text(
                    text = it.tagline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 40.dp)
                )
                Text(
                    text = "Description",
                    fontSize = 20.sp,
                )
                Text(text = it.overview,
                    modifier = Modifier.padding(bottom = 20.dp))
                val date = LocalDate.parse(it.first_air_date)
                Text(
                    text = "Premier épisode le " + date.dayOfMonth.toString() + " " + date.month.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    ) + " " + date.year.toString()

                )
                Text(
                    text = "Genres",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top=20.dp)
                )
                val genres = it.genres
                for(genre in genres){
                    Text(
                        text= genre.name
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 20.dp)) {
                    Text(text = "  Note ")
                    LinearProgressIndicator(
                        progress = (it.vote_average / 10).toFloat(),
                        modifier = Modifier.padding(top = 10.dp),
                        color = MaterialTheme.colors.surface
                    )
                    Text(text = "  " + it.vote_average.toString())

                }
                Text(text = "Casting",
                    fontSize = 20.sp,)
                val casting = it.credits.cast
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
                        Row(modifier = Modifier.height(350.dp).size(itemSize)){
                            CardPeople(person, navController)
                        }

                    }
                }

            }
        }
    }
}

