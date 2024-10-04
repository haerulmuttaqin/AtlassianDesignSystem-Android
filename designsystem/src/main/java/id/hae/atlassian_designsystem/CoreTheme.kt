package id.hae.atlassian_designsystem

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
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
import id.hae.atlassian_designsystem.foundation.darkColors
import id.hae.atlassian_designsystem.foundation.lightColors
import id.hae.atlassian_designsystem.utils.CirclePath
import id.hae.atlassian_designsystem.utils.rememberTypography

// this is core teme

@Composable
fun AtlasKitTheme2() {
    Column {
        // this is column
    }
}

@Composable
fun AtlasKitTheme(
    darkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    content: @Composable (onToggleTheme: (offset: Offset) -> Unit) -> Unit,
) {
    var animationOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    AnimatedContent(
        targetState = darkTheme,
        modifier = Modifier.fillMaxSize(),
        transitionSpec = {
            fadeIn(
                initialAlpha = 0f,
                animationSpec = tween(100),
            ) togetherWith fadeOut(
                targetAlpha = .9f,
                animationSpec = tween(800),
            )
        },
        label = "AppThemeChange",
    ) { currentTheme ->
        val revealSize = remember { Animatable(1f) }
        LaunchedEffect(Unit) {
            println(currentTheme)
            if (animationOffset.x > 0f) {
                revealSize.snapTo(0f)
                revealSize.animateTo(1f, animationSpec = tween(800))
            } else {
                revealSize.snapTo(1f)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CirclePath(revealSize.value, animationOffset)),
        ) {
            CoreTheme(
                typography = rememberTypography(LocalContext.current),
                colors = if (currentTheme) lightColors() else darkColors(),
                content = {
                    content { offset ->
                        animationOffset = offset
                        onThemeToggle(currentTheme)
                    }
                }
            )
        }
    }
}

@Composable
public fun CoreTheme(
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
