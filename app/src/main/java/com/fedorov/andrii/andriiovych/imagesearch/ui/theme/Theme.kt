package com.fedorov.andrii.andriiovych.imagesearch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Color.Yellow,
    secondary = SecondaryBackground,
    background = Background

)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.Yellow,
    secondary = SecondaryBackground,
    background = Background


)

@Composable
fun ImageSearchTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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