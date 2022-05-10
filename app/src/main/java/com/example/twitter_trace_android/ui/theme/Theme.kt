package com.example.twitter_trace_android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightBlue200,
    primaryVariant = LightBlue200,
    secondary = LightBlue200,

    background = Black200,
    surface = Black200,
    onPrimary = LightBlue200,
    onSecondary = LightBlue200,
    onBackground = Black200,
    onSurface = Black200,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Black200,
    secondary = Teal200,

    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun TwitterTraceAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
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