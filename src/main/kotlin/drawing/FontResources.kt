package drawing

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

object FontResources {
    private val zenMaruFont = Font(
        resource = "fonts/ZenMaruGothic-Regular.ttf",
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )

    private val cherryBombFont = Font(
        resource = "fonts/CherryBombOne-Regular.ttf",
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )

    val JapaneseFamily = FontFamily(zenMaruFont)
    val DisplayFamily = FontFamily(cherryBombFont)
}
