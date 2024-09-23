package id.hae.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.hae.atlassian_designsystem.AtlasKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            val iconId =
                if (darkTheme) id.hae.atlassian_designsystem.R.drawable.sun else id.hae.atlassian_designsystem.R.drawable.moon
            val rotationAngle by animateFloatAsState(
                targetValue = if (darkTheme) 180f else 0f,
                label = "rotationAngle"
            )
            AtlasKitTheme(
                darkTheme = darkTheme
            ) {
                Scaffold {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { darkTheme = !darkTheme }) {
                                Icon(
                                    painter = painterResource(id = iconId),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground,
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