package com.example.premireapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController

@Composable
fun ScreenProfile(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageNom()
                Descrip(navController)
            }
        }
        else -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ImageNom()
                Descrip(navController)

            }
        }
    }
}

@Composable
fun ImageNom() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MonImage()
        Nom("Sylvain Place")
    }
}

@Composable
fun Descrip(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Description("Ingénieur ISIS FIE4")
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            IconText(R.drawable.email, "sylvain@place.fr")
            IconText(R.drawable.linkedin, "sylvainplc")
        }
        ButtonCustom(navController)
    }
}


@Composable
fun MonImage() {
    Image(
        painterResource(id = R.drawable.photomoicarre),
        contentDescription = "Photo de Sylvain Place",
        modifier = Modifier
            .size(250.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Nom(contenu: String) {
    Text(
        text = contenu,
        style = MaterialTheme.typography.h2,
        fontSize = 12.em
    )
}

@Composable
fun Description(contenu: String) {
    Text(
        text = contenu,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun IconText(icon: Int, text: String) {
    Row {
        Image(
            painterResource(id = icon),
            contentDescription = "email",
            modifier = Modifier
                .size(25.dp)
                .padding(5.dp, 0.dp)
        )
        Text(
            text = text,
        )
    }
}

@Composable
fun ButtonCustom(navController: NavController) {
    Button(onClick = { navController.navigate("films") }) {
        Text(text = "Démarrer")
    }
}