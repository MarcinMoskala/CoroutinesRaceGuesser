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
import androidx.compose.ui.unit.sp

val backgroundColor = Color(0xFF030207)
val blueColor = Color(0xFF5E7BE9)
val orangeColor = Color(0xFFFC692C)

@Composable
fun GameDesign(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 24.sp,
                lineHeight = 28.sp
            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                fontSize = 42.sp,
            ),
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                fontSize = 60.sp,
            ),
            bodySmall = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp,
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