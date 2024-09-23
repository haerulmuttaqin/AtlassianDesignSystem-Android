package id.hae.atlassian_designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import id.hae.atlassian_designsystem.foundation.LocalColors
import id.hae.atlassian_designsystem.foundation.LocalContentColor
import id.hae.atlassian_designsystem.foundation.LocalTypography
import id.hae.atlassian_designsystem.foundation.ProvideMergedTextStyle

@Composable
public fun AtlasKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LocalColors.current
    val typography = LocalTypography.current
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    MaterialTheme(
        colorScheme = colors.toMaterial3Colors(),
        typography = typography.toMaterial3Typography(),
    ) {
        CompositionLocalProvider(
            // Orbit
            LocalColors provides colors,
//            LocalContentEmphasis provides ContentEmphasis.Normal,
//            LocalShapes provides shapes,
            LocalTypography provides typography,
            // Foundation
            LocalContentColor provides colors.content.normal,
        ) {
            ProvideMergedTextStyle(typography.bodyNormal, content)
        }
    }
}

public object AtlasKitTheme {
//
//    public val shapes: Shapes
//        @Composable
//        @ReadOnlyComposable
//        get() = LocalShapes.current
//
//    public val elevations: ElevationLevels = ElevationLevels
}