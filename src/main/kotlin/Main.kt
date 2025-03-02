import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import drawing.TotoroBasicShapes
import drawing.LeafHat

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
                val rainbowColors = listOf(
                    Color(0xFFFE90B3), // Baker-Miller pink
                    Color(0xFFFE6515), // Orange (Pantone)
                    Color(0xFFFDE773), // Jasmine
                    Color(0xFF94B758), // Olivine
                    Color(0xFF6A84B0), // Glaucous
                    Color(0xFF443F5D)  // English Violet
                )

                drawRect(
                    brush = Brush.verticalGradient(
                        colors = rainbowColors,
                        startY = 0f,
                        endY = size.height
                    )
                )
            }

            // Totoro
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            ) {
                TotoroBasicShapes()
            }

            // Leaf hat
            Box(
                modifier = Modifier
                    .fillMaxSize(0.15f)
                    .offset(x = (-10).dp, y = (-160).dp)
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
