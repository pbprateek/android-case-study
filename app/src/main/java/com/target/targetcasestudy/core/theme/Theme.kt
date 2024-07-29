package com.target.targetcasestudy.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = colorPrimary,
    onPrimary = Color.White,
    secondary = colorPrimaryDark,
    surfaceContainer = Color.Black,
    background = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}