package id.hae.atlassian_designsystem.foundation

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

public val LocalSmallButtonScope: ProvidableCompositionLocal<Boolean> =
    staticCompositionLocalOf { false }
