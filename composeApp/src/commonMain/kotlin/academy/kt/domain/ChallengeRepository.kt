package academy.kt.domain

import androidx.compose.ui.input.key.Key.Companion.Z
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class ChallengeRepository {
    private val client = HttpClient()

    suspend fun getChallenge(expectedStatements: Int, difficulty: CoroutinesRacesDifficulty): CoroutinesRaceChallenge =
        client.get("https://api.kt.academy/game/coroutines/$expectedStatements/$difficulty")
            .bodyAsText()
            .let { Json.decodeFromString<CoroutinesRaceChallenge>(it) }
}

@Serializable
data class CoroutinesRaceChallenge(
    val code: String,
    val sequentialResult: List<String>,
) {
    val possibleAnswers by lazy {
        val valuesUsed = Regex("\"[A-Z]\"").findAll(code).map { it.value }
            .map { it.replace("\"", "") }
        (List(3) { "(${it + 1} sec)" } + valuesUsed + sequentialResult)
            .toSet()
            .sortedWith(compareBy(
                { it.startsWith("(") }, // put special to the end
                { it }
            ))
    }
}

enum class CoroutinesRacesDifficulty {
    Simple,
    WithSynchronization,
    WithExceptions,
    WithSynchronizationAndExceptions
}