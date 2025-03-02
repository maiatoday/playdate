package drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.random.Random

data class RainDrop(
    val x: Float,
    var y: Float,
    val length: Float,
    val speed: Float,
    val alpha: Float
)

@Composable
fun RainDrops(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF6A84B0),  // Glaucous blue
    dropCount: Int = 100
) {
    var canvasWidth by remember { mutableStateOf(0f) }
    var canvasHeight by remember { mutableStateOf(0f) }

    var raindrops by remember {
        mutableStateOf(List(dropCount) {
            createRaindrop(Random.nextFloat() * 1000f, 1000f)
        })
    }

    LaunchedEffect(Unit) {
        while (true) {
            raindrops = raindrops.map { drop ->
                drop.y += drop.speed
                if (drop.y > canvasHeight) {
                    createRaindrop(0f, canvasWidth)
                } else {
                    drop
                }
            }
            kotlinx.coroutines.delay(16) // ~60 FPS
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        // Update canvas dimensions
        canvasWidth = size.width
        canvasHeight = size.height

        raindrops.forEach { drop ->
            drawLine(
                color = color.copy(alpha = drop.alpha),
                start = Offset(drop.x, drop.y),
                end = Offset(drop.x, drop.y + drop.length),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
        }
    }
}

private fun createRaindrop(startY: Float, width: Float): RainDrop {
    return RainDrop(
        x = Random.nextFloat() * width,
        y = startY - Random.nextFloat() * 500f,
        length = Random.nextFloat() * 30f + 20f,  // Longer drops (20-50px)
        speed = Random.nextFloat() * 15f + 20f,   // Faster drops (20-35px per frame)
        alpha = Random.nextFloat() * 0.4f + 0.3f  // More visible drops (0.3-0.7 alpha)
    )
}
