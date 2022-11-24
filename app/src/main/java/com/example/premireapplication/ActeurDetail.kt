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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            personDetail?.let {
                FirstTitle(it.name)
                ImgWithSubtitle(it.profile_path, it.known_for_department)
                Biography(it.biography)
                LifeDate(it.birthday,it.deathday)
                Popularity(it.popularity)
                MovieCastOrCrew("Joue dans",it.credits.cast,windowClass,navController)
                MovieCastOrCrew("Est dans l'équipe de",it.credits.crew,windowClass,navController)
            }
        }
    }
}

@Composable
fun Biography(biography: String?) {
    if (!biography.isNullOrEmpty()) {
        Text(
            text = "Biographie",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = biography,
            modifier = Modifier.padding(bottom = 20.dp),
        )
    }
}

@Composable
fun LifeDate(birthday: String?, deathday:String?) {
    Row {
        if (birthday != null) {
            Text(text = "Né le " + stringToDate(birthday))

            if (deathday != null) {
                Text(text = " et ")
            }
        }
        if (deathday != null) {
            Text(text = "décédé le " + stringToDate(deathday))

        }
    }
}
@Composable
fun MovieCastOrCrew(
    initText:String,
    casting: List<TmdbMovie>?,
    windowClass: WindowSizeClass,
    navController: NavHostController,
) {
    if (!casting.isNullOrEmpty()) {
        Text(
            text = initText,
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