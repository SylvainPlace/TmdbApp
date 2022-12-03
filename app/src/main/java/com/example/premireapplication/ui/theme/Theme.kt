package com.example.premireapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
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
private val DarkColorPalette = darkColorScheme(
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
fun PremièreApplicationTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colorScheme = colors,
            shapes = Shapes,
            content = content
    )
}