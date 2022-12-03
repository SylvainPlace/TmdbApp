package com.example.premireapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            personDetail?.let {
                FirstTitle(it.name)
                ImgWithSubtitle(it.profile_path, it.known_for_department)
                Biography(it.biography)
                LifeDate(it.birthday, it.deathday)
                Popularity(it.popularity)
                MovieCastOrCrew(
                    stringResource(R.string.cast_in),
                    it.credits.cast,
                    windowClass,
                    navController
                )
                MovieCastOrCrew(
                    stringResource(R.string.crew_in),
                    it.credits.crew,
                    windowClass,
                    navController
                )
            }
        }
    }
}

@Composable
fun Biography(biography: String?) {
    if (!biography.isNullOrEmpty()) {
        Column {
            Text(
                text = stringResource(R.string.biography),
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = biography,
                modifier = Modifier.padding(bottom = 20.dp),
            )
        }
    }
}

@Composable
fun LifeDate(birthday: String?, deathday: String?) {
    Row {
        if (birthday != null) {
            Text(text = stringResource(R.string.born_on) + " " + stringToDate(birthday))
            if (deathday != null) {
                Text(text = " " + stringResource(R.string.and) + " ")
            }
        }
        if (deathday != null) {
            Text(text = stringResource(R.string.deceased_on) + stringToDate(deathday))

        }
    }
}

@Composable
fun MovieCastOrCrew(
    initText: String,
    casting: List<TmdbMovie>?,
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
                Modifier.padding(bottom = 40.dp)
            ) {
                Text(text = initText)
            }
        }
        AnimatedVisibility(
            visible = display.value,
            enter = expandVertically() + slideInVertically(),
        ) {
            Column {
                Text(
                    text = initText,
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