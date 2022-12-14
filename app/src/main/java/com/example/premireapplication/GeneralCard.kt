package com.example.premireapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun GeneralCard(
    route: String,
    imgPath: String?,
    firstText: String,
    secondText: String?,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { navController.navigate(route) },
    ) {
        Column {
            if (imgPath != null) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$imgPath",
                    contentDescription = firstText
                )
            }
            Text(
                text = firstText,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .width(180.dp),
            )
            if (secondText != null) {
                Text(
                    text = secondText
                )
            }

        }
    }
}
