package academy.kt.domain

import coroutines.CoroutinesRaceChallenge
import coroutines.CoroutinesRacesDifficulty
import coroutines.generateChallenge

class ChallengeRepository {
    suspend fun getChallenge(expectedStatements: Int, difficulty: CoroutinesRacesDifficulty): CoroutinesRaceChallenge =
        generateChallenge(expectedStatements, difficulty)
}