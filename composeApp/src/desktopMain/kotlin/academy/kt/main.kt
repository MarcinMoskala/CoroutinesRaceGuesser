package academy.kt

import academy.kt.ui.GuesserScreen
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CoroutinesRaceGuesser",
    ) {
        GuesserScreen()
    }
}