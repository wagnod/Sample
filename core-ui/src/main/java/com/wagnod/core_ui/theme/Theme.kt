package com.wagnod.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkBackgroundBrand,
    primaryVariant = darkBackgroundBlue,
    secondary = lightGraphicsSecondary,
    error = darkTextNegative,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = lightBackgroundBrand,
    primaryVariant = lightBackgroundBlue,
    secondary = darkGraphicsSecondary,
    error = lightTextNegative
)

@Composable
fun SampleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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