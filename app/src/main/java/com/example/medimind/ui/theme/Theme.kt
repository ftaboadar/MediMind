package com.example.medimind.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MediMindColorScheme = lightColorScheme(
    primary = MediBlue,
    onPrimary = MediWhite,
    primaryContainer = MediLightBlue,
    onPrimaryContainer = MediDarkBlue,
    background = MediBackground,
    onBackground = MediDeepBlue,
    surface = MediWhite,
    onSurface = MediDeepBlue,
    outline = MediBorder,
)

@Composable
fun MediMindTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MediMindColorScheme,
        content = content
    )
}
