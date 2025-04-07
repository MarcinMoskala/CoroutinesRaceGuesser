package academy.kt

import academy.kt.ui.GuesserApp
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { GuesserApp() }