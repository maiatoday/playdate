import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import drawing.LeafHat
import drawing.RainDrops
import drawing.TextTest
import drawing.WobbleTotoro

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            // Rainbow background
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            ) {
                val stormyColors = listOf(
                    Color(0xFF1F2124), // Dark storm clouds
                    Color(0xFF2C2F35), // Dark grey with blue undertone
                    Color(0xFF3A3E47), // Medium grey
                    Color(0xFF4A4E58), // Lighter grey with blue undertone
                    Color(0xFF5C616D)  // Light grey with violet undertone
                )

                drawRect(
                    brush = Brush.verticalGradient(
                        colors = stormyColors,
                        startY = 0f,
                        endY = size.height
                    )
                )
            }

            // Title text
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
            ) {
                TextTest()
            }

            // Rain effect
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0.5f)
            ) {
                RainDrops(
                    dropCount = 300,  // More raindrops for denser rain
                    color = Color(0xFFB0B5C0).copy(alpha = 0.7f)  // Light grey rain, more visible
                )
            }

            // Big grey Totoro (left)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = (-150).dp)
                    .zIndex(1f)
            ) {
                WobbleTotoro()
            }

            // Small blue Totoro (right)
            Box(
                modifier = Modifier
                    .fillMaxSize(0.5f)  // Half size
                    .offset(x = 250.dp, y = 250.dp)  // Move down to align bottoms
                    .zIndex(1f)
            ) {
                WobbleTotoro(
                    bodyColor = Color(0xFF6A84B0)  // Glaucous blue
                )
            }

            // Leaf hat for big Totoro
            Box(
                modifier = Modifier
                    .fillMaxSize(0.15f)
                    .offset(x = (-160).dp, y = (-160).dp)
                    .graphicsLayer(
                        rotationZ = -15f,
                        scaleX = 0.8f
                    )
                    .zIndex(2f)
            ) {
                LeafHat(
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Leaf hat for small Totoro
            Box(
                modifier = Modifier
                    .fillMaxSize(0.075f)  // Half size of original hat
                    .offset(x = 245.dp, y = 170.dp)  // Adjusted for new Totoro position
                    .graphicsLayer(
                        rotationZ = -15f,
                        scaleX = 0.8f
                    )
                    .zIndex(2f)
            ) {
                LeafHat(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Totoro Drawing"
    ) {
        App()
    }
}
