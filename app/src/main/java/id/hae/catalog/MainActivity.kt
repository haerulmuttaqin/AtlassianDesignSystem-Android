package id.hae.catalog

import android.app.UiModeManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import id.hae.atlassian_designsystem.AtlasKitTheme
import id.hae.catalog.ui.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var darkTheme by rememberSaveable { mutableStateOf<Boolean?>(null) }
            val isDarkTheme = darkTheme ?: isSystemInDarkTheme()
            val iconId =
                if (isDarkTheme) id.hae.atlassian_designsystem.R.drawable.sun else id.hae.atlassian_designsystem.R.drawable.moon
            val rotationAngle by animateFloatAsState(
                targetValue = if (isDarkTheme) 180f else 0f,
                label = "rotationAngle"
            )
            LaunchedEffect(key1 = true, block = {
                println("dark = $darkTheme")
            })
            AppTheme(
                darkTheme = isDarkTheme,
                onThemeToggle = { isDarkThemeToggle ->
                    darkTheme = !isDarkThemeToggle
                    this.setUiMode(isDarkThemeToggle)
                },
            ) {
                Scaffold {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = AtlasKitTheme.colors.surface.main),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { darkTheme = darkTheme?.not() }) {
                                Icon(
                                    painter = painterResource(id = iconId),
                                    contentDescription = null,
                                    tint = AtlasKitTheme.colors.content.normal,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .graphicsLayer(rotationZ = rotationAngle),
                                )
                            }
                        }
                        Greeting("Android")
                    }
                }
            }
        }
    }

    private fun setUiMode(isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val uiModeManager = getSystemService<UiModeManager>()!!
            uiModeManager.setApplicationNightMode(
                if (isLight) UiModeManager.MODE_NIGHT_NO else UiModeManager.MODE_NIGHT_YES,
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                if (isLight) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES,
            )
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { !isLight },
            navigationBarStyle = SystemBarStyle.auto(
                DefaultLightScrim,
                DefaultDarkScrim
            ) { !isLight },
        )
    }

    private val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
    private val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AtlasKitTheme {
        Greeting("Android")
    }
}