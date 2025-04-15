package academy.kt

import academy.kt.ui.GuesserApp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.marcinmoskala.cpg.LoadFont
import kotlinx.browser.document
import kotlinx.browser.localStorage
import org.w3c.dom.asList

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    document.getElementById("no_wasm_comment")?.remove()
    document.getElementById("spinner")?.remove()
    document.body?.children?.asList()?.forEach { it.remove() }

    val firstTime = localStorage.getItem("first_time") != "false"
    if (firstTime) {
        localStorage.setItem("first_time", "false")
    }
    ComposeViewport(document.body!!) {
        LoadFont {
            GuesserApp(firstTime)
        }
    }
}