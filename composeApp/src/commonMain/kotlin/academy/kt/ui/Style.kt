package academy.kt.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
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
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontSize = fontSizeMedium,

            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                fontSize = fontSizeBig,
            ),
        ),
        colorScheme = lightColorScheme(
            primary = blueColor,
            secondary = orangeColor,
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