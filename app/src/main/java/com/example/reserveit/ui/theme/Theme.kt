package com.example.reserveit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00796B),   // Teal Green
    secondary = Color(0xFF004D40), // Dark Teal Grey
    tertiary = Color(0xFF4CAF50)   // Light Green Accent
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF388E3C),   // Green for primary
    secondary = Color(0xFF00796B), // Teal Green for secondary
    tertiary = Color(0xFFAFB42B),  // Lime Green for tertiary

    /* Override default colors if needed
    background = Color(0xFFE0F2F1),
    surface = Color(0xFFD7CCC8),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ReserveItTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun LectureHallTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF018786),  // Slightly lighter teal
            secondary = Color(0xFF03DAC5),  // Aqua-like secondary
            background = Color(0xFF121212)  // Dark background
        ),
        content = content
    )
}
