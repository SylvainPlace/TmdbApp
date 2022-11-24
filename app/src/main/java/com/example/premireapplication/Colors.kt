package com.example.premireapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
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

private val LightColors = lightColors(
    primary = primaryColor,
    primaryVariant = primaryVariantColor,
    onPrimary = onPrimaryColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryVariantColor,
    onSecondary = onSecondaryColor,
    error = errorColor,
    onError = Color.White,
    surface = surfaceColor,
    onSurface = onSurfaceColor,
    background = backgroundColor,
    onBackground = onBackgroundColor,
)
private val DarkColors = darkColors(
    primary = primaryDarkColor,
    primaryVariant = primaryVariantDarkColor,
    onPrimary = onPrimaryDarkColor,
    secondary = secondaryDarkColor,
    secondaryVariant = secondaryVariantDarkColor,
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
            color = onSurfaceDarkColor
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content
    )
}