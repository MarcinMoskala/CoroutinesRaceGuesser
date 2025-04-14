package academy.kt.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

val fontSizeBig = 40.sp
val fontSizeMedium = 24.sp
val fontSizeSmall = 16.sp

val blueColor = Color(0xff2e90cf)
val orangeColor = Color(0xffffa621)

@Composable
fun GameDesign(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            body1 = MaterialTheme.typography.body1.copy(
                fontSize = fontSizeMedium,
            ),
            h3 = MaterialTheme.typography.h3.copy(
                fontSize = fontSizeBig,
            ),
        ),
        colors = lightColors(
            primary = blueColor,
            primaryVariant = blueColor,
            secondary = orangeColor,
            secondaryVariant = orangeColor,
            background = Color.White,
            surface = Color.White,
            error = Color(0xffb00020),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = Color.Black,
            onSurface = Color.Black,
            onError = Color.White,
        )
    ) {
        content()
    }
}