package academy.kt

import academy.kt.domain.CoroutinesRacesDifficulty
import academy.kt.domain.GameMode
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
    trackEvent("app_open")
    document.getElementById("no_wasm_comment")?.remove()
    document.getElementById("spinner")?.remove()
    document.body?.children?.asList()?.forEach { it.remove() }
    val settings = StorageSettings()

    // Read URL params: ?user=$userId&mode=$mode&minLevel=$minLevel
    val challengeData: GameMode.ChallengeMode? = window.location.search
        .takeUnless { it.isEmpty() }
        ?.let { if(it.startsWith("?")) it.drop(1) else it }
        ?.split("&")
        ?.map { it.split("=") }
        ?.filter { it.size == 2 }
        ?.associate { it[0] to it[1] }
        ?.let {
            if (it.containsKey("user") && it.containsKey("mode") && it.containsKey("minLevel")) {
                GameMode.ChallengeMode(
                    userId = it["user"] ?: return@let null,
                    difficulty = try {
                        CoroutinesRacesDifficulty.valueOf(it["mode"]!!)
                    } catch (e: Exception) {
                        return@let null
                    },
                    levelToReach = it["minLevel"]!!.toIntOrNull() ?: return@let null
                )
            } else {
                null
            }
        }

    if (challengeData == null && window.location.search.isNotEmpty() && window.location.search != "?") {
        window.location.search = ""
    }

    ComposeViewport(document.body!!) {
        LoadFont {
            GuesserApp(settings, challengeData)
        }
    }
}