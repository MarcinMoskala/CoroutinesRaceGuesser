package academy.kt

import academy.kt.ui.GuesserApp
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CoroutinesRaceGuesser",
    ) {
        GuesserApp()
    }
}