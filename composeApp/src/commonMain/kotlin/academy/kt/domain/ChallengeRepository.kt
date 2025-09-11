package academy.kt.domain

import androidx.compose.ui.input.key.Key.Companion.Z
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class ChallengeRepository {
    private val client = HttpClient()

    private val baseUrl = "https://api.kt.academy"

    suspend fun getChallenge(expectedStatements: Int, difficulty: CoroutinesRacesDifficulty): CoroutinesRaceChallenge =
        client.get("$baseUrl/game/coroutines/$expectedStatements/$difficulty")
            .bodyAsText()
            .let { Json.decodeFromString<CoroutinesRaceChallenge>(it) }

    suspend fun decodeChallenge(challengeCode: String): CodeChallenge =
        client.post("$baseUrl/game/decode/$challengeCode")
            .bodyAsText()
            .let { Json.decodeFromString<CodeChallenge>(it) }

    suspend fun onUserReachedScore(userId: String, mode: String, score: Int) {
        client.post("$baseUrl/game/challenge/score/$userId/$mode/$score")
    }
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

@Serializable
data class CodeChallenge(
    val userId: String,
    val mode: String,
    val minLevel: Int
)

enum class CoroutinesRacesDifficulty {
    Simple,
    WithSynchronization,
    WithExceptions,
    WithSynchronizationAndExceptions
}