package com.example.premireapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val primaryColor = Color(0xff45607F)
val onPrimaryColor = Color(0xffffffff)
val primaryVariantColor = Color(0xff595F67)
val secondaryColor = Color(0xff595F67)
val onSecondaryColor = Color(0xffffffff)
val secondaryVariantColor = Color(0xff595F67)
val surfaceColor = Color(0xffD1E4FF)
val onSurfaceColor = Color(0xff001D35)
val backgroundColor = Color(0xffFEFBFD)
val onBackgroundColor = Color(0xff1B1C1D)
val errorColor = Color(0xffBA1A1A)


val primaryDarkColor = Color(0xffADC9EC)
val onPrimaryDarkColor = Color(0xff14324F)
val primaryVariantDarkColor = Color(0xffC2C7D0)
val secondaryDarkColor = Color(0xffC2C7D0)
val onSecondaryDarkColor = Color(0xff2B3138)
val secondaryVariantDarkColor = Color(0xff42474F)
val surfaceDarkColor = Color(0xff2D4966)
val onSurfaceDarkColor = Color(0xffD1E4FF)
val backgroundDarkColor = Color(0xff1B1C1D)
val onBackgroundDarkColor = Color(0xffE4E2E3)

private val LightColors = lightColorScheme(
    primary = primaryColor,
    tertiary = primaryVariantColor,
    onPrimary = onPrimaryColor,
    secondary = secondaryColor,
    onSecondary = onSecondaryColor,
    error = errorColor,
    onError = Color.White,
    surface = surfaceColor,
    onSurface = onSurfaceColor,
    background = backgroundColor,
    onBackground = onBackgroundColor,
)
private val DarkColors = darkColorScheme(
    primary = primaryDarkColor,
    tertiary = primaryVariantDarkColor,
    onPrimary = onPrimaryDarkColor,
    secondary = secondaryDarkColor,
    onSecondary = onSecondaryDarkColor,
    error = errorColor,
    onError = Color.White,
    surface = surfaceDarkColor,
    onSurface = onSurfaceDarkColor,
    background = backgroundDarkColor,
    onBackground = onBackgroundDarkColor,
)
@Composable
fun MyTheme(
    content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    if(isSystemInDarkTheme()){
        systemUiController.setSystemBarsColor(
            color = surfaceDarkColor
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = surfaceColor
        )
    }
    MaterialTheme(
        colorScheme  = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}