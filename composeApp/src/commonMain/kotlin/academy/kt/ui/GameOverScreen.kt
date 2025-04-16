package academy.kt.ui

import academy.kt.domain.GameScreenState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun GameOverScreen(
    state: GameScreenState.GameOver,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "Game over! ",
            textAlign = TextAlign.Center,
            fontSize = fontSizeBig,
            modifier = Modifier.padding(20.dp),
        )
        Text(
            buildAnnotatedString {
                append("You reached level ${state.level} on ")
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(state.mode.displayName)
                }
                append(" mode.")
                appendLine()
                if (state.isHighestScore) {
                    withStyle(SpanStyle(fontWeight = Bold)) {
                        append("This is your highest score on this mode! 🎉")
                    }
                } else {
                    append("Your highest score on this mode is ${state.highestScore}.")
                }
            },
            textAlign = TextAlign.Center,
            fontSize = fontSizeMedium,
            modifier = Modifier.padding(20.dp),
        )
        Button(state.startAgain) {
            Text(
                "Play again",
                fontSize = fontSizeMedium,
            )
        }
        Text(
            buildAnnotatedString {
                val hyperlinkStyle = SpanStyle(
                    color = Color(0xff64B5F6),
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline
                )
                append("Made with ❤\uFE0F\nby ")
                withLink(LinkAnnotation.Url(url = "https://kt.academy/user/marcinmoskala")) {
                    withStyle(style = hyperlinkStyle) {
                        append("Marcin Moskała")
                    }
                }
                append("\nin ")
                withLink(LinkAnnotation.Url(url = "https://www.jetbrains.com/compose-multiplatform/")) {
                    withStyle(style = hyperlinkStyle) {
                        append("Compose Multiplatform")
                    }
                }
                append(".\n\nIf you want to improve your Kotlin Coroutines skills,\ncheck out ")
                withLink(LinkAnnotation.Url(url = "https://kt.academy/book/coroutines")) {
                    withStyle(style = hyperlinkStyle) {
                        append("Kotlin Coroutines: Deep Dive book")
                    }
                }
                append(" or ")
                withLink(LinkAnnotation.Url(url = "https://kt.academy/#workshops-offer")) {
                    withStyle(style = hyperlinkStyle) {
                        append("my workshops")
                    }
                }
                append(".")
            },
            textAlign = TextAlign.Center,
            fontSize = fontSizeSmall,
            modifier = Modifier.padding(20.dp),
        )
    }
}