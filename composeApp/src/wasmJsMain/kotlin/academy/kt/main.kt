package academy.kt

import academy.kt.ui.GuesserApp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.marcinmoskala.cpg.LoadFont
import com.russhwolf.settings.StorageSettings
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.asList

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    document.getElementById("no_wasm_comment")?.remove()
    document.getElementById("spinner")?.remove()
    document.body?.children?.asList()?.forEach { it.remove() }
    val settings = StorageSettings()

    // Read URL params: ?challenge={code}
    val challengeCode: String? = window.location.search
        .takeIf { it.startsWith("?challenge=") }
        ?.substringAfter("?challenge=")

    ComposeViewport(document.body!!) {
        LoadFont {
            GuesserApp(settings, challengeCode)
        }
    }
}