package drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Brush

// Color palette for Totoro
private val defaultBodyColor = Color(0xFF808584)   // Battleship gray
private val bellyLightGray = Color(0xFFF0F2F3)  // Lighter Platinum
private val outlineColor = Color(0xFF30333A)    // Onyx
private val arrowColor = Color(0xFF443F5D)      // English Violet

@Composable
fun TotoroBasicShapes(
    modifier: Modifier = Modifier,
    bodyColor: Color = defaultBodyColor,
    mouseClickPosition: Offset? = null
) {
    // Calculate darker variant for gradients
    val bodyColorDark = bodyColor.copy(
        red = bodyColor.red * 0.85f,
        green = bodyColor.green * 0.85f,
        blue = bodyColor.blue * 0.85f
    )
    Canvas(modifier = modifier.fillMaxSize()) {
        // Calculate center position and sizes based on canvas size
        val centerX = size.width / 2
        val centerY = size.height / 2
        val bodyRadius = minOf(size.width, size.height) * 0.3f
        val bellyRadius = bodyRadius * 0.8f
        val earRadius = bodyRadius * 0.2f

        // Create body gradient
        val bodyGradient = Brush.radialGradient(
            colors = listOf(bodyColor, bodyColorDark),
            center = Offset(centerX + bodyRadius * 0.2f, centerY - bodyRadius * 0.2f),
            radius = bodyRadius * 1.5f
        )

        // Draw main body oval
        val bodyWidth = bodyRadius * 1.6f
        val bodyHeight = bodyRadius * 2f
        drawOval(
            brush = bodyGradient,
            topLeft = Offset(centerX - bodyWidth/2, centerY - bodyHeight/2),
            size = Size(bodyWidth, bodyHeight)
        )
        drawOval(
            color = outlineColor,
            topLeft = Offset(centerX - bodyWidth/2, centerY - bodyHeight/2),
            size = Size(bodyWidth, bodyHeight),
            style = Stroke(width = 4f)
        )

        // Draw belly oval
        val bellyWidth = bodyWidth * 0.8f
        val bellyHeight = bodyHeight * 0.5f
        drawOval(
            color = bellyLightGray,
            topLeft = Offset(
                centerX - bellyWidth/2,
                centerY - bellyHeight * 0.1f  // Moved lower
            ),
            size = Size(bellyWidth, bellyHeight),
        )

        // Draw arrow pattern on belly
        val arrowWidth = bellyWidth * 0.12f
        val arrowHeight = bellyHeight * 0.15f
        val arrowSpacing = arrowWidth * 1.5f

        // Create arrow gradient
        val arrowGradient = Brush.verticalGradient(
            colors = listOf(
                arrowColor.copy(alpha = 0.9f),
                arrowColor.copy(alpha = 0.6f)
            )
        )

        // Draw three arrows in bottom half of belly
        val arrowY = centerY + bellyHeight * 0.2f  // Move arrows higher
        val numArrows = 3
        val totalWidth = arrowSpacing * (numArrows - 1)  // Space between first and last arrow
        val startX = centerX - totalWidth/2  // Center the arrows

        for (i in 0..2) {
            val arrowX = startX + (i * arrowSpacing)

            // Draw arrow
            val arrowPath = Path().apply {
                // Arrow point
                moveTo(arrowX + arrowWidth / 2, arrowY - arrowHeight / 2)
                // Right side
                lineTo(arrowX + arrowWidth, arrowY + arrowHeight / 2)
                // Base indent
                lineTo(arrowX + arrowWidth / 2, arrowY + arrowHeight / 4)
                // Left side
                lineTo(arrowX, arrowY + arrowHeight / 2)
                close()
            }

            drawPath(
                path = arrowPath,
                brush = arrowGradient,
                alpha = 0.7f
            )
        }

        // Draw belly outline
        drawOval(
            color = outlineColor,
            topLeft = Offset(
                centerX - bellyWidth/2,
                centerY - bellyHeight * 0.1f  // Matched with belly position
            ),
            size = Size(bellyWidth, bellyHeight),
            style = Stroke(width = 4f)
        )

        // Draw pointy ears
        val earOffsetY = centerY - bodyHeight/2 - bodyRadius * 0.2f
        val earSpacing = bodyWidth * 0.25f  // Reduced spacing
        val earWidth = bodyWidth * 0.2f
        val earHeight = bodyHeight * 0.25f

        // Create ear paths
        val leftEarPath = Path().apply {
            val baseX = centerX - earSpacing
            val baseY = earOffsetY + earHeight
            moveTo(baseX - earWidth/2, baseY)  // Left base point
            lineTo(baseX, earOffsetY)          // Top point
            lineTo(baseX + earWidth/2, baseY)  // Right base point
            close()
        }

        val rightEarPath = Path().apply {
            val baseX = centerX + earSpacing
            val baseY = earOffsetY + earHeight
            moveTo(baseX - earWidth/2, baseY)  // Left base point
            lineTo(baseX, earOffsetY)          // Top point
            lineTo(baseX + earWidth/2, baseY)  // Right base point
            close()
        }

        // Create ear gradient
        val earGradient = Brush.linearGradient(
            colors = listOf(bodyColor, bodyColorDark),
            start = Offset(0f, earOffsetY),
            end = Offset(0f, earOffsetY + earHeight)
        )

        // Draw left ear
        drawPath(
            path = leftEarPath,
            brush = earGradient
        )
        drawPath(
            path = leftEarPath,
            color = outlineColor,
            style = Stroke(width = 4f)
        )

        // Draw right ear
        drawPath(
            path = rightEarPath,
            brush = earGradient
        )
        drawPath(
            path = rightEarPath,
            color = outlineColor,
            style = Stroke(width = 4f)
        )

        // Draw face features
        val eyeSpacing = bodyWidth * 0.2f  // Reduced to match ear spacing
        val eyeRadius = bodyWidth * 0.08f
        val eyeOffsetY = centerY - bodyHeight * 0.25f  // Moved up slightly

        // Left eye
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX - eyeSpacing, eyeOffsetY)
        )
        drawCircle(
            color = outlineColor,
            radius = eyeRadius,
            center = Offset(centerX - eyeSpacing, eyeOffsetY),
            style = Stroke(width = 4f)
        )
        // Calculate pupil movement
        val maxPupilMove = eyeRadius * 0.2f // Maximum distance pupil can move from center
        val leftPupilBaseX = centerX - eyeSpacing
        val rightPupilBaseX = centerX + eyeSpacing
        val pupilBaseY = eyeOffsetY + eyeRadius * 0.3f

        // Calculate pupil positions based on mouse click
        val (leftPupilX, leftPupilY) = mouseClickPosition?.let { clickPos ->
            val deltaX = (clickPos.x - leftPupilBaseX).coerceIn(-maxPupilMove, maxPupilMove) * 0.3f
            val deltaY = (clickPos.y - pupilBaseY).coerceIn(-maxPupilMove, maxPupilMove) * 0.3f
            Pair(leftPupilBaseX + deltaX, pupilBaseY + deltaY)
        } ?: Pair(leftPupilBaseX, pupilBaseY)

        // Left pupil
        drawCircle(
            color = outlineColor,
            radius = eyeRadius * 0.25f,
            center = Offset(leftPupilX, leftPupilY)
        )

        // Right eye
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX + eyeSpacing, eyeOffsetY)
        )
        drawCircle(
            color = outlineColor,
            radius = eyeRadius,
            center = Offset(centerX + eyeSpacing, eyeOffsetY),
            style = Stroke(width = 4f)
        )
        // Calculate right pupil position
        val (rightPupilX, rightPupilY) = mouseClickPosition?.let { clickPos ->
            val deltaX = (clickPos.x - rightPupilBaseX).coerceIn(-maxPupilMove, maxPupilMove) * 0.3f
            val deltaY = (clickPos.y - pupilBaseY).coerceIn(-maxPupilMove, maxPupilMove) * 0.3f
            Pair(rightPupilBaseX + deltaX, pupilBaseY + deltaY)
        } ?: Pair(rightPupilBaseX, pupilBaseY)

        // Right pupil
        drawCircle(
            color = outlineColor,
            radius = eyeRadius * 0.25f,
            center = Offset(rightPupilX, rightPupilY)
        )

        // Nose (half moon shape)
        val noseRadius = eyeRadius * 0.45f  // Make nose smaller
        val noseY = eyeOffsetY + eyeRadius * 1.1f   // Move slightly higher

        // Create half moon path
        val nosePath = Path().apply {
            // Start from left point
            moveTo(centerX - noseRadius, noseY)

            // Draw bottom curve
            quadraticTo(
                centerX, noseY + noseRadius * 0.6f,  // control point (less deep)
                centerX + noseRadius, noseY          // end point
            )

            // Draw top curve
            quadraticTo(
                centerX, noseY - noseRadius * 0.4f,  // control point (more curved)
                centerX - noseRadius, noseY          // end point
            )

            close()
        }

        // Draw nose fill
        drawPath(
            path = nosePath,
            color = Color(0xFFFE90B3)  // Baker-Miller pink
        )

        // Draw nose outline
        drawPath(
            path = nosePath,
            color = outlineColor,
            style = Stroke(width = 2f)
        )

        // Whiskers
        val whiskerLength = bodyWidth * 0.25f
        val whiskerSpacing = bodyHeight * 0.035f  // Reduced vertical spacing
        val whiskerStartX = centerX - bodyWidth * 0.12f  // Closer to center
        val whiskerEndXOffset = whiskerLength * 0.7f   // Slightly shorter
        val whiskerY = eyeOffsetY + eyeRadius * 2.2f  // Moved closer to eyes

        // Left whiskers
        for (i in -1..1) {
            val yOffset = whiskerSpacing * i
            drawLine(
                color = outlineColor,
                start = Offset(whiskerStartX, whiskerY + yOffset),
                end = Offset(whiskerStartX - whiskerEndXOffset, whiskerY + yOffset + (yOffset * 0.2f)),
                strokeWidth = 2.5f
            )
        }

        // Right whiskers
        val rightWhiskerStartX = centerX + bodyWidth * 0.12f  // Matching left side
        for (i in -1..1) {
            val yOffset = whiskerSpacing * i
            drawLine(
                color = outlineColor,
                start = Offset(rightWhiskerStartX, whiskerY + yOffset),
                end = Offset(rightWhiskerStartX + whiskerEndXOffset, whiskerY + yOffset + (yOffset * 0.2f)),
                strokeWidth = 2.5f
            )
        }

        // Draw tail
        val tailPath = Path().apply {
            val tailStartX = centerX - bodyWidth * 0.4f
            val tailStartY = centerY + bodyHeight * 0.3f
            val tailWidth = bodyWidth * 0.25f
            val tailHeight = bodyHeight * 0.3f

            // Start from the base of the tail
            moveTo(tailStartX, tailStartY)

            // Create a curved tail shape
            quadraticTo(
                tailStartX - tailWidth * 0.5f,  // control point x
                tailStartY - tailHeight * 0.5f,  // control point y
                tailStartX - tailWidth,          // end point x
                tailStartY - tailHeight          // end point y
            )

            // Complete the tail shape
            quadraticTo(
                tailStartX - tailWidth * 0.8f,   // control point x
                tailStartY - tailHeight * 1.2f,  // control point y
                tailStartX,                      // end point x
                tailStartY - tailHeight * 0.8f   // end point y
            )

            // Close the path
            close()
        }

        // Create tail gradient
        val tailGradient = Brush.radialGradient(
            colors = listOf(bodyColor, bodyColorDark),
            center = Offset(
                centerX - bodyWidth * 0.4f,
                centerY + bodyHeight * 0.3f
            ),
            radius = bodyWidth * 0.3f
        )

        // Draw tail fill
        drawPath(
            path = tailPath,
            brush = tailGradient
        )

        // Draw tail outline
        drawPath(
            path = tailPath,
            color = outlineColor,
            style = Stroke(width = 4f)
        )
    }
}
