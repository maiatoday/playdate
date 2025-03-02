package drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

private val leafGreen = Color(0xFF94B758)  // Olivine
private val leafShadow = Color(0xFF6A8442) // Darker Olivine
private val stemColor = Color(0xFF30333A)  // Onyx

@Composable
fun LeafHat(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val leafWidth = size.width * 0.8f
        val leafHeight = leafWidth * 0.6f

        // Create leaf gradient
        val leafGradient = Brush.radialGradient(
            colors = listOf(
                leafGreen.copy(alpha = 0.9f),
                leafShadow.copy(alpha = 0.7f)
            ),
            center = Offset(centerX + leafWidth * 0.2f, centerY - leafHeight * 0.2f),
            radius = leafWidth * 0.8f
        )

        // Draw leaf shape
        val leafPath = Path().apply {
            // Start at the stem point
            moveTo(centerX, centerY)

            // Right curve
            cubicTo(
                centerX + leafWidth * 0.4f, centerY - leafHeight * 0.2f,  // control point 1
                centerX + leafWidth * 0.5f, centerY - leafHeight * 0.8f,  // control point 2
                centerX, centerY - leafHeight                             // end point
            )

            // Left curve
            cubicTo(
                centerX - leafWidth * 0.5f, centerY - leafHeight * 0.8f,  // control point 1
                centerX - leafWidth * 0.4f, centerY - leafHeight * 0.2f,  // control point 2
                centerX, centerY                                          // end point
            )

            close()
        }

        // Draw leaf fill
        drawPath(
            path = leafPath,
            brush = leafGradient
        )

        // Draw leaf veins
        val veinPath = Path().apply {
            // Main vein
            moveTo(centerX, centerY)
            lineTo(centerX, centerY - leafHeight)

            // Side veins
            for (i in 1..3) {
                val y = centerY - (leafHeight * i / 4)
                val xOffset = (leafWidth * 0.3f) * (i / 4f)

                // Right vein
                moveTo(centerX, y)
                quadraticTo(
                    centerX + xOffset * 0.7f, y - leafHeight * 0.05f,
                    centerX + xOffset, y
                )

                // Left vein
                moveTo(centerX, y)
                quadraticTo(
                    centerX - xOffset * 0.7f, y - leafHeight * 0.05f,
                    centerX - xOffset, y
                )
            }
        }

        // Draw veins
        drawPath(
            path = veinPath,
            color = leafShadow.copy(alpha = 0.5f),
            style = Stroke(width = 2f)
        )

        // Draw outline
        drawPath(
            path = leafPath,
            color = stemColor,
            style = Stroke(width = 3f)
        )

        // Draw stem
        val stemLength = leafHeight * 0.2f
        drawLine(
            color = stemColor,
            start = Offset(centerX, centerY),
            end = Offset(centerX, centerY + stemLength),
            strokeWidth = 3f
        )
    }
}
