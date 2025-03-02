package drawing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextTest(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
            .wrapContentHeight(Alignment.Bottom),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "となりのトトロ",
            style = TextStyle(
                fontFamily = FontResources.JapaneseFamily,
                fontSize = 28.sp,
                color = Color(0xFFFE6515)  // Orange (Pantone)
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "My Neighbor Totoro",
            style = TextStyle(
                fontFamily = FontResources.DisplayFamily,
                fontSize = 38.sp,
                color = Color(0xFFFE90B3)  // Baker-Miller pink
            )
        )
    }
}
