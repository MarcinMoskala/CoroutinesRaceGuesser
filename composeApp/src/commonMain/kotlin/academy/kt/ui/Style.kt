package academy.kt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coroutinesraceguesser.composeapp.generated.resources.DMSans_ExtraBold
import coroutinesraceguesser.composeapp.generated.resources.DMSans_Regular
import coroutinesraceguesser.composeapp.generated.resources.Rajdhani_SemiBold
import coroutinesraceguesser.composeapp.generated.resources.Res

val backgroundColor = Color(0xFF030207)
val blueColor = Color(0xFF5E7BE9)
val orangeColor = Color(0xFFFC692C)

@Composable
fun GameDesign(
    content: @Composable () -> Unit,
) {
    val fontBody = FontFamily(Font(Res.font.DMSans_ExtraBold))
    val fontHeader = FontFamily(Font(Res.font.Rajdhani_SemiBold))
    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                fontFamily = fontHeader,
                fontSize = 60.sp,
                lineHeight = 64.sp
            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontHeader,
                fontSize = 42.sp,
                lineHeight = 46.sp
            ),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontBody,
                fontSize = 24.sp,
                lineHeight = 28.sp
            ),
            bodySmall = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontBody,
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
        ),
        colorScheme = lightColorScheme(
            primary = orangeColor,
            secondary = blueColor,
            background = backgroundColor,
            surface = backgroundColor,
            error = Color(0xffb00020),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White,
            onError = Color.White,
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(backgroundColor)
        ) {
            content()
        }
    }
}