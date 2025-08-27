package academy.kt

import academy.kt.ui.GuesserApp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.russhwolf.settings.PreferencesSettings
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import java.util.prefs.Preferences
import kotlin.jvm.java


fun main() {
    val preferences = PreferencesSettings(Preferences.userRoot())
    singleWindowApplication(
        title = "My CHR App",
        state = WindowState(width = 800.dp, height = 800.dp),
        alwaysOnTop = true
    ) {
        GuesserApp(preferences)
    }
}
