package id.hae.atlassian_designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import id.hae.atlassian_designsystem.foundation.Colors
import id.hae.atlassian_designsystem.foundation.ContentEmphasis
import id.hae.atlassian_designsystem.foundation.ElevationLevels
import id.hae.atlassian_designsystem.foundation.LocalColors
import id.hae.atlassian_designsystem.foundation.LocalContentColor
import id.hae.atlassian_designsystem.foundation.LocalContentEmphasis
import id.hae.atlassian_designsystem.foundation.LocalShapes
import id.hae.atlassian_designsystem.foundation.LocalTypography
import id.hae.atlassian_designsystem.foundation.ProvideMergedTextStyle
import id.hae.atlassian_designsystem.foundation.Shapes
import id.hae.atlassian_designsystem.foundation.Typography

@Composable
public fun AtlasKitTheme(
    colors: Colors = AtlasKitTheme.colors,
    typography: Typography = AtlasKitTheme.typography,
    shapes: Shapes = AtlasKitTheme.shapes,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = colors.toMaterial3Colors(),
        typography = typography.toMaterial3Typography(),
    ) {
        CompositionLocalProvider(
            LocalColors provides colors,
            LocalContentEmphasis provides ContentEmphasis.Normal,
            LocalShapes provides shapes,
            LocalTypography provides typography,
            // Foundation
            LocalContentColor provides colors.content.normal,
        ) {
            ProvideMergedTextStyle(typography.bodyNormal, content)
        }
    }
}

public object AtlasKitTheme {
    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    public val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    public val elevations: ElevationLevels = ElevationLevels
}