package academy.kt

import academy.kt.ui.GamePlot
import academy.kt.ui.GameStoryDialogScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview(device = "id:pixel_xl")
@Preview(device = "id:desktop_medium")
@Composable
fun GamePlotNoTextPreview() {
    GamePlot(text = null, onPlay = {})
}

@Preview(device = "id:pixel_xl")
@Preview(device = "id:desktop_medium")
@Composable
fun GamePlotTextPreview() {
    GamePlot(
        text = "So you are the new pretender to become a master of Kotlin Coroutines?",
        onPlay = {})
}

@Preview(device = "id:pixel_xl")
@Preview(device = "id:desktop_medium")
@Composable
fun GamePlotScreenPreview() {
    GameStoryDialogScreen(
        dialogs = listOf(
            "So you are the new pretender to become a master of Kotlin Coroutines?",
            "Let's see how you do with basic structures..."
        ),
        onNext = {}
    )
}