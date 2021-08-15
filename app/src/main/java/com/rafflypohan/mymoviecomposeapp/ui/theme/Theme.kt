package com.rafflypohan.mymoviecomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue500,
    primaryVariant = Black300,
    onPrimary = Black500
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Gray500,
    onPrimary = White,
    surface = Black500,
    onSurface = Gray200

)

@Composable
fun MyMovieComposeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}