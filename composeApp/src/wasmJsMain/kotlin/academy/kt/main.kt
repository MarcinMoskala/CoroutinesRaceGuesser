package academy.kt

import academy.kt.ui.GuesserApp
import academy.kt.ui.fontSizeBig
import academy.kt.ui.fontSizeMedium
import academy.kt.ui.fontSizeSmall
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        MaterialTheme(
            typography = MaterialTheme.typography.copy(
                h3 = androidx.compose.ui.text.TextStyle(
                    fontSize = 40.sp,
                ),
                body1 = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                ),
                button = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                )
            )
        ) {
            GuesserApp()
        }
    }
}