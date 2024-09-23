package id.hae.catalog.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import id.hae.atlassian_designsystem.AtlasKitTheme
import id.hae.atlassian_designsystem.foundation.darkColors
import id.hae.atlassian_designsystem.foundation.lightColors
import id.hae.atlassian_designsystem.utils.CirclePath
import id.hae.atlassian_designsystem.utils.rememberTypography

@Composable
internal fun AppTheme(
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
        val revealSize = remember { androidx.compose.animation.core.Animatable(1f) }
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
            AtlasKitTheme(
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