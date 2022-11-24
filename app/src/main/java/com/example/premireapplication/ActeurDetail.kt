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
fun ScreenActeurDetail(
    viewModel: MainViewModel,
    id: Int?,
    navController: NavHostController,
    windowClass: WindowSizeClass
) {
    if (id != null) {
        viewModel.getPersonDetails(id)
        val personDetail by viewModel.personDetail.collectAsState()
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            personDetail?.let {
                Text(
                    text = it.name,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                )
                if (it.profile_path != null) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w780" + it.profile_path,
                        contentDescription = null,
                        alignment = Alignment.Center,
                    )
                }
                Text(
                    text = it.known_for_department,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 40.dp)
                )
                if (it.biography != null && it.biography != "") {
                    Text(
                        text = "Biographie",
                        fontSize = 20.sp,
                    )
                    Text(
                        text = it.biography,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }

                Row {
                    if (it.birthday != null) {
                        val bDate = LocalDate.parse(it.birthday)
                        Text(
                            text = "Né le " + bDate.dayOfMonth.toString() + " " + bDate.month.getDisplayName(
                                TextStyle.SHORT,
                                Locale.getDefault()
                            ) + " " + bDate.year.toString()
                        )
                        if (it.deathday != null) {
                            Text(text = " et ")
                        }
                    }

                    if (it.deathday != null) {
                        val dDate = LocalDate.parse(it.deathday)
                        Text(
                            text = "décédé le " + dDate.dayOfMonth.toString() + " " + dDate.month.getDisplayName(
                                TextStyle.SHORT,
                                Locale.getDefault()
                            ) + " " + dDate.year.toString()
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    Text(text = "  Note ")
                    LinearProgressIndicator(
                        progress = (it.popularity / 100).toFloat(),
                        modifier = Modifier.padding(top = 10.dp),
                        color = MaterialTheme.colors.surface
                    )
                    Text(text = "  " + it.popularity.toString())

                }
                Text(
                    text = "Joue dans",
                    fontSize = 20.sp,
                )

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
                    for (movie in casting) {
                        Row(
                            modifier = Modifier
                                .height(350.dp)
                                .size(itemSize)
                        ) {
                            CardMovie(movie, navController)
                        }
                    }
                }

            }
        }
    }
}

