package drawing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.ExperimentalTextApi

@OptIn(ExperimentalTextApi::class)
@Composable
fun RainbowText(
    modifier: Modifier = Modifier
) {
    var rotation by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while(true) {
            rotation = (rotation + 1f) % 200f  // Slower for mirrored effect
            delay(16) // ~60fps
        }
    }

    val rainbowColors = listOf(
        Color(0xFFFE90B3), // Baker-Miller pink
        Color(0xFFFE6515), // Orange (Pantone)
        Color(0xFFFDE773), // Jasmine
        Color(0xFF94B758), // Olivine
        Color(0xFF6A84B0), // Glaucous
        Color(0xFF443F5D), // English Violet
        Color(0xFFFE90B3)  // Back to pink for smooth transition
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "vibecoding",
            style = TextStyle(
                fontFamily = FontResources.DisplayFamily,
                fontSize = 96.sp,
                textAlign = TextAlign.End,
                brush = Brush.linearGradient(
                    colors = rainbowColors.map { it.copy(alpha = 0.95f) },
                    start = Offset(-50f + rotation, 0f),
                    end = Offset(50f + rotation, 0f),
                    tileMode = TileMode.Mirror  // Mirrored gradient
                )
            ),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height((-16).dp))

        Text(
            text = "with Junie",
            style = TextStyle(
                fontFamily = FontResources.JapaneseFamily,
                fontSize = 42.sp,
                textAlign = TextAlign.End,
                color = Color(0xFF94B758)  // Olivine (light green)
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
