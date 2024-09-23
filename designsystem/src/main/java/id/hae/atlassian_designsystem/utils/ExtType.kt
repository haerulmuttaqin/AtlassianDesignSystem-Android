package id.hae.atlassian_designsystem.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import id.hae.atlassian_designsystem.foundation.Typography
import id.hae.atlassian_designsystem.foundation.createTypography

@SuppressLint("DiscouragedApi")
@Composable
fun rememberTypography(context: Context): Typography = remember {
    val resources = context.resources
    val packageName = context.packageName

    val book = resources.getIdentifier("circular20_book", "font", packageName)
    val medium = resources.getIdentifier("circular20_medium", "font", packageName)
    val bold = resources.getIdentifier("circular20_bold", "font", packageName)

    if (book == 0 || medium == 0 || bold == 0) {
        return@remember createTypography()
    }

    return@remember createTypography(
        defaultFontFamily = FontFamily(
            Font(book, FontWeight.Normal),
            Font(medium, FontWeight.Medium),
            Font(bold, FontWeight.Bold),
        ),
    )
}