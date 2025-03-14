package drawing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import kotlinx.coroutines.launch

@Composable
fun WobbleTotoro(
    modifier: Modifier = Modifier,
    bodyColor: Color = Color(0xFF808584)  // Default grey
) {
    var isWobbling by remember { mutableStateOf(false) }
    var wobbleDirection by remember { mutableStateOf(1f) }
    var clickPosition by remember { mutableStateOf<Offset?>(null) }
    val scope = rememberCoroutineScope()

    // Animation values
    val wobbleRotation by animateFloatAsState(
        targetValue = if (isWobbling) 8f * wobbleDirection else 0f,
        animationSpec = spring(
            dampingRatio = 0.2f,  // More bouncy
            stiffness = 200f      // Softer spring
        ),
        finishedListener = {
            isWobbling = false
            wobbleDirection *= -1f  // Alternate direction for next wobble
        }
    )

    Box(
        modifier = modifier
            .graphicsLayer {
                rotationZ = wobbleRotation
                transformOrigin = TransformOrigin(0.5f, 0.6f)  // Pivot at middle-bottom
            }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.type == PointerEventType.Press) {
                            clickPosition = event.changes.first().position
                            scope.launch {
                                isWobbling = true
                            }
                        }
                    }
                }
            }
    ) {
        TotoroBasicShapes(
            bodyColor = bodyColor,
            mouseClickPosition = clickPosition
        )
    }
}
